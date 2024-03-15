package com.entity.result;

import lombok.Data;

@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    private Result() {}

    private static<T> Result<T> resp(ResultCode resultCode) {
        Result result = new Result();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        return result;
    }

    public static<T> Result<T> resp(ResultCode resultCode, String message) {
        Result result = new Result();
        result.setCode(resultCode.getCode());
        result.setMessage(message);
        return result;
    }

    public static<T> Result<T> resp(ResultCode resultCode, T data) {
        Result result = new Result();
        result.setCode(resultCode.getCode());
        result.setData(data);
        return result;
    }

    public static<T> Result<T> success() {

        return Result.resp(ResultCode.SUCCESS);
    }
}
