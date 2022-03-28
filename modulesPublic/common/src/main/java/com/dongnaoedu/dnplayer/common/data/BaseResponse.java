package com.dongnaoedu.dnplayer.common.data;

import com.dongnaoedu.dnplayer.libbase.net.response.IResponse;

/**
 * 最外层返回数据的包装类
 *
 * @param <T>
 */
public class BaseResponse<T> implements IResponse<T> {

    private int code;
    private T data;
    private String msg;

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String getCode() {
        return String.valueOf(code);
    }

    @Override
    public boolean isSuccess() {
        return code == 200;
    }
}
