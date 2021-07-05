package org.czh.commons.exceptions;

import lombok.Getter;
import org.czh.commons.enums.parent.IDictEnum;

/**
 * @author : czh
 * description : 通用异常类
 * date : 2021-04-28
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public class CommonException extends RuntimeException {

    private static final long serialVersionUID = 8294692408624714772L;

    @Getter
    protected final Integer code;

    /**
     * 空参
     */
    public CommonException() {
        this(CommonStatusDict.FAIL);
    }

    /**
     * 一参
     */
    public CommonException(Integer code) {
        this(code, CommonStatusDict.FAIL.value);
    }

    public CommonException(String message) {
        this(CommonStatusDict.FAIL.key, message);
    }

    public CommonException(Throwable cause) {
        this(CommonStatusDict.FAIL, cause);
    }

    public CommonException(IDictEnum<Integer, String> dictEnum) {
        this(dictEnum.getKey(), dictEnum.getValue());
    }

    /**
     * 双参
     */
    public CommonException(Integer code, Throwable cause) {
        this(code, cause.getMessage(), cause);
    }

    public CommonException(String message, Throwable cause) {
        this(CommonStatusDict.FAIL.key, message, cause);
    }

    public CommonException(IDictEnum<Integer, String> dictEnum,
                           Throwable cause) {
        this(dictEnum.getKey(), dictEnum.getValue(), cause);
    }

    public CommonException(Integer code, IDictEnum<Integer, String> dictEnum) {
        this(code, dictEnum.getValue());
    }

    public CommonException(IDictEnum<Integer, String> dictEnum, String message) {
        this(dictEnum.getKey(), message);
    }

    public CommonException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 三参
     */
    public CommonException(Integer code, IDictEnum<Integer, String> dictEnum, Throwable cause) {
        this(code, dictEnum.getValue(), cause);
    }

    public CommonException(IDictEnum<Integer, String> dictEnum, String message, Throwable cause) {
        this(dictEnum.getKey(), message, cause);
    }

    public CommonException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    /**
     * 多参
     */
    public CommonException(Integer code,
                           Throwable cause,
                           boolean enableSuppression,
                           boolean writableStackTrace) {
        this(code, cause.getMessage(), cause, enableSuppression, writableStackTrace);
    }

    public CommonException(Integer code,
                           IDictEnum<Integer, String> dictEnum,
                           Throwable cause,
                           boolean enableSuppression,
                           boolean writableStackTrace) {
        this(code, dictEnum.getValue(), cause, enableSuppression, writableStackTrace);
    }

    public CommonException(IDictEnum<Integer, String> dictEnum,
                           String message,
                           Throwable cause,
                           boolean enableSuppression,
                           boolean writableStackTrace) {
        this(dictEnum.getKey(), message, cause, enableSuppression, writableStackTrace);
    }

    public CommonException(IDictEnum<Integer, String> dictEnum,
                           Throwable cause,
                           boolean enableSuppression,
                           boolean writableStackTrace) {
        this(dictEnum.getKey(), dictEnum.getValue(), cause, enableSuppression, writableStackTrace);
    }

    public CommonException(Integer code,
                           String message,
                           Throwable cause,
                           boolean enableSuppression,
                           boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }
}
