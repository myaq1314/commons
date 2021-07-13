package org.czh.commons.utils.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.czh.commons.annotations.tag.NotBlankTag;
import org.czh.commons.entity.domain.IBaseDO;
import org.czh.commons.utils.DateUtil;
import org.czh.commons.utils.date.CalendarUtil;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;

import java.util.Date;

/**
 * @author : czh
 * description :
 * date : 2021-07-07
 * email 916419307@qq.com
 */
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CookieDo implements IBaseDO {

    private static final long serialVersionUID = -1113388478764957720L;

    private String cookieText;
    private String name;
    private String value;
    private String domain;
    private String path;
    private Date expires;
    private Integer maxAge;
    private Integer size;
    private Boolean httpOnly;
    private Boolean secure;
    private String sameSite;
//    private String sameParty;
//    private String priority;

    public CookieDo(@NotBlankTag String cookieText, @NotBlankTag String domain) {
        EmptyAssert.isNotBlank(cookieText);
        this.cookieText = cookieText;

        String[] eachArray = cookieText.split(";");
        String cookieNameValue = eachArray[0].trim();
        int index = cookieNameValue.indexOf("=");
        // httpOnly
        if (index < 0) {
            System.err.printf("%s error: %s\n", "cookieNameValue", cookieText);
        } else if (index == cookieNameValue.length() - 1) { // t_uid=
            this.name = cookieNameValue.substring(0, index);
            this.value = "";
        } else { // t_uid=232
            this.name = cookieNameValue.substring(0, index);
            this.value = cookieNameValue.substring(index + 1);
        }
        if ("last-domain".equals(this.name)) {
            this.domain = "www" + domain;
        }

        for (int i = 1; i < eachArray.length; i++) {
            String nextCookieItem = eachArray[i].toLowerCase().trim();
            if (nextCookieItem.startsWith("domain")) {
                if (domain == null) {
                    String[] domainArray = nextCookieItem.split("=");
                    this.domain = domainArray[1];
                } else {
                    this.domain = domain;
                }
            } else if (nextCookieItem.startsWith("path")) {
                String[] pathArray = nextCookieItem.split("=");
                this.path = pathArray[1];
            } else if (nextCookieItem.startsWith("expires")) {
                String[] expiresArray = nextCookieItem.split("=");
                this.expires = parseGMT(expiresArray[1].trim());
            } else if (nextCookieItem.startsWith("max-age")) {
                String[] maxAgeArray = nextCookieItem.split("=");
                this.maxAge = Integer.valueOf(maxAgeArray[1].trim());
            } else if (nextCookieItem.startsWith("size")) {
                String[] sizeArray = nextCookieItem.split("=");
                this.size = Integer.valueOf(sizeArray[1].trim());
            } else if (nextCookieItem.equals("httponly")) {
                this.httpOnly = true;
            } else if (nextCookieItem.equals("secure")) {
                this.secure = true;
            } else if (nextCookieItem.equals("samesite")) {
                String[] sameSiteArray = nextCookieItem.split("=");
                this.sameSite = sameSiteArray[1].trim();
            } else if (nextCookieItem.equals("sameparty")) {
                System.err.printf("%s error: %s\n", "sameparty", cookieText);
            } else if (nextCookieItem.equals("priority")) {
                System.err.printf("%s error: %s\n", "priority", cookieText);
            }
        }
    }

    // Thu, 07 Jul 2022 16:36:56 GMT
    public static Date parseGMT(String dateText) {
        if (EmptyValidate.isBlank(dateText)) {
            return null;
        }

        dateText = dateText.toLowerCase().trim();

        dateText = dateText.substring(5, 25).replace(" ", "/");
        dateText = dateText.replace("jan", "01");
        dateText = dateText.replace("feb", "02");
        dateText = dateText.replace("mar", "03");
        dateText = dateText.replace("apr", "04");
        dateText = dateText.replace("may", "05");
        dateText = dateText.replace("jun", "06");
        dateText = dateText.replace("jul", "07");
        dateText = dateText.replace("aug", "08");
        dateText = dateText.replace("sep", "09");
        dateText = dateText.replace("oct", "10");
        dateText = dateText.replace("nov", "11");
        dateText = dateText.replace("dec", "12");

        Date date = DateUtil.parseToDate(dateText, "dd/MM/yyyy/HH:mm:ss");
        return CalendarUtil.getAddHourDate(date, 8);
    }
}
