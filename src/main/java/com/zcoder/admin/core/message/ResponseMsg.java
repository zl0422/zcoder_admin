package com.zcoder.admin.core.message;

/**
 * controller返回的消息体
 * 用于页面ajax请求
 * Created by lin on 2016-05-19.
 */
public class ResponseMsg<T> {

    private String rtnCode;

    private T data;

    public ResponseMsg(T data) {
        this.data = data;
        this.rtnCode = ResponseMsgStatus.OK;
    }

    public ResponseMsg(T data, String rtnCode) {
        this.data = data;
        this.rtnCode = rtnCode;
    }

    public String getRtnCode() {
        return rtnCode;
    }

    public void setRtnCode(String rtnCode) {
        this.rtnCode = rtnCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseMsg{" +
                "rtnCode='" + rtnCode + '\'' +
                ", data=" + data +
                '}';
    }

   public class ResponseMsgStatus{
        public final static String OK = "ok";
        public final static String ERROR = "error";
    }


}
