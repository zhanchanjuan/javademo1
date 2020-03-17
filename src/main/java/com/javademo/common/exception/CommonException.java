package com.javademo.common.exception;


import com.javademo.common.util.ResultEnum;
import lombok.Data;


/**
 * @author
 * @date 2019/12/10
 * 自定义网关异常
 */
@Data
public class CommonException extends RuntimeException {

    private static final long serialVersionUID = -6332552389020848385L;

    private int code;

    private String msg;

    public CommonException() {
        super(ResultEnum.SYSTEM_ERROR.getMsg());
        this.code = ResultEnum.SYSTEM_ERROR.getCode();
        this.msg = ResultEnum.SYSTEM_ERROR.getMsg();
    }

    public CommonException(String msg) {
        super(msg);
        this.code = ResultEnum.SYSTEM_ERROR.getCode();
        this.msg = msg;
    }

    public CommonException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

}
