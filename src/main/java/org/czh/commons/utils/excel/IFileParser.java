package org.czh.commons.utils.excel;

import java.io.File;
import java.io.IOException;

/**
 * @author : czh
 * description : 解析器基类
 * date : 2021-06-29
 * email 916419307@qq.com
 */
public interface IFileParser {

    /**
     * 设置批次大小
     */
    IFileParser setBatchSize(int batchSize);

    /**
     * 设置标题行号
     */
    IFileParser setHeaderRowIndex(int headerRowIndex);

    /**
     * 设置内容行号
     */
    IFileParser setDataRowIndex(int dataRowIndex);

    /**
     * 解析入口
     *
     * @param file        文件
     * @param fileHandler 处理器
     * @throws IOException 文件输入输出异常
     */
    void parse(File file, IFileHandler fileHandler) throws IOException;

}
