package org.czh.commons.utils.excel;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;
import org.czh.commons.validate.FlagAssert;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : czh
 * description :
 * date : 2021-06-29
 * email 916419307@qq.com
 */
@Slf4j
@NoArgsConstructor
public abstract class AbstractFileParser extends AbstractBuildFileParser implements IFileParser {

    protected IFileHandler fileHandler; // 处理器

    protected int currentRow = 0; // 当前行号
    protected int errorRow = 0; // 错误行号

    protected Map<Integer, String> headerMap = new HashMap<>(); // 标题内容
    protected List<Map<String, Object>> dataList; // 数据内容

    /**
     * 解析入口 具体实现
     *
     * @param file        文件
     * @param fileHandler 处理器
     * @throws IOException 文件输入输出异常
     */
    @Override
    public void parse(File file, IFileHandler fileHandler) throws IOException {
        this.fileHandler = fileHandler;

        this.beforeParse(file);
        this.parse();
        this.afterParse(file);
    }

    /**
     * 解析前置处理
     * 校验文件
     * 调用处理器前置方法
     *
     * @param file 文件
     * @throws IOException 文件输入输出异常
     */
    protected void beforeParse(File file) throws IOException {
        log.info("Before excel......");
        EmptyAssert.isNotNull(file);
        FlagAssert.isTrue(file.canRead());
        log.info("execute handler.beforeHandle()");

        if (this.fileHandler != null) {
            this.fileHandler.beforeHandle();
        }
    }

    /**
     * 具体解析方法
     *
     * @throws IOException 文件输入输出异常
     */
    protected abstract void parse() throws IOException;

    /**
     * 解析后置处理
     * 处理剩余解析到的目标对象
     *
     * @param file 文件
     */
    protected void afterParse(File file) {
        log.info("After excel......");
        if (EmptyValidate.isNotEmpty(this.dataList)) {
            if (this.fileHandler != null) {
                this.fileHandler.handle(this.dataList);
            }
        }
    }

    /**
     * 处理解析出来的文件内容
     *
     * @param value 解析出来的文件头内容
     */
    protected void processContent(Object value) {
        if (isHeader()) {
            processHeader(value);
        } else if (isBody()) {
            processBody(value);
        }
    }

    /**
     * 判断当前解析内容是否是文件头
     */
    protected boolean isHeader() {
        return this.currentRow == this.headerRowIndex;
    }

    /**
     * 处理文件头
     */
    protected void processHeader(Object value) {

    }

    /**
     * 判断当前解析内容是否是文件主体
     */
    protected boolean isBody() {
        return this.currentRow >= this.dataRowIndex;
    }

    /**
     * 处理文件主体
     */
    protected abstract void processBody(Object value);

}
