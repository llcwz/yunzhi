package com.union.yunzhi.factories.moudles.me;

/**
 * Created by CrazyGZ on 2018/3/3.
 */

public class MeModel {
    private PersonModel mPersonModel;
    private CourseModel mCourseModel;
    private NavigationModel mNavigationModel;
    private WorkModel mWorkModel;
    private GradeModel mGradeModel;

    private int mViewType;

    public MeModel() {}

    // 测试个人数据
    public MeModel(PersonModel personModel) {
        mPersonModel = personModel;
    }

    // 测试课程数据
    public MeModel(CourseModel courseModel, int viewType) {
        mCourseModel = courseModel;
        mViewType = viewType;
    }

    // 测试导航数据
    public MeModel(NavigationModel navigationModel) {
        mNavigationModel = navigationModel;
    }

    // 测试任务数据
    public MeModel(WorkModel workModel, int viewType) {
        mWorkModel = workModel;
        mViewType = viewType;
    }

    public MeModel(WorkModel workModel) {
        mWorkModel = workModel;
    }

    // 测试成绩数据
    public MeModel(GradeModel gradeModel) {
        mGradeModel = gradeModel;
    }

    public PersonModel getPersonModel() {
        return mPersonModel;
    }

    public void setPersonModel(PersonModel personModel) {
        mPersonModel = personModel;
    }

    public CourseModel getCourseModel() {
        return mCourseModel;
    }

    public void setCourseModel(CourseModel courseModel) {
        mCourseModel = courseModel;
    }

    public NavigationModel getNavigationModel() {
        return mNavigationModel;
    }

    public void setNavigationModel(NavigationModel navigationModel) {
        mNavigationModel = navigationModel;
    }

    public WorkModel getWorkModel() {
        return mWorkModel;
    }

    public void setWorkModel(WorkModel workModel) {
        mWorkModel = workModel;
    }

    public GradeModel getGradeModel() {
        return mGradeModel;
    }

    public void setGradeModel(GradeModel gradeModel) {
        mGradeModel = gradeModel;
    }

    public int getViewType() {
        return mViewType;
    }

    public void setViewType(int viewType) {
        mViewType = viewType;
    }
}
