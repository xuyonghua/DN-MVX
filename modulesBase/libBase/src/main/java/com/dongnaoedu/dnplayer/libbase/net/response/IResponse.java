package com.dongnaoedu.dnplayer.libbase.net.response;

/**
 * 具体的返回的类型数据不知道：
 * 通过接口的形式来转换兼容
 * @param <T>
 */
public interface IResponse<T> {

    T getData();

    String getMsg();

    String getCode();

    boolean isSuccess();

}
