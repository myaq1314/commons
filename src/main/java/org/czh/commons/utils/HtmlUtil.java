package org.czh.commons.utils;

import org.czh.commons.annotations.tag.NotBlankTag;
import org.czh.commons.validate.EmptyAssert;

import java.util.regex.Pattern;

/**
 * @author : czh
 * description :
 * date : 2021-07-02
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public final class HtmlUtil {

    /**
     * html script
     */
    public static final String HTML_SCRIPT_REGEX = "<script[^>]*?>[\\s\\S]*?</script>";
    /**
     * html style
     */
    public static final String HTML_STYLE_REGEX = "<style[^>]*?>[\\s\\S]*?</style>";
    /**
     * html 标签
     */
    public static final String HTML_LABEL_REGEX = "<[^>]+>";
    /**
     * html特殊字符
     */
    public static final String HTML_SPECIAL_REGEX = "&[a-zA-Z]{1,10};";

    public static String parse(@NotBlankTag String txt) {
        EmptyAssert.isNotBlank(txt);

        return txt.replaceAll("&", "&amp;")
                .replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;")
                .replaceAll(" ", "&nbsp;")
                .replaceAll("'", "&#39;")
                .replaceAll("\"", "&quot;")
                .replaceAll("\n", "<br />");
    }

    public static String format(@NotBlankTag String html) {
        EmptyAssert.isNotBlank(html);

        html = Pattern.compile(HTML_SCRIPT_REGEX, Pattern.CASE_INSENSITIVE).matcher(html).replaceAll("");
        html = Pattern.compile(HTML_STYLE_REGEX, Pattern.CASE_INSENSITIVE).matcher(html).replaceAll("");
        html = Pattern.compile(HTML_LABEL_REGEX, Pattern.CASE_INSENSITIVE).matcher(html).replaceAll("");
        html = Pattern.compile(HTML_SPECIAL_REGEX, Pattern.CASE_INSENSITIVE).matcher(html).replaceAll("");
        return html.trim().replaceAll("'", "");
    }
}
