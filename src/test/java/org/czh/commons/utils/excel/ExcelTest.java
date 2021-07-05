package org.czh.commons.utils.excel;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @author : czh
 * description :
 * date : 2021-06-29
 * email 916419307@qq.com
 */
public class ExcelTest {

    private static final String filePath =
            "./src/test/java/org/czh/commons/utils/excel/";

    public static void main(String[] args) {
        try {
            new Excel03FileParser()
                    .setHeaderRowIndex(0)
                    .setDataRowIndex(1)
                    .setBatchSize(2)
                    .parse(new File(filePath + File.separator + "test.xls"), dataList -> {
                        System.out.println("开始");
                        for (Map<String, Object> data : dataList) {
                            System.out.println(data);
                        }
                        System.out.println("结束");
                    });

            System.out.println("------------");

            new Excel07FileParser()
                    .setHeaderRowIndex(0)
                    .setDataRowIndex(1)
                    .setBatchSize(10)
                    .parse(new File(filePath + File.separator + "test.xlsx"), dataList -> {
                        System.out.println("开始");
                        for (Map<String, Object> data : dataList) {
                            System.out.println(data);
                        }
                        System.out.println("结束");
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
