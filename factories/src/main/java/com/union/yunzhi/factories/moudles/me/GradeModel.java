package com.union.yunzhi.factories.moudles.me;

import java.util.List;

/**
 * Created by CrazyGZ on 2018/3/6.
 * 作为成绩的一级列表，一级列表显示的是期末成绩
 */

public class GradeModel {
    private String mCourse; // 课程名称
    private String mGrade; // 期末成绩
    private List<UnitGradeModel> mUnitGradeModels; // 各个单元的成绩


    public GradeModel() {}

    public GradeModel(String course, String grade, List<UnitGradeModel> unitGradeModels) {
        mCourse = course;
        mGrade = grade;
        mUnitGradeModels = unitGradeModels;
    }



    public String getCourse() {
        return mCourse;
    }

    public void setCourse(String course) {
        mCourse = course;
    }

    public String getGrade() {
        return mGrade;
    }

    public void setGrade(String grade) {
        mGrade = grade;
    }

    public List<UnitGradeModel> getUnitGradeModels() {
        return mUnitGradeModels;
    }

    public void setUnitGradeModels(List<UnitGradeModel> unitGradeModels) {
        mUnitGradeModels = unitGradeModels;
    }
}
