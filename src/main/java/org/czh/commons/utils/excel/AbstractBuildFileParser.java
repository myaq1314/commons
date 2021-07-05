package org.czh.commons.utils.excel;

import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;

/**
 * @author : czh
 * description :
 * date : 2021-06-29
 * email 916419307@qq.com
 */
@NoArgsConstructor
public abstract class AbstractBuildFileParser implements IFileParser {

    protected static final int DEFAULT_BATCH_SIZE = 16;
    protected static final int DEFAULT_HEADER_INDEX = 0;
    protected static final int DEFAULT_DATA_INDEX = 2;

    protected int batchSize = DEFAULT_BATCH_SIZE;
    protected int headerRowIndex = DEFAULT_HEADER_INDEX;
    protected int dataRowIndex = DEFAULT_DATA_INDEX;

    @Override
    public IFileParser setHeaderRowIndex(int headerRowIndex) {
        this.headerRowIndex = headerRowIndex;
        return this;
    }

    @Override
    public IFileParser setDataRowIndex(int dataRowIndex) {
        this.dataRowIndex = dataRowIndex;
        return this;
    }

    public abstract void parse(File file, IFileHandler fileHandler) throws IOException;

    @Override
    public IFileParser setBatchSize(int batchSize) {
        this.batchSize = batchSize;
        return this;
    }
}
