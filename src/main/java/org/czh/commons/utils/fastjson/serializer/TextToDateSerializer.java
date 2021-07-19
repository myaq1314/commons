package org.czh.commons.utils.fastjson.serializer;

import org.czh.commons.utils.fastjson.ConvertUtil;

import java.util.Date;

/**
 * @author : czh
 * description :
 * date : 2021-07-01
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public class TextToDateSerializer extends AbstractContextObjectSerializer<String, Date> {

    @Override
    public Date serialize(String source, String format) {
        return ConvertUtil.textToDate(source, format);
    }
}
