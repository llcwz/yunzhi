package com.union.yunzhi.factories.moudles.me;

import android.graphics.drawable.Drawable;

/**
 * Created by CrazyGZ on 2018/3/3.
 */

public class CourseModel {
    private String mId;
    private String mIcon; // 课程缩略图
    private String mName; // 课程名称
    private String mSchool; // 属于哪个学校的
    private String mCollege; // 开设课程的学院
    private String mTeacher; // 课程的教师
    private int mSchedule; // 课程的总进度安排
    private int mProgress; // 课程进度
    private int mState; // 课程的状态
    private int mViewType; // 类型

    public CourseModel() {}

    public CourseModel(String id, String icon, String name, String school, String college, String teacher, int schedule, int progress, int state, int viewType) {
        mId = id;
        mIcon = icon;
        mName = name;
        mSchool = school;
        mCollege = college;
        mTeacher = teacher;
        mSchedule = schedule;
        mProgress = progress;
        mState = state;
        mViewType = viewType;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSchool() {
        return mSchool;
    }

    public void setSchool(String school) {
        mSchool = school;
    }

    public String getCollege() {
        return mCollege;
    }

    public void setCollege(String college) {
        mCollege = college;
    }

    public String getTeacher() {
        return mTeacher;
    }

    public void setTeacher(String teacher) {
        mTeacher = teacher;
    }

    public int getSchedule() {
        return mSchedule;
    }

    public void setSchedule(int schedule) {
        mSchedule = schedule;
    }

    public int getProgress() {
        return mProgress;
    }

    public void setProgress(int progress) {
        mProgress = progress;
    }

    public int getState() {
        return mState;
    }

    public void setState(int state) {
        mState = state;
    }

    public int getViewType() {
        return mViewType;
    }

    public void setViewType(int viewType) {
        mViewType = viewType;
    }
}
