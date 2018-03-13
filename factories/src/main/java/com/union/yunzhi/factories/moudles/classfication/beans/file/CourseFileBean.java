package com.union.yunzhi.factories.moudles.classfication.beans.file;

import java.util.List;

/**
 * Created by cjw on 2018/3/8 0008.
 */

public class CourseFileBean {

    public CourseFatherFileBean tittleParent;

    public List<CourseSonFileBean> tittleSons;

    public CourseFileBean(CourseFatherFileBean father,List<CourseSonFileBean> sons){

        this.tittleParent =father;
        this.tittleSons =sons;
    }

}
