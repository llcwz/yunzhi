package com.union.yunzhi.factories.moudles.me;

import java.util.List;

/**
 * Created by CrazyGZ on 2018/3/3.
 */

public class MeModel {
    private PersonModel mPersonModel; // 个人账号
    private List<CourseModel> mCourseModels; // 我的课程
    private List<MessageModel> mMessageModels; // 我的消息
    private List<WorkModel> mWorkModels; // 任务
    private List<GradeModel> mGradeModels; // 成绩

    private int mViewType;

    public MeModel() {}

    public PersonModel getPersonModel() {
        return mPersonModel;
    }

    public void setPersonModel(PersonModel personModel) {
        mPersonModel = personModel;
    }

    public List<CourseModel> getCourseModels() {
        return mCourseModels;
    }

    public void setCourseModels(List<CourseModel> courseModels) {
        mCourseModels = courseModels;
    }

    public List<WorkModel> getWorkModels() {
        return mWorkModels;
    }

    public void setWorkModels(List<WorkModel> workModels) {
        mWorkModels = workModels;
    }

    public List<GradeModel> getGradeModels() {
        return mGradeModels;
    }

    public void setGradeModels(List<GradeModel> gradeModels) {
        mGradeModels = gradeModels;
    }

    public List<MessageModel> getMessageModels() {
        return mMessageModels;
    }

    public void setMessageModels(List<MessageModel> messageModels) {
        mMessageModels = messageModels;
    }

    public int getViewType() {
        return mViewType;
    }

    public void setViewType(int viewType) {
        mViewType = viewType;
    }
}
