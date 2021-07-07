package org.czh.commons.utils.http;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.czh.commons.annotations.tag.NotBlankTag;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.enums.parent.IKeyEnum;
import org.czh.commons.utils.ConstructorUtil;
import org.czh.commons.validate.EmptyAssert;

/**
 * @author : czh
 * description :
 * date : 2021-07-07
 * email 916419307@qq.com
 */
@Getter
@AllArgsConstructor
public enum EnclosingEnum implements IKeyEnum<Class<? extends HttpEntityEnclosingRequestBase>> {

    POST(HttpPost.class),
    PUT(HttpPut.class),
    DELETE(HttpDeleteWithBody.class),

    // 占位符
    ;

    public final Class<? extends HttpEntityEnclosingRequestBase> key;

    public static HttpEntityEnclosingRequestBase getEnclosingRequest(@NotNullTag final EnclosingEnum enclosingEnum,
                                                                     @NotBlankTag final String url) {
        EmptyAssert.isNotNull(enclosingEnum);
        EmptyAssert.isNotBlank(url);
        return ConstructorUtil.newInstance(ConstructorUtil.newConstructor(enclosingEnum.key, String.class), url);
    }
}