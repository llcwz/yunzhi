package com.union.yunzhi.factories.moudles.classfication.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cjw on 2018/2/19 0019.
 * 分类侧滑页面课程显示item的bean类
 */

public class TitleBean {

    private List<String> sonTitle=new ArrayList<>();//课程名称集合
    private List<String> sonCode=new ArrayList<>();//课程代码集合
    private String title;//学院名称

    public TitleBean(String title,List<String> sonTitle,List<String> sonCode){
        this.title=title;
        this.sonTitle=sonTitle;
        this.sonCode=sonCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getSonTitle() {
        return sonTitle;
    }

    public void setSonTitle(List<String> sonTitle) {
        this.sonTitle = sonTitle;
    }


}
