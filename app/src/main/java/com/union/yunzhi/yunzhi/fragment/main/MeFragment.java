package com.union.yunzhi.yunzhi.fragment.main;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
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
import com.union.yunzhi.factories.moudles.me.NavigationModel;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.activities.LoginActivity;
import com.union.yunzhi.yunzhi.activities.me.MyCourseActivity;
import com.union.yunzhi.yunzhi.activities.me.MyMessageActivity;
import com.union.yunzhi.yunzhi.activities.me.WorkActivity;
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

    private UserManager mUserManager; // 用户单例
    private CircleImageView mMe; // 用户头像
    private TextView mUsername; // 用户名
    private TextView mAccount; // 账号
    private TextView mMyCourse; // 我的课程
    private TextView mMyMessage; // 我的消息

    private RecyclerView mRecyclerView;
    private MeNavigationAdapter mMeNavigationAdapter = null;

    private List<NavigationModel> mStudentNavigations = new ArrayList<>(); // 学生导航数据
    private List<NavigationModel> mTeacherNavigations = new ArrayList<>(); // 教师导航数据

    //登录和注销广播接收器
    private LoginBroadcastReceiver mLoginReceiver = new LoginBroadcastReceiver();
    private LogoutBroadcastReceiver mLogoutReceiver = new LogoutBroadcastReceiver();

    @Override
    protected int getContentLayoutId() {
        return R.layout.main_fragment_me;
    }

    @Override
    protected void initWidget(View view) {
        // 获取用户单例
        // TODO: 2018/3/10 读取数据库用户的状态
        mUserManager = UserManager.getInstance();
        //注册广播
        registerBroadcast();

        mMe = (CircleImageView) view.findViewById(R.id.ci_me);
        mUsername = (TextView) view.findViewById(R.id.tv_username);
        mAccount = (TextView) view.findViewById(R.id.tv_account);
        mMyCourse = (TextView) view.findViewById(R.id.tv_my_course);
        mMyMessage = (TextView) view.findViewById(R.id.tv_my_message);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rec_me);

    }

    // 初始化导航数据
    private void initNavigationData() {

        // 初始化学生导航数据
        mStudentNavigations.add(new NavigationModel(R.drawable.ri_me_navigation_comprehensive, getString(R.string.me_navigation_comprehensive)));
        mStudentNavigations.add(new NavigationModel(R.drawable.ri_me_navigation_work, getString(R.string.me_navigation_my_work)));
        mStudentNavigations.add(new NavigationModel(R.drawable.ri_me_navigation_search, getString(R.string.me_navigation_score_search)));
        mStudentNavigations.add(new NavigationModel(R.drawable.ri_me_navigation_ability, getString(R.string.me_navigation_ability)));
        mStudentNavigations.add(new NavigationModel(R.drawable.ri_me_navigation_news, getString(R.string.me_navigation_news)));

        // 初始化教师导航据
        mTeacherNavigations.add(new NavigationModel(R.drawable.ri_me_navigation_course,getString(R.string.me_navigation_course_management)));
        mTeacherNavigations.add(new NavigationModel(R.drawable.ri_me_navigation_comprehensive,getString(R.string.me_navigation_comprehensive)));
        mTeacherNavigations.add(new NavigationModel(R.drawable.ri_me_navigation_work,getString(R.string.me_navigation_work_management)));
        mTeacherNavigations.add(new NavigationModel(R.drawable.ri_me_navigation_ability,getString(R.string.me_navigation_ability)));
        mTeacherNavigations.add(new NavigationModel(R.drawable.ri_me_navigation_analysis,getString(R.string.me_navigation_data_analysis)));

    }

    // 初始化适配器，并且监听导航栏的点击事件
    private void initAdapter() {
        initNavigationData();
        if (mUserManager.getPerson().getPriority() == MeConstant.PRIORITY_STUDENT) { // 如果是学生登录
            mMeNavigationAdapter = new MeNavigationAdapter(getActivity() ,mStudentNavigations, new MyAdapter.AdapterListener<NavigationModel>() {
                @Override
                public void onItemClick(MyAdapter.MyViewHolder holder, NavigationModel data) {
                    if (MeConstant.NAVIGATION_COMPREHENSIVE.equals(data.getNavigationName())) { // 我的实训
                        // TODO: 2018/3/10 学生：我的实训
                    } else if (MeConstant.NAVIGATION_MY_WORK.equals(data.getNavigationName())) { // 我的任务
                        WorkActivity.newInstance(getActivity());
                    } else if (MeConstant.NAVIGATION_SCORE_SEARCH.equals(data.getNavigationName())) { // 成绩查询
                        SearchGradeActivity.newInstance(getActivity());
                    } else if (MeConstant.NAVIGATION_ABILITY.equals(data.getNavigationName())) { //能力档案
                        // TODO: 2018/3/10 学生：能力档案
                    } else if (MeConstant.NAVIGATION_NEWS.equals(data.getNavigationName())) { // 新闻资讯
                        // TODO: 2018/3/10 学生：新闻资讯
                    }
                }

                @Override
                public void onItemLongClick(MyAdapter.MyViewHolder holder, NavigationModel data) {

                }

                @Override
                public Boolean setAddActionContinue() {
                    return null;
                }

                @Override
                public void updataUI(Object object) {

                }
            });
        } else if (mUserManager.getPerson().getPriority() == MeConstant.PRIORITY_TEACHER){
            mMeNavigationAdapter = new MeNavigationAdapter(getActivity(), mTeacherNavigations, new MyAdapter.AdapterListener<NavigationModel>() {

                @Override
                public void onItemClick(MyAdapter.MyViewHolder holder, NavigationModel data) {
                    if (MeConstant.NAVIGATION_COURSE_MANAGEMENT.equals(data.getNavigationName())) { // 课程管理
                        // TODO: 2018/3/10 教师：课程管理
                    } else if (MeConstant.NAVIGATION_MY_WORK.equals(data.getNavigationName())) { // 综合实训
                        // TODO: 2018/3/10教师：综合实训
                    } else if (MeConstant.NAVIGATION_WORK_MANAGEMENT.equals(data.getNavigationName())) { // 任务管理
                        WorkActivity.newInstance(getActivity());
                    } else if (MeConstant.NAVIGATION_ABILITY.equals(data.getNavigationName())) { //能力档案
                        // TODO: 2018/3/10 教师：能力档案
                    } else if (MeConstant.NAVIGATION_DATA_ANALYSIS.equals(data.getNavigationName())) { // 数据分析
                        // TODO: 2018/3/10 教师：数据分析
                    }
                }

                @Override
                public void onItemLongClick(MyAdapter.MyViewHolder holder, NavigationModel data) {

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
        if (mUserManager.hasLogined()){ //假如用户登录了
            loginUI();
        } else { // 游客模式
            visitUI();
        }

        mMe.setOnClickListener(this);
        mMyCourse.setOnClickListener(this);
        mMyMessage.setOnClickListener(this);

    }


    /**
     * 点击事件：
     * 1、点击头像：如果用户登录了，则会弹出一个dialog，可以修改头像、修改密码和退出平台；否则跳转到注册界面
     * 2、点击我的课程：可以查看课程详情
     * 3、点击我的消息：可以查看课程消息或者讨论区消息等
     * @param view
     */
    @Override
    public void onClick(View view) {
        if (mUserManager.hasLogined()) { // 如果用户已经登录，则对应相应的点击事件
            switch (view.getId()) {
                case R.id.ci_me: // 点击头像弹出dialogFragment
                    PersonDialogFragment.newInstance().show(getChildFragmentManager(), PersonDialogFragment.TAG);
                    break;
                case R.id.tv_my_course: // 查看我的课程
                    MyCourseActivity.newInstance(getActivity());
                    break;
                case R.id.tv_my_message: // 查看我的消息
                    MyMessageActivity.newInstance(getActivity());
                    break;
                default:
            }

        } else if (view.getId() == R.id.ci_me) { // 如果没有登录，点击头像则跳转到登录界面
            LoginActivity.newInstance(getActivity());
        } else { // 如果未登录，点击其他则提示登录操作
            Toast.makeText(getActivity(), "点击头像登录", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 注册广播，监听用户登录状态
     */
    private void registerBroadcast() {
        IntentFilter loginFilter = new IntentFilter(LoginActivity.LOGIN_ACTION);
        IntentFilter logoutFilter = new IntentFilter(PersonDialogFragment.LOGOUT_ACTION);
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
        localBroadcastManager.registerReceiver(mLogoutReceiver, logoutFilter);
        localBroadcastManager.registerReceiver(mLoginReceiver, loginFilter);
    }

    /**
     * 注销广播
     */
    private void unregisterBroadcast() {
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
        localBroadcastManager.unregisterReceiver(mLoginReceiver);
        localBroadcastManager.unregisterReceiver(mLogoutReceiver);
    }


    /**
     * 接收mina发送来的消息，并更新UI
     */
    private class LoginBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (mUserManager.hasLogined()) {
                loginUI();
            }
        }
    }

    /**
     * 接受PersonDialogFragment发来的注销广播
     */
    private class LogoutBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            visitUI();
        }
    }


    // 游客模式下的UI
    private void visitUI() {
        mUserManager.removeUser(); // 清除用户的数据
        // TODO: 2018/3/10 更改数据库用户的数据
        // 加载默认头像
        Glide.with(this).load("http://img.zcool.cn/community/0173a755ba4cf332f87528a16a73cf.jpg").into(mMe);
        mUsername.setText("点击头像登录");
        mAccount.setText("");
        if (mMeNavigationAdapter != null) { // 如果是用户选择注销，则清除已经加载的导航数据
            mStudentNavigations.clear();
            mTeacherNavigations.clear();
            mMeNavigationAdapter.notify();
            mMeNavigationAdapter = null;
        }
    }


    /**
     * 登录状态下的UI
     */
    private void loginUI() {
        // 加载导航数据以及初始化适配器
        initAdapter();
        // 加载头像
        Glide.with(this).load(R.drawable.icon_wst).into(mMe);
        mUsername.setText(mUserManager.getPerson().getStudentname());
        mAccount.setText(mUserManager.getPerson().getAccount());
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        mRecyclerView.setAdapter(mMeNavigationAdapter);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterBroadcast();
    }
}
