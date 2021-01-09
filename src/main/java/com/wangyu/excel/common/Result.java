package com.wangyu.excel.common;

/**
 * @author hs
 * @version 1.0
 * @date 2019/1/16 15:18
 */
public class Result {
    private Object data;
    private int code;
    private String message;

    public Result(Object data, int code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public Result() {
        this.code = Status.SUCCESS.getCode();
        this.message = Status.SUCCESS.getMessage();

    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Result ofMessage(int code, String message) {
        return new Result(null, code, message);
    }

    public static Result ofSuccess(Object data) {
        return new Result(data, Status.SUCCESS.getCode(), Status.SUCCESS.getMessage());
    }

    public static Result error(Object data) {
        return new Result(data, Status.INTERNAL_SERVER_ERROR.getCode(), Status.INTERNAL_SERVER_ERROR.getMessage());
    }

    public static Result forbidden(Object data) {
        return new Result(data, Status.FORBIDDEN.getCode(), Status.FORBIDDEN.getMessage());
    }

    public static Result exists(Object data){
        return new Result(data, Status.DATA_EXISTS.getCode(), Status.DATA_EXISTS.getMessage());
    }

    public static Result tmp(Object data, int code, String message){
        return new Result(data, code, message);
    }



    /**
     * 内部枚举类，列举常用请求返回状态码
     */
    public enum Status {
        /**
         * SUCCESS 请求成功
         * BAD_REQUEST 请求无效
         * FORBIDDEN 无权限访问
         * INTERNAL_SERVER_ERROR 未知服务器错误
         * LOGIN_ERROR 登录失败(用户名或密码不正确)
         * LOGIN_SUCCESS 登录成功
         * DATA_EXISTS 数据已存在（保存前在数据库查询）
         */
        SUCCESS(200, "请求成功"),
        BAD_REQUEST(400, "请求无效"),
        FORBIDDEN(403, "无权限访问"),
        INTERNAL_SERVER_ERROR(500, "未知服务器错误"),
        LOGIN_FAIL(415,"用户名或密码不正确"),
        LOGIN_SUCCESS(200,"登录成功"),
        DATA_EXISTS(501,"数据已存在");

        private int code;
        private String message;

        Status(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    @Override
    public String toString() {
        return "Result{" +
                "data=" + data +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
