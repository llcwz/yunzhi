package com.union.yunzhi.factories.moudles.communication;

import com.union.yunzhi.factories.moudles.BaseModel;
import com.union.yunzhi.factories.moudles.me.MeModel;

import java.util.List;

/**
 * Created by CrazyGZ on 2018/3/7.
 */

public class BaseCommunicationModel extends BaseModel {
    public int ecode;
    public String emsg;
    public List<PostModel> data;
}
