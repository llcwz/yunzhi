package com.union.yunzhi.factories.moudles.classfication.beans.drawer;

import com.union.yunzhi.factories.moudles.classfication.beans.details.CourseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cjw on 2018/2/19 0019.
 * 分类侧滑页面课程显示item的bean类
 */

public class DrawerBean {

    public String academicid; //学院Id
    public String academicname;//学院名称
    public List<CourseBean> course;//课程集合

    public DrawerBean(List<CourseBean> course, String academicname, String academicid){
        course=new ArrayList<>();
        this.course=course;
        this.academicname = academicname;
        this.academicid = academicid;
    }

}
