package com.union.yunzhi.factories.moudles.classfication.beans;

/**
 * Created by cjw on 2018/3/9 0009.
 */

public class CourseFatherFileBean {

//    public int chapterNum;
//    public int chapterId;
//    public int isFinished;//0表示学习完成，1表示为学习完成
    public String chapterName;

//    public CourseFatherFileBean(int chapterNum,int chapterId,int isFinished,String chapterName){
//
//        this.chapterName=chapterName;
//        this.chapterId=chapterId;
//        this.chapterNum=chapterNum;
//        this.isFinished=isFinished;
//    }
    public CourseFatherFileBean(String chapterName){

        this.chapterName=chapterName;
    }
}
