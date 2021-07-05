package org.czh.commons.utils.excel;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.czh.commons.validate.EmptyValidate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : czh
 * description :
 * date : 2021-06-29
 * email 916419307@qq.com
 */
@Slf4j
@NoArgsConstructor
public abstract class AbstractExcelFileParser extends AbstractFileParser {

    protected int currentCol;
    protected Map<String, Object> columnMap; // 一行数据内容
    protected String sheetName; // 当前sheet页名称

    public void startRow(int rowNum) {
        this.columnMap = new HashMap<>();
    }

    public void endRow(int rowNum) {
        if (EmptyValidate.isNotEmpty(columnMap)) {
            this.columnMap.put("sheetName", sheetName);
            this.columnMap.put("currentRow", currentRow);

            this.dataList.add(this.columnMap);
            this.columnMap = null;
        }

        if (this.dataList.size() >= batchSize) {
            if (this.fileHandler != null) {
                this.fileHandler.handle(this.dataList);
                this.dataList = new ArrayList<>(this.batchSize);
            }
        }
    }

    @Override
    protected abstract void parse() throws IOException;

    /**
     * 处理文件头
     */
    @Override
    protected void processHeader(Object value) {
        this.headerMap.put(this.currentCol, value.toString());
    }

    @Override
    protected void processBody(Object value) {
        if (this.headerRowIndex < 0) {
            this.columnMap.put(String.valueOf(this.currentCol), value);
            return;
        }

        String header = this.headerMap.get(this.currentCol);
        this.columnMap.put(header, value);
    }
}
