package org.czh.commons.utils.excel;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.eventusermodel.FormatTrackingHSSFListener;
import org.apache.poi.hssf.eventusermodel.HSSFEventFactory;
import org.apache.poi.hssf.eventusermodel.HSSFListener;
import org.apache.poi.hssf.eventusermodel.HSSFRequest;
import org.apache.poi.hssf.eventusermodel.MissingRecordAwareHSSFListener;
import org.apache.poi.hssf.record.BOFRecord;
import org.apache.poi.hssf.record.BlankRecord;
import org.apache.poi.hssf.record.BoolErrRecord;
import org.apache.poi.hssf.record.BoundSheetRecord;
import org.apache.poi.hssf.record.FormulaRecord;
import org.apache.poi.hssf.record.LabelSSTRecord;
import org.apache.poi.hssf.record.NumberRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.RowRecord;
import org.apache.poi.hssf.record.SSTRecord;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.czh.commons.validate.EmptyValidate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author : czh
 * description :
 * date : 2021-06-29
 * email 916419307@qq.com
 */
@Slf4j
public final class Excel03FileParser extends AbstractExcelFileParser implements HSSFListener {

    private final List<BoundSheetRecord> boundSheetRecords = new ArrayList<>();
    //    private boolean outputFormulaValues = true;
    private POIFSFileSystem poifsFileSystem;
    private SSTRecord sstRecord;
    private int sheetIndex = -1;
    private BoundSheetRecord[] orderedBSRs;
    private int colNum;

    @Override
    protected void beforeParse(File file) throws IOException {
        super.beforeParse(file);

        poifsFileSystem = new POIFSFileSystem(new FileInputStream(file));
    }

    @Override
    protected void parse() throws IOException {
        MissingRecordAwareHSSFListener listener = new MissingRecordAwareHSSFListener(this);
        FormatTrackingHSSFListener formatListener = new FormatTrackingHSSFListener(listener);
        HSSFEventFactory factory = new HSSFEventFactory();
        HSSFRequest request = new HSSFRequest();
        request.addListenerForAllRecords(formatListener);
//        if (outputFormulaValues) {
//            request.addListenerForAllRecords(formatListener);
//        } else {
//            EventWorkbookBuilder.SheetRecordCollectingListener workbookBuildingListener = new EventWorkbookBuilder
//                    .SheetRecordCollectingListener(formatListener);
//            request.addListenerForAllRecords(workbookBuildingListener);
//        }

        factory.processWorkbookEvents(request, poifsFileSystem);

        if (this.fileHandler != null) {
            this.fileHandler.handle(this.dataList);
        }
        this.sheetName = null;
        this.headerMap = null;
        this.dataList = null;
    }

    @Override
    protected void afterParse(File file) {
        super.afterParse(file);

        if (poifsFileSystem != null) {
            try {
                poifsFileSystem.close();
            } catch (IOException e) {
                log.warn("文件流关闭失败", e);
            }
        }
    }

    @Override
    public void processRecord(Record record) {
        switch (record.getSid()) {
            case BOFRecord.sid:
                BOFRecord bof = (BOFRecord) record;
                // 顺序进入新的Workbook
                if (bof.getType() == BOFRecord.TYPE_WORKBOOK) {
                    log.debug("开始解析excel 文档.....");
                    // 顺序进入新的Worksheet，因为Event API不会把Excel文件里的所有数据结构都关联起来，
                    // 所以这儿一定要记录现在进入第几个sheet了。
                } else if (bof.getType() == BOFRecord.TYPE_WORKSHEET) {
                    // 读取新的一个Sheet页
                    log.debug("开始解析sheet页面内容...");
                    sheetIndex++;

                    if (orderedBSRs == null) {
                        orderedBSRs = BoundSheetRecord.orderByBofPosition(boundSheetRecords);
                    }

                    if (EmptyValidate.isNotBlank(this.sheetName)) {
                        if (this.fileHandler != null) {
                            this.fileHandler.handle(this.dataList);
                        }
                    }

                    this.sheetName = orderedBSRs[sheetIndex].getSheetname();
                    this.headerMap = new HashMap<>();
                    this.dataList = new ArrayList<>();
                }
                break;
            // 开始解析Sheet的信息，记录sheet，这儿会把所有的sheet都顺序打印出来
            case BoundSheetRecord.sid:
                BoundSheetRecord bsr = (BoundSheetRecord) record;
                boundSheetRecords.add(bsr);
                log.debug("New sheet named: {}", bsr.getSheetname());
                break;
            // 执行行记录事件
            case RowRecord.sid:
                RowRecord rowrec = (RowRecord) record;
                colNum = rowrec.getLastCol();

                log.debug("第{}行记录开始, first column at {}， last column at {}", rowrec.getRowNumber(),
                        rowrec.getFirstCol(), rowrec.getLastCol());
                break;
            // SSTRecords store a array of unique strings used in Excel.
            case SSTRecord.sid:
                sstRecord = (SSTRecord) record;
                break;
            case FormulaRecord.sid: // 数式
                FormulaRecord fr = (FormulaRecord) record;
                cell(fr.getRow(), fr.getColumn(), String.valueOf(fr.getValue()));
                break;

            // 发现数字类型的cell，数字和日期都是用这个格式
            case NumberRecord.sid:
                NumberRecord nr = (NumberRecord) record;
                cell(nr.getRow(), nr.getColumn(), nr.getValue());
                break;
            // 发现字符串类型，这儿要取字符串的值的话，跟据其index去字符串表里读取
            case LabelSSTRecord.sid:
                LabelSSTRecord lsr = (LabelSSTRecord) record;
                cell(lsr.getRow(), lsr.getColumn(), sstRecord.getString(lsr.getSSTIndex()).getString());
                break;
            case BoolErrRecord.sid: // 解析boolean错误信息
                BoolErrRecord ber = (BoolErrRecord) record;
                if (ber.isBoolean()) {
                    cell(ber.getRow(), ber.getColumn(), ber.getBooleanValue());
                }
                break;
            // 空白记录的信息
            case BlankRecord.sid:
                BlankRecord br = (BlankRecord) record;
                cell(br.getRow(), br.getColumn(), "");
                break;
            default:
                break;
        }
    }

    /**
     * 处理单元格数据并检查是否换行
     *
     * @param row       实际当前行号
     * @param col       实际记录当前列
     * @param cellValue 当前cell的值
     */
    public void cell(int row, int col, Object cellValue) {
        currentRow = row;
        currentCol = col;

        // 处理文件单元格的内容
        processContent(cellValue);
    }

    @Override
    protected void processBody(Object cellValue) {
        if (this.columnMap == null) {
            startRow(currentRow);
        }

        super.processBody(cellValue);

        if (currentCol == colNum - 1) {
            endRow(currentRow);
        }
    }
}
