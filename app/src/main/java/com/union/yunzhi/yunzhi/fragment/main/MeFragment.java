package com.union.yunzhi.yunzhi.fragment.main;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.union.yunzhi.common.app.FragmentM;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.me.MeConstant;
import com.union.yunzhi.factories.moudles.me.MeModel;
import com.union.yunzhi.factories.moudles.me.NavigationModel;
import com.union.yunzhi.factories.moudles.me.PersonModel;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.account.AccountSingle;
import com.union.yunzhi.yunzhi.activities.LoginActivity;
import com.union.yunzhi.yunzhi.activities.me.MyCourseActivity;
import com.union.yunzhi.yunzhi.activities.me.MyMessageActivity;
import com.union.yunzhi.yunzhi.activities.me.MyWorkActivity;
import com.union.yunzhi.yunzhi.activities.me.SearchGradeActivity;
import com.union.yunzhi.yunzhi.adapter.MeNavigationAdapter;
import com.union.yunzhi.yunzhi.fragment.me.PersonDialogFragment;
import com.union.yunzhi.yunzhi.manager.UserManager;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends FragmentM implements View.OnClickListener {

    private UserManager mUserManager;
    private CircleImageView mMe;
    private TextView mUsername; // 用户名
    private TextView mAccount; // 账号
    private TextView mMyCourse; // 我的课程
    private TextView mMyMessage; // 我的消息
    private RecyclerView mRecyclerView;
    private MeNavigationAdapter mMeNavigationAdapter;
    private MeModel mPersonModel; // 个人测试数据
    private List<MeModel> mStudentNavigations = new ArrayList<>(); // 学生导航测试数据
    private List<MeModel> mTeacherNavigations = new ArrayList<>(); // 教师导航测试数据


    //自定义了一个广播接收器
    private LoginBroadcastReceiver receiver =
            new LoginBroadcastReceiver();

    @Override
    protected int getContentLayoutId() {
        return R.layout.main_fragment_me;
    }

    @Override
    protected void initWidget(View view) {
        // 获取用户单例
        mUserManager = UserManager.getInstance();
        //注册广播
        registerBroadcast();
        data();
        mMe = (CircleImageView) view.findViewById(R.id.ci_me);
        mUsername = (TextView) view.findViewById(R.id.tv_username);
        mAccount = (TextView) view.findViewById(R.id.tv_account);
        mMyCourse = (TextView) view.findViewById(R.id.tv_my_course);
        mMyMessage = (TextView) view.findViewById(R.id.tv_my_message);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rec_me);

        mMe.setOnClickListener(this);
        mMyCourse.setOnClickListener(this);
        mMyMessage.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    private void data() {

        // 初始化学生导航数据
        mStudentNavigations.add(new MeModel(new NavigationModel(R.drawable.ri_me_navigation_comprehensive, getString(R.string.me_navigation_comprehensive))));
        mStudentNavigations.add(new MeModel(new NavigationModel(R.drawable.ri_me_navigation_work, getString(R.string.me_navigation_my_work))));
        mStudentNavigations.add(new MeModel(new NavigationModel(R.drawable.ri_me_navigation_search, getString(R.string.me_navigation_score_search))));
        mStudentNavigations.add(new MeModel(new NavigationModel(R.drawable.ri_me_navigation_ability, getString(R.string.me_navigation_ability))));
        mStudentNavigations.add(new MeModel(new NavigationModel(R.drawable.ri_me_navigation_news, getString(R.string.me_navigation_news))));

        // 初始化教师导航数据
        mTeacherNavigations.add(new MeModel(new NavigationModel(R.drawable.ri_me_navigation_course,getString(R.string.me_navigation_course_management))));
        mTeacherNavigations.add(new MeModel(new NavigationModel(R.drawable.ri_me_navigation_comprehensive,getString(R.string.me_navigation_comprehensive))));
        mTeacherNavigations.add(new MeModel(new NavigationModel(R.drawable.ri_me_navigation_work,getString(R.string.me_navigation_work_management))));
        mTeacherNavigations.add(new MeModel(new NavigationModel(R.drawable.ri_me_navigation_ability,getString(R.string.me_navigation_ability))));
        mTeacherNavigations.add(new MeModel(new NavigationModel(R.drawable.ri_me_navigation_analysis,getString(R.string.me_navigation_data_analysis))));

    }

    // 初始化适配器
    private void initAdapter() {
        if (mUserManager.getPerson().getAccess() == MeConstant.ACCESS_STUDENT) { // 如果是学生登录
            mMeNavigationAdapter = new MeNavigationAdapter(mStudentNavigations, new MyAdapter.AdapterListener<MeModel>() {
                @Override
                public void onItemClick(MyAdapter.MyViewHolder holder, MeModel data) {
                    if (MeConstant.NAVIGATION_COMPREHENSIVE.equals(data.getNavigationModel().getNavigationName())) { // 我的实训

                    } else if (MeConstant.NAVIGATION_MY_WORK.equals(data.getNavigationModel().getNavigationName())) { // 我的任务
                        MyWorkActivity.newInstance(getActivity());
                    } else if (MeConstant.NAVIGATION_SCORE_SEARCH.equals(data.getNavigationModel().getNavigationName())) { // 成绩查询
                        SearchGradeActivity.newInstance(getActivity());
                    } else if (MeConstant.NAVIGATION_ABILITY.equals(data.getNavigationModel().getNavigationName())) { //能力档案

                    } else if (MeConstant.NAVIGATION_NEWS.equals(data.getNavigationModel().getNavigationName())) { // 新闻资讯

                    }
                }

                @Override
                public void onItemLongClick(MyAdapter.MyViewHolder holder, MeModel data) {

                }

                @Override
                public Boolean setAddActionContinue() {
                    return null;
                }

                @Override
                public void updataUI(Object object) {

                }
            });
        } else if (mUserManager.getPerson().getAccess() == MeConstant.ACCESS_TEACHER){
            mMeNavigationAdapter = new MeNavigationAdapter(mTeacherNavigations, new MyAdapter.AdapterListener<MeModel>() {

                @Override
                public void onItemClick(MyAdapter.MyViewHolder holder, MeModel data) {
                    if (MeConstant.NAVIGATION_COURSE_MANAGEMENT.equals(data.getNavigationModel().getNavigationName())) { // 课程管理

                    } else if (MeConstant.NAVIGATION_MY_WORK.equals(data.getNavigationModel().getNavigationName())) { // 综合实训

                    } else if (MeConstant.NAVIGATION_WORK_MANAGEMENT.equals(data.getNavigationModel().getNavigationName())) { // 任务管理
                        MyWorkActivity.newInstance(getActivity());
                    } else if (MeConstant.NAVIGATION_ABILITY.equals(data.getNavigationModel().getNavigationName())) { //能力档案

                    } else if (MeConstant.NAVIGATION_DATA_ANALYSIS.equals(data.getNavigationModel().getNavigationName())) { // 数据分析

                    }
                }

                @Override
                public void onItemLongClick(MyAdapter.MyViewHolder holder, MeModel data) {

                }

                @Override
                public Boolean setAddActionContinue() {
                    return null;
                }

                @Override
                public void updataUI(Object object) {

                }
            });
        }
    }

    @Override
    protected void initData() {
        visitUI();
        if (mUserManager.hasLogined()){ //假如用户登录了
            Glide.with(getActivity()).load(mPersonModel.getPersonModel().getMe()).into(mMe);
            mMe.setOnClickListener(this);
            mUsername.setText(mPersonModel.getPersonModel().getUsername());
            mAccount.setText(mPersonModel.getPersonModel().getAccount());
            mMyCourse.setOnClickListener(this);
            mMyMessage.setOnClickListener(this);
            mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
            mRecyclerView.setAdapter(mMeNavigationAdapter);
        } else { // 游客模式

        }

    }


    /**
     * 点击事件
     * 1、点击头像会弹出一个dialog，可以修改密码、退出平台
     * 2、点击我的课程可以查看课程详情
     * 3、点击消息可以查看课程消息或者讨论区消息等
     * @param view
     */
    @Override
    public void onClick(View view) {
        if (mUserManager.hasLogined()) { // 如果用户已经登录，则对应相应的点击事件

            switch (view.getId()) {
                case R.id.ci_me: // 点击头像
                    FragmentManager fragmentManager = getChildFragmentManager();
                    PersonDialogFragment personDialogFragment = PersonDialogFragment.newInstance();
                    personDialogFragment.show(fragmentManager, PersonDialogFragment.TAG_PERSON_DIALOG_FRAGMENT);
                    break;
                case R.id.tv_my_course: // 我的课程
                    MyCourseActivity.newInstance(getActivity());
                    break;
                case R.id.tv_my_message: // 我的消息
                    MyMessageActivity.newInstance(getContext());
                    break;
                default:
            }

        } else if (view.getId() == R.id.ci_me) { // 如果点击头像，则跳转到登录界面
            LoginActivity.newInstance(getActivity());
        } else { // 如果用户未登录，则提示登录操作
            Toast.makeText(getActivity(), "点击头像登录", Toast.LENGTH_SHORT).show();
        }

    }



    private void registerBroadcast() {
        IntentFilter filter = new IntentFilter(LoginActivity.LOGIN_ACTION);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(receiver, filter);
    }

    private void unregisterBroadcast() {
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(receiver);
    }


    /**
     * 接收mina发送来的消息，并更新UI
     */
    private class LoginBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (mUserManager.hasLogined()) {
                loginUI();
            } else {
                visitUI();
            }
        }
    }

    // 游客模式下的UI
    private void visitUI() {
        Glide.with(this).load(R.drawable.dragon_cat).into(mMe);
        mUsername.setText("点击头像登录");
        mAccount.setText("");
        mStudentNavigations.clear();
        mTeacherNavigations.clear();
        mMeNavigationAdapter = null;
    }

    // 登陆后改变UI
    private void loginUI() {
        initAdapter();
        // 加载头像
        Glide.with(this).load(R.drawable.icon_wst).into(mMe);
        //Glide.with(this).load(mUserManager.getPerson().getMe()).into(mMe);
        mUsername.setText(mPersonModel.getPersonModel().getUsername());
        mAccount.setText(mPersonModel.getPersonModel().getAccount());
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        mRecyclerView.setAdapter(mMeNavigationAdapter);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterBroadcast();
    }
}
