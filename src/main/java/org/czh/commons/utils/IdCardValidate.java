package org.czh.commons.utils;

import lombok.extern.slf4j.Slf4j;
import org.czh.commons.annotations.tag.LengthTag;
import org.czh.commons.annotations.tag.NotBlankTag;
import org.czh.commons.annotations.tag.NotEmptyTag;
import org.czh.commons.constant.DateConstant;
import org.czh.commons.utils.date.AgeUtil;
import org.czh.commons.utils.date.CalendarUtil;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;
import org.czh.commons.validate.FlagAssert;
import org.czh.commons.validate.NumAssert;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : czh
 * description :
 * date : 2021-07-02
 * email 916419307@qq.com
 */
@Slf4j
public final class IdCardValidate {

    // 省份编码
    private static Set<String> PROVINCE_CODE_SET;
    // 加权因子
    private static int[] WEIGHTING_ARRAY;
    // 生肖
    private static String[] ZODIAC_ARRAY;
    // 天干
    private static String[] HEAVENLY_ARRAY;
    // 地支
    private static String[] TERRESTRIAL_ARRAY;

    // 省份编码
    public static Set<String> getProvinceCodeSet() {
        if (EmptyValidate.isNull(PROVINCE_CODE_SET)) {
            synchronized (IdCardValidate.class) {
                if (EmptyValidate.isNull(PROVINCE_CODE_SET)) {
                    PROVINCE_CODE_SET = new HashSet<>(35);
                    Collections.addAll(PROVINCE_CODE_SET,
                            "11", "12", "13", "14", "15",
                            "21", "22", "23",
                            "31", "32", "33", "34", "35", "36", "37",
                            "41", "42", "43", "44", "45", "46",
                            "50", "51", "52", "53", "54",
                            "61", "62", "63", "64", "65",
                            "71",
                            "81", "82",
                            "91");
                }
            }
        }
        return PROVINCE_CODE_SET;
    }

    // 加权因子
    public static int[] getWeightingArray() {
        if (EmptyValidate.isNull(WEIGHTING_ARRAY)) {
            synchronized (IdCardValidate.class) {
                if (EmptyValidate.isNull(WEIGHTING_ARRAY)) {
                    WEIGHTING_ARRAY = new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
                }
            }
        }
        return WEIGHTING_ARRAY;
    }

    public static String format(@NotBlankTag String idCard) {
        EmptyAssert.isNotBlank(idCard);
        return idCard.trim()
                .replaceAll(" ", "")
                .replaceAll("\n", "")
                .replaceAll("\t", "");
    }

    public static int getYear(@NotBlankTag String idCard) {
        idCard = format(idCard);
        FlagAssert.isTrue(isMainlandIdCard(idCard), "目前只支持15位和18位的大陆居民身份证");

        int year;
        if (idCard.length() == 15) {
            year = Integer.parseInt(idCard.substring(6, 8));
            year = Integer.parseInt(year <= 3 ? "20" + year : "19" + year);
        } else {
            year = Integer.parseInt(idCard.substring(6, 10));
        }
        return year;
    }

    public static String getBirthday(@NotBlankTag String idCard) {
        idCard = format(idCard);
        FlagAssert.isTrue(isMainlandIdCard(idCard), "目前只支持15位和18位的大陆居民身份证");

        if (idCard.length() == 15) {
            int year = Integer.parseInt(idCard.substring(6, 8));
            year = Integer.parseInt(year <= 3 ? "20" + year : "19" + year);
            return year + idCard.substring(6, 12);
        }
        return idCard.substring(6, 14);
    }

    public static int getAge(@NotBlankTag String idCard) {
        return AgeUtil.getCurrentAge(getBirthday(idCard));
    }

    public static String getGender(@NotBlankTag String idCard) {
        idCard = format(idCard);
        FlagAssert.isTrue(isMainlandIdCard(idCard), "目前只支持15位和18位的大陆居民身份证");

        String sex;
        if (idCard.length() == 15) {
            sex = idCard.substring(14);
        } else {
            sex = idCard.substring(16, 17);
        }
        if (Integer.parseInt(sex) % 2 == 0) {
            return "女";
        } else {
            return "男";
        }
    }

