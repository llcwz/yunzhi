package com.union.yunzhi.factories.moudles.classfication.beans;

/**
 * Created by cjw on 2018/3/9 0009.
 */

public class CourseSonFileBean {
    public int sonChapterId;
    public int sonChapterNum;
    public int isLoad;//0表示已经下载 表示未下载
    public int isFinish;
    public String sonChapterName;

        public CourseSonFileBean(int sonChapterId, int sonChapterNum,int isLoad,int isFinish,String sonChapterName){
        this.sonChapterId=sonChapterId;
        this.sonChapterNum=sonChapterNum;
        this.isLoad=isLoad;
        this.isFinish=isFinish;
        this.sonChapterName=sonChapterName;
    }

}
