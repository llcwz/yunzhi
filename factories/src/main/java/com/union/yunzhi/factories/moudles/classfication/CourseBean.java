package com.union.yunzhi.factories.moudles.classfication;

/**
 * Created by cjw on 2018/2/22 0022.
 */

public class CourseBean {

    public String courseName;
    public String teacherName;
    public String portraitUrl;//课程图像
    public int good,bad,commentNum;

    public CourseBean(String courseName,String teacherName,String portraitUrl,int good,int bad,int commentNum){
        this.courseName=courseName;
        this.teacherName=teacherName;
        this.portraitUrl=portraitUrl;
        this.good=good;
        this.bad=bad;
        this.commentNum=commentNum;
    }


}
