package com.hzx.conc.common.result;

import com.hzx.conc.common.enumeration.ResponseCode;
import io.swagger.annotations.ApiModelProperty;

import java.util.Collections;

/**
 * 后端统一返回结果
 *
 * @param <T>
 */
public class Result<T> {

    @ApiModelProperty("返回码")
    private int code;

    @ApiModelProperty("返回消息")
    private String msg;

    @ApiModelProperty("返回数据")
    private T data = (T) "";

    @ApiModelProperty("是否成功")
    private Boolean flag;


    public Result(int code, String msg, T data, Boolean flag) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.flag = flag;
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result() {
    }

    public static <T> Result<T> instance(int code, String msg) {
        return new Result<>(code, msg);
    }

    public static <T> Result<T> instance(int code, String msg, T data, Boolean flag) {
        return new Result<>(code, msg, data, flag);
    }

    public static <T> Result<T> success() {
        return new Result<>(ResponseCode.success.getCode(), ResponseCode.success.getMsg());
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(ResponseCode.success.getCode(), ResponseCode.success.getMsg(), data, true);
    }

    public static <T> Result<T> error() {
        return new Result<>(ResponseCode.error.getCode(), ResponseCode.error.getMsg());
    }

    public static <T> Result<T> error(String msg) {
        return new Result(ResponseCode.error.getCode(), msg, Collections.EMPTY_LIST, false);
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", flag=" + flag +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public boolean isSuccess() {
        return this.code == ResponseCode.success.getCode();
    }
}
