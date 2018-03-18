package com.union.yunzhi.yunzhi.activities.classfication;

import android.support.v4.app.Fragment;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.union.yunzhi.common.app.ActivityM;
import com.union.yunzhi.factories.moudles.classfication.ClassConst;
import com.union.yunzhi.factories.moudles.classfication.beans.question.BaseQuestionBean;
import com.union.yunzhi.factories.moudles.me.UserModel;
import com.union.yunzhi.factories.okhttp.listener.DisposeDataListener;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.fragment.classfication.ClassAddQuestionDialogFragment;
import com.union.yunzhi.yunzhi.fragment.classfication.ClassFileFragment;
import com.union.yunzhi.yunzhi.fragment.classfication.ClassQuestionFragment;
import com.union.yunzhi.yunzhi.fragment.classfication.ClassTestFragment;
import com.union.yunzhi.yunzhi.manager.DialogManager;
import com.union.yunzhi.yunzhi.meutils.MeUtils;
import com.union.yunzhi.yunzhi.network.RequestCenter;

import java.util.ArrayList;

/**
 * Created by cjw on 2018/3/1 0001.
 */

public class ClassCourseFileActivity extends ActivityM implements ClassAddQuestionDialogFragment.OnAddQuestionListener {

    public static final String KEY = "courseId";
    private UserModel mUser;
    private String mCourseId;
    private CommonTabLayout mTabLayout;
    private ArrayList<CustomTabEntity> mList=new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ClassQuestionFragment mClassQuestionFragment;

    @Override
    protected int getContentLayoutId() {
        return R.layout.class_course_file;
    }



    @Override
    protected void initWidget() {
        mCourseId = getIntent().getStringExtra(KEY);
        mUser = MeUtils.getUser();
        data();
        mTabLayout = (CommonTabLayout)findViewById(R.id.sliding_tab_layout);
    }

    private void data() {

        mFragments.add(ClassFileFragment.newInstance());
        mClassQuestionFragment = ClassQuestionFragment.newInstance(mCourseId);
        mFragments.add(mClassQuestionFragment);
        mFragments.add(ClassTestFragment.newInstance());
        mList.add(getCustomTabEntity("课程",R.drawable.select_24dp,R.drawable.unselect_24dp));
        mList.add(getCustomTabEntity("交流",R.drawable.select_24dp,R.drawable.unselect_24dp));
        mList.add(getCustomTabEntity("测评",R.drawable.select_24dp,R.drawable.unselect_24dp));
    }

    @Override
    protected void initData() {
        mTabLayout.setTabData(mList,this, R.id.framelayout, mFragments);

    }

    private CustomTabEntity getCustomTabEntity(String title,int sIcon,int uIcon){

        final String tabTitle=title;
        final int SelectedIcon=sIcon,UnselectedIcon=uIcon;

        CustomTabEntity temp=new CustomTabEntity(){

            @Override
            public String getTabTitle() {
                return tabTitle;
            }

            @Override
            public int getTabSelectedIcon() {
                return SelectedIcon;
            }

            @Override
            public int getTabUnselectedIcon() {
                return UnselectedIcon;
            }
        };

        return temp;
    }

    @Override
    public void getQuestion(String question, String details) {
        DialogManager.getInstnce().showProgressDialog(ClassCourseFileActivity.this);
        RequestCenter.requestAddPost(mUser.getAccount(),
                mUser.getPriority(),
                1,
                question,
                details,
                new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        DialogManager.getInstnce().dismissProgressDialog();
                        BaseQuestionBean baseQuestionBean = (BaseQuestionBean) responseObj;
                        if (baseQuestionBean.ecode == ClassConst.ECODE) {
                            mClassQuestionFragment.notifyQuestion(baseQuestionBean.data.get(0));
                        }
                    }

                    @Override
                    public void onFailure(Object reasonObj) {

                    }
                }
        );
    }
}
