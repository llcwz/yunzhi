package com.union.yunzhi.factories.moudles.classfication.beans;

import com.union.yunzhi.factories.moudles.BaseModel;

import java.util.List;

/**
 * Created by 62588 on 2018/3/9.
 * 课程考核题目
 */

public class TestContentBean extends BaseModel{
    public String title;//题目
    public String questionId;//所属题库Id
    public List<String>  chooses;//选项
    public String imgurl;//是否有图片，如果没有为null

    public TestContentBean(String title, List<String> chooses) {
        this.title = title;
        this.chooses = chooses;
    }
}
