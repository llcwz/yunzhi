package com.union.yunzhi.factories.moudles.me;

/**
 * Created by CrazyGZ on 2018/3/6.
 * 这个是每个课程下的单元测试的成绩
 */

public class UnitGradeModel {
    private int mId;
    private String mName; // 单元名字
    private String mGrade; // 单元成绩

    public UnitGradeModel() {}

    public UnitGradeModel(int id, String name, String grade) {
        mId = id;
        mName = name;
        mGrade = grade;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getGrade() {
        return mGrade;
    }

    public void setGrade(String grade) {
        mGrade = grade;
    }


}
