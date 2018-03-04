package com.union.yunzhi.factories.moudles.me;

/**
 * Created by CrazyGZ on 2018/3/3.
 */

public class MeModel {
    private PersonModel mPersonModel;
    private NavigationModel mNavigationModel;
    private CourseModel mCourseModel;

    private int mViewType;

    // 测试课程数据
    public MeModel(CourseModel courseModel, int viewType) {
        mCourseModel = courseModel;
        mViewType = viewType;
    }

    public PersonModel getPersonModel() {
        return mPersonModel;
    }

    public void setPersonModel(PersonModel personModel) {
        mPersonModel = personModel;
    }

    public NavigationModel getNavigationModel() {
        return mNavigationModel;
    }

    public void setNavigationModel(NavigationModel navigationModel) {
        mNavigationModel = navigationModel;
    }

    public CourseModel getCourseModel() {
        return mCourseModel;
    }

    public void setCourseModel(CourseModel courseModel) {
        mCourseModel = courseModel;
    }

    public int getViewType() {
        return mViewType;
    }

    public void setViewType(int viewType) {
        mViewType = viewType;
    }
}
