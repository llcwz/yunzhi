package com.union.yunzhi.factories.moudles.me;

import android.graphics.drawable.Drawable;

/**
 * Created by CrazyGZ on 2018/3/3.
 */

public class CourseModel {
    private int mId;
    private Drawable mIcon;
    private String mName;
    private String mSchool;
    private String mCollege;
    private String mTeacher;
    private int mSchedule; // 课程的总进度安排
    private int mProgress; // 课程进度
    private int mState; // 课程的状态

    public CourseModel(int id, Drawable icon, String name, String school, String college, String teacher, int schedule, int progress, int state) {
        mId = id;
        mIcon = icon;
        mName = name;
        mSchool = school;
        mCollege = college;
        mTeacher = teacher;
        mSchedule = schedule;
        mProgress = progress;
        mState = state;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public Drawable getIcon() {
        return mIcon;
    }

    public void setIcon(Drawable icon) {
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
}
