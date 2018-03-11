package com.union.yunzhi.factories.moudles.classfication.beans;

import java.util.List;

/**
 * Created by cjw on 2018/3/8 0008.
 */

public class CourseFileBean {

    public CourseFatherFileBean father;

    public List<CourseSonFileBean> sons;

    public CourseFileBean(CourseFatherFileBean father,List<CourseSonFileBean> sons){

        this.father=father;
        this.sons=sons;
    }

}
