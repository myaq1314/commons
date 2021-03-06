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
                log.warn("?????????????????????", e);
            }
        }
    }

    @Override
    public void processRecord(Record record) {
        switch (record.getSid()) {
            case BOFRecord.sid:
                BOFRecord bof = (BOFRecord) record;
                // ??????????????????Workbook
                if (bof.getType() == BOFRecord.TYPE_WORKBOOK) {
                    log.debug("????????????excel ??????.....");
                    // ??????????????????Worksheet?????????Event API?????????Excel????????????????????????????????????????????????
                    // ????????????????????????????????????????????????sheet??????
                } else if (bof.getType() == BOFRecord.TYPE_WORKSHEET) {
                    // ??????????????????Sheet???
                    log.debug("????????????sheet????????????...");
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
            // ????????????Sheet??????????????????sheet????????????????????????sheet?????????????????????
            case BoundSheetRecord.sid:
                BoundSheetRecord bsr = (BoundSheetRecord) record;
                boundSheetRecords.add(bsr);
                log.debug("New sheet named: {}", bsr.getSheetname());
                break;
            // ?????????????????????
            case RowRecord.sid:
                RowRecord rowrec = (RowRecord) record;
                colNum = rowrec.getLastCol();

                log.debug("???{}???????????????, first column at {}??? last column at {}", rowrec.getRowNumber(),
                        rowrec.getFirstCol(), rowrec.getLastCol());
                break;
            // SSTRecords store a array of unique strings used in Excel.
            case SSTRecord.sid:
                sstRecord = (SSTRecord) record;
                break;
            case FormulaRecord.sid: // ??????
                FormulaRecord fr = (FormulaRecord) record;
                cell(fr.getRow(), fr.getColumn(), String.valueOf(fr.getValue()));
                break;

            // ?????????????????????cell???????????????????????????????????????
            case NumberRecord.sid:
                NumberRecord nr = (NumberRecord) record;
                cell(nr.getRow(), nr.getColumn(), nr.getValue());
                break;
            // ?????????????????????????????????????????????????????????????????????index????????????????????????
            case LabelSSTRecord.sid:
                LabelSSTRecord lsr = (LabelSSTRecord) record;
                cell(lsr.getRow(), lsr.getColumn(), sstRecord.getString(lsr.getSSTIndex()).getString());
                break;
            case BoolErrRecord.sid: // ??????boolean????????????
                BoolErrRecord ber = (BoolErrRecord) record;
                if (ber.isBoolean()) {
                    cell(ber.getRow(), ber.getColumn(), ber.getBooleanValue());
                }
                break;
            // ?????????????????????
            case BlankRecord.sid:
                BlankRecord br = (BlankRecord) record;
                cell(br.getRow(), br.getColumn(), "");
                break;
            default:
                break;
        }
    }

    /**
     * ??????????????????????????????????????????
     *
     * @param row       ??????????????????
     * @param col       ?????????????????????
     * @param cellValue ??????cell??????
     */
    public void cell(int row, int col, Object cellValue) {
        currentRow = row;
        currentCol = col;

        // ??????????????????????????????
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
