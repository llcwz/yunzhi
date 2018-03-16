package com.union.yunzhi.factories.moudles.classfication.beans.details;

import com.union.yunzhi.factories.moudles.BaseModel;

import java.util.List;

/**
 * Created by cjw on 2018/2/28 0028.
 * 分类点击某个课程进入详情页面
 * 显示相关老师的itembean类
 */

public class TeacherBean extends BaseModel{

    //父项目内容
    public String photourl;
    public String teachername;
    public String teacherstate;
    //public int good;

    public List<String> coursecover;

//    public CourseBean course1;
//    public CourseBean course2;
//    public CourseBean course3;


    public String teacherinfo;


}
