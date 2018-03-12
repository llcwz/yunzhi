package com.union.yunzhi.factories.moudles.classfication.beans;

/**
 * Created by cjw on 2018/2/22 0022.
 * 分类主页面课程显示bean类
 */

public class CourseShowBean {

    public String courseName;
    public String teacherName;
    public String courseCover;//课程图像
    public int good,commentNum;

    public CourseShowBean(String courseName, String teacherName, String portraitUrl, int good,  int commentNum){
        this.courseName=courseName;
        this.teacherName=teacherName;
        this.courseCover=portraitUrl;
        this.good=good;
        this.commentNum=commentNum;
    }


}
