package com.union.yunzhi.factories.moudles.classfication.beans.details;

/**
 * Created by cjw on 2018/3/5 0005.
 */

public class CourseBean {

    public String courseid;
    public String coursename;
    public String coursecover;

    public CourseBean(String coursename,String coursecover,String courseid){
        this.coursename=coursename;
        this.courseid=courseid;
        this.coursecover=coursecover;
    }
    public CourseBean(){

    }
}
