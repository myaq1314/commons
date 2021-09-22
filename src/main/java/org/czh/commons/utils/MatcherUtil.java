package org.czh.commons.utils;

import org.czh.commons.validate.EmptyAssert;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : czh
 * description :
 * date : 2021-07-03
 * email 916419307@qq.com
 */
public final class MatcherUtil {

    public static List<String> match(String sourceText, String regx) {
        EmptyAssert.allNotNull(sourceText, regx);

        List<String> resultList = new ArrayList<>();
        Matcher matcher = Pattern.compile(regx, Pattern.CASE_INSENSITIVE).matcher(sourceText);
        while (matcher.find()) {
            resultList.add(matcher.group(0));
        }
        return resultList;
    }

    public static void main(String[] args) {
        String sourceText = "insert into `tb_base_primary_test` (`id`, `name`, `age`, `score`) values (#{id}, #{name}, #{age}, #{score});";
        String regx = "#\\{(.*?)}";

        List<String> matchList = match(sourceText, regx);
        System.out.println(matchList); // [#{id}, #{name}, #{age}, #{score}]
    }
}
