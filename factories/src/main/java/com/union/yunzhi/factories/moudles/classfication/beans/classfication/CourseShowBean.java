package com.union.yunzhi.factories.moudles.classfication.beans.classfication;

/**
 * Created by cjw on 2018/2/22 0022.
 * 分类主页面课程显示bean类
 */

public class CourseShowBean {

    public String coursename;
    public String teachername;
    public String coursecover;//课程图像
    public int good,commentnum;

    public CourseShowBean(String coursename, String teachername, String portraiturl, int good,  int commentnum){
            this.coursename=coursename;
        this.teachername=teachername;
        this.coursecover=portraiturl;
        this.good=good;
        this.commentnum=commentnum;
    }


}
