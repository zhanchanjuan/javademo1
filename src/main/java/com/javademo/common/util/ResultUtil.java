package com.javademo.common.util;

public class ResultUtil<T> {

    /**
     * 成功
     *
     * @param code 返回码
     * @param msg  返回消息
     * @param obj  返回自定义数据
     * @return
     */
    public static <T> Result<T> success(Integer code, String msg, T obj) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(obj);
        return result;
    }
    public static <T> Result<T> success2(Integer code, String msg, T obj,int count) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(obj);
        result.setCount(count);
        return result;
    }

    public static <T> Result<T> success(String msg, T obj) {
        return success(ResultEnum.SUCCESS.getCode(), msg, obj);
    }
    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T obj) {
        return success(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), obj);
    }

    /**
     * 失败
     *
     * @param code 返回码
     * @param msg  返回消息
     * @param obj  返回自定义数据（异常详情）
     * @return
     */
    public static <T> Result<T> error(Integer code, String msg, T obj) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(obj);
        return result;
    }

    public static <T> Result<T> error(String msg, T obj) {
        return error(ResultEnum.SYSTEM_ERROR.getCode(), msg, obj);
    }

    public static Result error(String msg) {
        return error(ResultEnum.SYSTEM_ERROR.getCode(), msg, null);
    }

    public static Result error() {
        return error(ResultEnum.SYSTEM_ERROR.getMsg());
    }

}
