package com.union.yunzhi.factories.moudles.me;

/**
 * Created by CrazyGZ on 2018/2/25.
 */

public class MeConstant {

    public static final int ECODE = 0; // success

    // 视图
    public static final int STUDENT_COURSE_VIEW = 0;
    public static final int TEACHER_COURSE_VIEW = 1;

    public static final int STUDENT_WORK_VIEW = 0;
    public static final int TEACHER_WORK_VIEW = 1;

    public static final int POST_COMMENT_VIEW = 0;
    public static final int QUESTION_COMMENT_VIEW = 1;

    // 权限
    public static final int PRIORITY_STUDENT = 0;
    public static final int PRIORITY_TEACHER = 1;

    // 课程的状态
    public static final int COURSE_STATE_ALL = 0; // 全部状态的课程
    public static final int COURSE_STATE_UNDERWAY = 1; // 进行时
    public static final int COURSE_STATE_BEGIN = 2; // 即将开始
    public static final int COURSE_STATE_FINISH = 3; // 已完成

    // 消息的fragment标记
    public static final int MESSAGE_FRAGMENT_TAG_COMMENT = 0; // 评论
    public static final int MESSAGE_FRAGMENT_TAG_LIKE = 1; // 赞
    public static final int MESSAGE_FRAGMENT_TAG_INFORM = 2; // 系统消息

    // 导航标记
    public static final String NAVIGATION_MY_WORK = "我的任务";
    public static final String NAVIGATION_COMPREHENSIVE = "综合实训";
    public static final String NAVIGATION_SCORE_SEARCH = "成绩查询";
    public static final String NAVIGATION_ABILITY = "能力档案";
    public static final String NAVIGATION_NEWS = "新闻资讯";
    public static final String NAVIGATION_COURSE_MANAGEMENT = "课程管理";
    public static final String NAVIGATION_WORK_MANAGEMENT = "任务管理";
    public static final String NAVIGATION_DATA_ANALYSIS = "数据分析";
}
