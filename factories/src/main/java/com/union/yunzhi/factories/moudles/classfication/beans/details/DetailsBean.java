package com.union.yunzhi.factories.moudles.classfication.beans.details;

import com.union.yunzhi.factories.moudles.BaseModel;

import java.util.List;

/**
 * Created by cjw on 2018/3/7 0007.
 */

public class DetailsBean extends BaseModel{

    public String introimgurl;
    public String introvideourl;
    public int videoid;
    //public int allElementNum;//课程总章数
    //public int finishNum;//已经更新到的章数字
    //public String academicName;
    //public String academicId;
    public String coursename;
    public String courseinfo;
    public String teacherinfo;
    public String teachername;
    //public TeacherBean thisTeacher;
    public List<String> coursecover;
    public List<TeacherBean> teacher;

}
