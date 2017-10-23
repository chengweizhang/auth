package com.zcw.auth.web.controller.common;


/**
 * Created by zhangsan on 2015/8/16.
 */
public class Result {

    private Boolean success;

    private String msg;

    /**
     * 错误码
     */
    private Integer errCode = 0;

    private Object data;

    private Long total;

    public Result(boolean isSuccess) {
        this.success = isSuccess;
    }

    public Result() {
    }

    public Result(Exception exception) {
        setSuccess(false);

        this.msg = exception.getMessage();
    }

    public Result(Object data) {
        setSuccess(true);
        this.data = data;
    }

    public Result(String msg, boolean success) {
        setSuccess(success);
        this.msg = msg;
    }


    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
        if (!Boolean.TRUE.equals(success)) {
            this.errCode = 11000;
        }
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public Result setErrCode(Integer errCode) {
        this.errCode = errCode;
        return this;
    }

    public Long getTotal() {
        return total;
    }

    public Result setTotal(Long total) {
        this.total = total;
        return this;
    }
}
