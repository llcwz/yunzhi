package com.union.yunzhi.factories.moudles.classfication.beans;

import com.union.yunzhi.factories.moudles.BaseModel;

/**
 * Created by 62588 on 2018/3/9.
 */

public class CourseTestBean extends BaseModel{
    public String name;//测试名称
    public String data;//结束时间
    public int score;//总分
    public String chapter;//属于第几章的测试

    public int viewType;

    public CourseTestBean(String chapter, int viewType) {
        this.chapter = chapter;
        this.viewType = viewType;
    }

    public CourseTestBean(String name, String data, int score, String chapter, int viewType) {
        this.name = name;
        this.data = data;
        this.score = score;
        this.chapter = chapter;
        this.viewType = viewType;
    }
}