    public static boolean isMainlandIdCard(@NotBlankTag String idCard) {
        idCard = format(idCard);
        FlagAssert.isTrue(idCard.length() == 15 || idCard.length() == 18);

        if (idCard.length() == 15) {
            return is15IdCard(idCard);
        }
        return is18IdCard(idCard);
    }

    // 500222 860214 141
    public static boolean is15IdCard(@NotBlankTag String idCard) {
        idCard = format(idCard);
        FlagAssert.isTrue(idCard.length() == 15);

        String provinceCode = idCard.substring(0, 2);
        boolean flag = getProvinceCodeSet().contains(provinceCode);
        if (!flag) {
            return false;
        }

        try {
            int year = Integer.parseInt(idCard.substring(6, 8));
            if (year <= 3) {
                year += 2000;
            } else {
                year += 1900;
            }
            String birthdayText = year + idCard.substring(8, 12);
            DateUtil.parseToDate(birthdayText, DateConstant.DATE_SIMPLE());
        } catch (Exception e) {
            log.error("15位身份证号码校验出生日期出错", e);
            return false;
        }
        return true;
    }

    // 500222 19860214 1411
    public static boolean is18IdCard(@NotBlankTag String idCard) {
        idCard = format(idCard);
        FlagAssert.isTrue(idCard.length() == 18);

        String provinceCode = idCard.substring(0, 2);
        boolean flag = getProvinceCodeSet().contains(provinceCode);
        if (!flag) {
            return false;
        }

        try {
            String birthdayText = idCard.substring(6, 14);
            DateUtil.parseToDate(birthdayText, DateConstant.DATE_SIMPLE());
        } catch (Exception e) {
            log.error("18位身份证号码校验出生日期出错", e);
            return false;
        }

        String idCard17 = idCard.substring(0, 17);
        NumAssert.isPureNumber(idCard17, "18位身份证号码前17位必须为纯数字");
        return idCard.substring(17).equalsIgnoreCase(getCheckCode(idCard17.toCharArray()));
    }

    /*
      -----------------------------根据身份证号码计算生肖-------------------------------
     */

    // 特殊的大陆15位身份证转换为18位身份证
    // 百岁老人15位身份证转变为18位身份证
    // 类似于 1902年出生的老人，身份证上的出生日期为020101，需要补充为19020101
    public static String convertCentenarian15To18(@NotBlankTag @LengthTag(min = 15, max = 15) String idCard) {
        EmptyAssert.isNotBlank(idCard);
        idCard = format(idCard);
        FlagAssert.isTrue(idCard.length() == 15, "15位身份证号码长度不符合要求");
        NumAssert.isPureNumber(idCard, "15位身份证号码必须为纯数字");

        // 计算身份证前17位
        String idCard17 = idCard.substring(0, 6) + "19" + idCard.substring(6);
        // 通过身份证前17位，计算校验位
        return idCard17 + getCheckCode(idCard17.toCharArray());
    }

    // 大陆15位身份证转换为18位身份证
    public static String convert15To18(@NotBlankTag @LengthTag(min = 15, max = 15) String idCard) {
        EmptyAssert.isNotBlank(idCard);
        idCard = format(idCard);
        FlagAssert.isTrue(idCard.length() == 15, "15位身份证号码长度不符合要求");
        NumAssert.isPureNumber(idCard, "15位身份证号码必须为纯数字");

        // 2004年1月1日发行第二代18位居民身份证
        // 第一代身份证发行日期截止到2003年12月31日
        int year = Integer.parseInt(idCard.substring(6, 8));
        String chooseCentury = year <= 3 ? "20" : "19";
        // 计算身份证前17位
        String idCard17 = idCard.substring(0, 6) + chooseCentury + idCard.substring(6);
        // 通过身份证前17位，计算校验位
        return idCard17 + getCheckCode(idCard17.toCharArray());
    }

