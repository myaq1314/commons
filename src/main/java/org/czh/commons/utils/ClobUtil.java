package org.czh.commons.utils;

import lombok.extern.slf4j.Slf4j;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.validate.EmptyAssert;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;

/**
 * @author : czh
 * description :
 * date : 2021-07-01
 * email 916419307@qq.com
 */
@Slf4j
public final class ClobUtil {

    public static String covertToText(@NotNullTag Clob clob) {
        EmptyAssert.isNotNull(clob);

        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(clob.getCharacterStream())) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            return builder.toString();
        } catch (SQLException | IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException("Clob转换文本失败");
        }

    }

}
