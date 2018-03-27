package com.union.yunzhi.factories.okhttp.response;

/**
 * Created by meng on 2018/3/14.
 */

public class NotCallBackData {

    /**
     * 请求成功但是不需要返回数据
     */
    /**
     * the server return code
     */
    private int ecode;

    /**
     * the server return success message
     */
    private Object emsg;

    public NotCallBackData() {}

    public NotCallBackData(int ecode, Object emsg) {
        this.ecode = ecode;
        this.emsg = emsg;
    }

    public int getEcode() {
        return ecode;
    }

    public Object getEmsg() {
        return emsg;
    }
}