    private static String getCheckCode(@NotEmptyTag @LengthTag(min = 17, max = 17) char[] idCard17Chars) {
        EmptyAssert.isNotEmpty(idCard17Chars);
        FlagAssert.isTrue(idCard17Chars.length == 17);

        int weighting = 0;
        for (int i = 0; i < idCard17Chars.length; i++) {
            weighting += Integer.parseInt(String.valueOf(idCard17Chars[i])) * getWeightingArray()[i];
        }
        int mod = weighting % 11;
        return mod == 0 ? "1" : (mod == 1 ? "0" : (mod == 2 ? "X" : String.valueOf(12 - mod)));
    }

    /*
      -----------------------------根据身份证号码计算天干地支-------------------------------
     */

    // 生肖
    public static String[] getZodiacArray() {
        if (EmptyValidate.isNull(ZODIAC_ARRAY)) {
            synchronized (IdCardValidate.class) {
                if (EmptyValidate.isNull(ZODIAC_ARRAY)) {
                    ZODIAC_ARRAY = new String[]{"猪", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗"};
                }
            }
        }
        return ZODIAC_ARRAY;
    }

    public static String getZodiacByMainland(@NotBlankTag @LengthTag(min = 15, max = 18) String idCard) {
        int year = getYear(idCard);
        return getZodiacArray()[(year - 3) % 12];
    }

    // 天干
    public static String[] getHeavenlyArray() {
        if (EmptyValidate.isNull(HEAVENLY_ARRAY)) {
            synchronized (IdCardValidate.class) {
                if (EmptyValidate.isNull(HEAVENLY_ARRAY)) {
                    HEAVENLY_ARRAY = new String[]{"癸", "甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "任"};
                }
            }
        }
        return HEAVENLY_ARRAY;
    }

    // 地支
    public static String[] getTerrestrialArray() {
        if (EmptyValidate.isNull(TERRESTRIAL_ARRAY)) {
            synchronized (IdCardValidate.class) {
                if (EmptyValidate.isNull(TERRESTRIAL_ARRAY)) {
                    TERRESTRIAL_ARRAY = new String[]{"亥", "子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌"};
                }
            }
        }
        return TERRESTRIAL_ARRAY;
    }

    public static String getHeavenly(@NotBlankTag String idCard) {
        int year = getYear(idCard);
        return getHeavenlyArray()[(year - 3) % 10];
    }

    public static String getTerrestrial(@NotBlankTag String idCard) {
        int year = getYear(idCard);
        return getTerrestrialArray()[(year - 3) % 12];
    }

    public static String getChineseEraById(@NotBlankTag String idCard) {
        int year = getYear(idCard);
        return getHeavenlyArray()[(year - 3) % 10] + getTerrestrialArray()[(year - 3) % 12];
    }

    public static String getConstellation(@NotBlankTag String idCard) {
        String birthdayText = getBirthday(idCard);
        Date birthdayDate = DateUtil.parseToDate(birthdayText, DateConstant.DATE_SIMPLE());
        Calendar calendar = CalendarUtil.getCalendar(birthdayDate);
        int month = CalendarUtil.getMonth(calendar);
        int dayOfMonth = CalendarUtil.getDayOfMonth(calendar);

        if ((month == 1 && dayOfMonth >= 20) || (month == 2 && dayOfMonth <= 18)) {
            return "水瓶座";
        } else if (month == 2 || month == 3 && dayOfMonth <= 20) {
            return "双鱼座";
        } else if (month == 3 || month == 4 && dayOfMonth <= 19) {
            return "白羊座";
        } else if (month == 4 || month == 5 && dayOfMonth <= 20) {
            return "金牛座";
        } else if (month == 5 || month == 6 && dayOfMonth <= 21) {
            return "双子座";
        } else if (month == 6 || month == 7 && dayOfMonth <= 22) {
            return "巨蟹座";
        } else if (month == 7 || month == 8 && dayOfMonth <= 22) {
            return "狮子座";
        } else if (month == 8 || month == 9 && dayOfMonth <= 22) {
            return "处女座";
        } else if (month == 9 || month == 10 && dayOfMonth <= 23) {
            return "天秤座";
        } else if (month == 10 || month == 11 && dayOfMonth <= 22) {
            return "天蝎座";
        } else if (month == 11 || month == 12 && dayOfMonth <= 21) {
            return "射手座";
        } else {
            return "魔羯座";
        }
    }
}
