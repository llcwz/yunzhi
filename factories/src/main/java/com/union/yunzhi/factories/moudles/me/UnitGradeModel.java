package com.union.yunzhi.factories.moudles.me;

/**
 * Created by CrazyGZ on 2018/3/6.
 * 这个是每个课程下的单元测试的成绩
 */

public class UnitGradeModel {
    private String mName; // 单元名字
    private String mGrade; // 单元成绩

    public UnitGradeModel() {}

    public UnitGradeModel(String name, String grade) {
        mName = name;
        mGrade = grade;
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
