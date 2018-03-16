package com.union.yunzhi.factories.moudles.classfication.beans.details;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cjw on 2018/3/7 0007.
 */

public class DetailsBean {

    public String introImgUrl;
    public String introVideoUrl;
    public int allElementNum;//课程总章数
    public int finishNum;//已经更新到的章数字
    public String academicName;
    public String academicId;
    public String courseName;
    public String courseInfo;
    public String teacherInfo;
    public TeacherBean thisTeacher;
    public List<TeacherBean> moreTeachers;

    public DetailsBean(){
        thisTeacher=new TeacherBean();
        moreTeachers=new ArrayList<>();
    }
}
