package com.union.yunzhi.factories.moudles.me;

/**
 * Created by CrazyGZ on 2018/3/6.
 */

public class WorkModel {
    private String mId; // 该任务的id
    private String mName; // 任务名称
    private String mCourse; // 所属课程
    private String mType; // 类型，比如是单元测试，期末测评等
    private String mStart; // 开始时间
    private String mEnd; // 结束时间
    private String mState; // 当前状态 有进行中和已完成两种
    private String mPromulgator; // 任务发布者
    private String mTime; // 发布时间
    private int mViewType; // 布局的类型

    public WorkModel() {}

    public WorkModel(String id, String name, String course, String type, String start, String end, String state, String promulgator, String time, int viewType) {
        mId = id;
        mName = name;
        mCourse = course;
        mType = type;
        mStart = start;
        mEnd = end;
        mState = state;
        mPromulgator = promulgator;
        mTime = time;
        mViewType = viewType;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
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

    public int getViewType() {
        return mViewType;
    }

    public void setViewType(int viewType) {
        mViewType = viewType;
    }
}
