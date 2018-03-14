package com.union.yunzhi.factories.moudles.classfication.beans.classfication;

import com.union.yunzhi.factories.moudles.BaseModel;
import com.union.yunzhi.factories.moudles.classfication.beans.ClassBean;
import com.union.yunzhi.factories.moudles.classfication.beans.QuestionBean;

import java.util.List;

/**
 * Created by cjw on 2018/3/7 0007.
 */

public class BaseQuestionBean extends BaseModel {
    public int ecode;
    public String emsg;
    public List<QuestionBean> data;
}
