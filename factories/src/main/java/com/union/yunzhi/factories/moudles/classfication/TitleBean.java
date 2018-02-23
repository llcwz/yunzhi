package com.union.yunzhi.factories.moudles.classfication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cjw on 2018/2/19 0019.
 */

public class TitleBean {

    private String title;
    private List<String> sonTitle=new ArrayList<>();

    public TitleBean(String title,List<String> sonTitle){
        this.title=title;
        this.sonTitle=sonTitle;
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
