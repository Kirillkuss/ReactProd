package com.itrail.react.reactprod.responses;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

public class BaseResponse<T> {

    private int code;
    private String message = "System mallfunction";
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public BaseResponse() {
    }

    public BaseResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static BaseResponse success(){
        return new BaseResponse(0, "success" );
    }

    public static BaseResponse error( int code, Throwable e ){
        return new BaseResponse( 0 , null == e.getMessage() ? "System mullfunction" : e.getMessage() );
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

    public <T> T getData() {
        return (T)data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseResponse)) return false;
        BaseResponse<?> that = (BaseResponse<?>) o;
        return getCode() == that.getCode() && getMessage().equals(that.getMessage()) && getData().equals(that.getData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getMessage(), getData());
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
