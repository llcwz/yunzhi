package com.union.yunzhi.factories.moudles.classfication.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cjw on 2018/2/19 0019.
 * 分类侧滑页面课程显示item的bean类
 */

public class TitleBean {

    private List<String> courseName =new ArrayList<>();//课程名称集合
    private List<String> courseCode =new ArrayList<>();//课程代码集合
    private String academicName;//学院名称

    public TitleBean(String academicName, List<String> courseName, List<String> courseCode){
        this.academicName=academicName;
        this.courseName = courseName;
        this.courseCode = courseCode;
    }

    public List<String> getCourseName() {
        return courseName;
    }

    public void setCourseName(List<String> courseName) {
        this.courseName = courseName;
    }

    public List<String> getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(List<String> courseCode) {
        this.courseCode = courseCode;
    }

    public String getAcademicName() {
        return academicName;
    }

    public void setAcademicName(String academicName) {
        this.academicName = academicName;
    }
}
