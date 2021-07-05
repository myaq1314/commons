package org.czh.commons.utils.excel;

import java.util.List;
import java.util.Map;

/**
 * @author : czh
 * description :
 * date : 2021-06-29
 * email 916419307@qq.com
 */
public interface IFileHandler {

    default void beforeHandle() {

    }

    default void handleHeader(Map<String, Object> headerMap) {

    }

    void handle(List<Map<String, Object>> dataList);

    default void afterHandle(int totalRow) {

    }
}
