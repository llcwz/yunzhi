package com.union.yunzhi.factories.moudles.me;

/**
 * Created by CrazyGZ on 2018/3/6.
 */

public class WorkModel {
    private int mId;
    private String mName;
    private String mCourse; // 所属课程
    private String mType; // 类型
    private String mStart; // 开始时间
    private String mEnd; // 结束时间
    private String mState; // 当前状态 有进行中和已完成两种
    private String mPromulgator; // 任务发布者
    private String mTime; // 发布时间

    public WorkModel() {}

    public WorkModel(int id, String name, String course, String type, String start, String end, String state, String promulgator, String time) {
        mId = id;
        mName = name;
        mCourse = course;
        mType = type;
        mStart = start;
        mEnd = end;
        mState = state;
        mPromulgator = promulgator;
        mTime = time;
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

    public String getCourse() {
        return mCourse;
    }

    public void setCourse(String course) {
        mCourse = course;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getStart() {
        return mStart;
    }

    public void setStart(String start) {
        mStart = start;
    }

    public String getEnd() {
        return mEnd;
    }

    public void setEnd(String end) {
        mEnd = end;
    }

    public String getState() {
        return mState;
    }

    public void setState(String state) {
        mState = state;
    }

    public String getPromulgator() {
        return mPromulgator;
    }

    public void setPromulgator(String promulgator) {
        mPromulgator = promulgator;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }
}
