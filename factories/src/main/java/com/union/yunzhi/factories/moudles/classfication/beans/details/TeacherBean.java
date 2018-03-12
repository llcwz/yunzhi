package com.union.yunzhi.factories.moudles.classfication.beans.details;

/**
 * Created by cjw on 2018/2/28 0028.
 * 分类点击某个课程进入详情页面
 * 显示相关老师的itembean类
 */

public class TeacherBean {

    //父项目内容
    public String photoUrl;
    public String teacherName;
    public String teacherState;
    public int good;

    public CourseBean course1;
    public CourseBean course2;
    public CourseBean course3;

    public String teacherInfo;

    public TeacherBean(){
        course1=new CourseBean();
        course2=new CourseBean();
        course3=new CourseBean();
    }

}
