package com.union.yunzhi.yunzhi.fragment.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.union.yunzhi.common.app.FragmentM;
import com.union.yunzhi.factories.moudles.me.MeConstant;
import com.union.yunzhi.factories.moudles.me.PersonModel;
import com.union.yunzhi.factories.moudles.me.NavigationModel;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.activities.me.MyCourseActivity;
import com.union.yunzhi.yunzhi.adapter.MeNavigationAdapter;
import com.union.yunzhi.yunzhi.fragment.me.PersonDialogFragment;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends FragmentM implements View.OnClickListener {
    private int mAccess; // 权限，根据这个来判断是学生还是教师
    private CircleImageView mMe;
    private TextView mUsername;
    private TextView mAccount;
    private TextView mMyCourse;
    private TextView mMyMessage;
    private RecyclerView mRecyclerView;
    private MeNavigationAdapter mMeNavigationAdapter;
    private PersonModel mPersonModel; // 个人测试数据
    private List<NavigationModel> mStudentNavigations = new ArrayList<>(); // 学生导航测试数据
    private List<NavigationModel> mTeacherNavigations = new ArrayList<>(); // 教师导航测试数据

    @Override
    protected int getContentLayoutId() {
        return R.layout.main_fragment_me;
    }

    @Override
    protected void initArgs(Bundle bundle) {
        super.initArgs(bundle);
    }

    @Override
    protected void initWidget(View view) {
        data();
        mMe = (CircleImageView) view.findViewById(R.id.ci_me);
        mUsername = (TextView) view.findViewById(R.id.tv_username);
        mAccount = (TextView) view.findViewById(R.id.tv_account);
        mMyCourse = (TextView) view.findViewById(R.id.tv_my_course);
        mMyMessage = (TextView) view.findViewById(R.id.tv_my_message);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rec_me);

    }

    /**
     * 初始化数据
     */
    private void data() {

        // 初始化个人数据
        mPersonModel = new PersonModel(getResources().getDrawable(R.drawable.dragon_cat), "Crazy贵子", "201509155064", "123456", 1);
        mAccess = mPersonModel.getAccess();

        // 初始化学生导航数据
        mStudentNavigations.add(new NavigationModel(MeConstant.ACCESS_STUDENT,R.drawable.ri_me_navigation_comprehensive, getString(R.string.me_navigation_comprehensive)));
        mStudentNavigations.add(new NavigationModel(MeConstant.ACCESS_STUDENT ,R.drawable.ri_me_navigation_work, getString(R.string.me_navigation_my_work)));
        mStudentNavigations.add(new NavigationModel(MeConstant.ACCESS_STUDENT ,R.drawable.ri_me_navigation_search, getString(R.string.me_navigation_score_search)));
        mStudentNavigations.add(new NavigationModel(MeConstant.ACCESS_STUDENT ,R.drawable.ri_me_navigation_ability, getString(R.string.me_navigation_ability)));
        mStudentNavigations.add(new NavigationModel(MeConstant.ACCESS_STUDENT ,R.drawable.ri_me_navigation_news, getString(R.string.me_navigation_news)));

        // 初始化教师导航数据
        mTeacherNavigations.add(new NavigationModel(MeConstant.ACCESS_TEACHER ,R.drawable.ri_me_navigation_course,getString(R.string.me_navigation_course_management)));
        mTeacherNavigations.add(new NavigationModel(MeConstant.ACCESS_TEACHER ,R.drawable.ri_me_navigation_comprehensive,getString(R.string.me_navigation_comprehensive)));
        mTeacherNavigations.add(new NavigationModel(MeConstant.ACCESS_TEACHER ,R.drawable.ri_me_navigation_work,getString(R.string.me_navigation_work_management)));
        mTeacherNavigations.add(new NavigationModel(MeConstant.ACCESS_TEACHER ,R.drawable.ri_me_navigation_ability,getString(R.string.me_navigation_ability)));
        mTeacherNavigations.add(new NavigationModel(MeConstant.ACCESS_TEACHER ,R.drawable.ri_me_navigation_analysis,getString(R.string.me_navigation_data_analysis)));

        // 实例化适配器
        if (mAccess == MeConstant.ACCESS_STUDENT) {
            mMeNavigationAdapter = new MeNavigationAdapter(mStudentNavigations, null);
        } else if (mAccess == MeConstant.ACCESS_TEACHER){
            mMeNavigationAdapter = new MeNavigationAdapter(mTeacherNavigations, null);
        }

    }

    @Override
    protected void initData() {
        mMe.setImageDrawable(mPersonModel.getMe());
        mMe.setOnClickListener(this);
        mUsername.setText(mPersonModel.getUsername());
        mAccount.setText(mPersonModel.getAccount());
        mMyCourse.setOnClickListener(this);
        mMyMessage.setOnClickListener(this);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        mRecyclerView.setAdapter(mMeNavigationAdapter);
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
        switch (view.getId()) {
            case R.id.ci_me: // 点击头像
                FragmentManager fragmentManager = getChildFragmentManager();
                PersonDialogFragment personDialogFragment = PersonDialogFragment.newInstance(mPersonModel.getAccount(), mPersonModel.getPassword());
                personDialogFragment.show(fragmentManager, PersonDialogFragment.TAG_PERSON_DIALOG_FRAGMENT);
                break;
            case R.id.tv_my_course: // 我的课程
                MyCourseActivity.newInstance(getContext(), mPersonModel.getAccount(), mPersonModel.getAccess());
                break;
            case R.id.tv_my_message: // 我的消息
                break;
            default:
        }

    }
}
