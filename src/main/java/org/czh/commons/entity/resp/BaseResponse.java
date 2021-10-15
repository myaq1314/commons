package org.czh.commons.entity.resp;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.czh.commons.exceptions.CommonStatusDict;
import org.czh.commons_core.parent.entity.IBaseEntity;
import org.czh.commons_core.parent.enums.IDictEnum;

import javax.xml.bind.annotation.XmlTransient;

/**
 * @author : czh
 * description :
 * date : 2021-04-30
 * email 916419307@qq.com
 */
@Data
@XmlTransient
@ToString
@EqualsAndHashCode
@ApiModel(value = "基本响应外壳实体")
@SuppressWarnings("unused")
public final class BaseResponse<T> implements IBaseEntity {

    private static final long serialVersionUID = -8479527317902292721L;

    private Integer code;

    private String msg;

    private T data;

    private BaseResponse(final Integer code, final String msg, final T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 构建 成功 响应体
     *
     * @param <T> 数据类型
     * @return 成功响应体
     */
    public static <T> BaseResponse<T> newSuccessResp() {
        return newSuccessResp(null);
    }

    /**
     * 构建 成功 响应体
     *
     * @param data 响应数据
     * @param <T>  数据类型
     * @return 成功响应体
     */
    public static <T> BaseResponse<T> newSuccessResp(final T data) {
        return newSuccessResp(CommonStatusDict.SUCCESS.value, data);
    }

    /**
     * 构建 成功 响应体
     *
     * @param msg  响应文本
     * @param data 响应数据
     * @param <T>  数据类型
     * @return 成功响应体
     */
    public static <T> BaseResponse<T> newSuccessResp(final String msg, final T data) {
        return new BaseResponse<>(CommonStatusDict.SUCCESS.key, msg, data);
    }

    /**
     * 构建 失败 响应体
     *
     * @param <T> 数据类型
     * @return 失败响应体
     */
    public static <T> BaseResponse<T> newFailResp() {
        return newFailResp(CommonStatusDict.FAIL);
    }

    /**
     * 构建 失败 响应体
     *
     * @param dictEnum 异常枚举
     * @param <T>      数据类型
     * @return 失败响应体
     */
    public static <T> BaseResponse<T> newFailResp(final IDictEnum<Integer, String> dictEnum) {
        return newFailResp(dictEnum, null);
    }

    /**
     * 构建 失败 响应体
     *
     * @param dictEnum 异常枚举
     * @param data     异常数据
     * @param <T>      数据类型
     * @return 失败响应体
     */
    public static <T> BaseResponse<T> newFailResp(final IDictEnum<Integer, String> dictEnum, final T data) {
        return newFailResp(dictEnum.getKey(), dictEnum.getValue(), data);
    }

    /**
     * 构建 失败 响应体
     *
     * @param e   捕获的异常
     * @param <T> 数据类型
     * @return 失败响应体
     */
    public static <T> BaseResponse<T> newFailResp(final Exception e) {
        return newFailResp(CommonStatusDict.FAIL.key, e.getMessage());
    }

    /**
     * 构建 失败 响应体
     *
     * @param msg 异常文本
     * @param <T> 数据类型
     * @return 失败响应体
     */
    public static <T> BaseResponse<T> newFailResp(final String msg) {
        return newFailResp(msg, null);
    }

    /**
     * 构建 失败 响应体
     *
     * @param msg  异常文本
     * @param data 异常数据
     * @param <T>  数据类型
     * @return 失败响应体
     */
    public static <T> BaseResponse<T> newFailResp(final String msg, final T data) {
        return newFailResp(CommonStatusDict.FAIL.key, msg, data);
    }

    /**
     * 构建 失败 响应体
     *
     * @param code 异常编码
     * @param msg  异常文本
     * @param <T>  数据类型
     * @return 失败响应体
     */
    public static <T> BaseResponse<T> newFailResp(final Integer code, final String msg) {
        return newFailResp(code, msg, null);
    }

    /**
     * 构建 失败 响应体
     *
     * @param code 异常编码
     * @param msg  异常文本
     * @param data 异常数据
     * @param <T>  数据类型
     * @return 失败响应体
     */
    public static <T> BaseResponse<T> newFailResp(final Integer code, final String msg, final T data) {
        return new BaseResponse<>(code, msg, data);
    }
}
