package com.union.yunzhi.yunzhi.activities.me;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.union.yunzhi.common.app.ActivityM;
import com.union.yunzhi.common.util.LogUtils;
import com.union.yunzhi.factories.moudles.me.BaseMessageModel;
import com.union.yunzhi.factories.moudles.me.MeConstant;
import com.union.yunzhi.factories.moudles.me.MessageModel;
import com.union.yunzhi.factories.moudles.me.UserModel;
import com.union.yunzhi.factories.okhttp.exception.OkHttpException;
import com.union.yunzhi.factories.okhttp.listener.DisposeDataListener;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.fragment.me.MessageFragment;
import com.union.yunzhi.yunzhi.manager.DialogManager;
import com.union.yunzhi.yunzhi.meutils.MeUtils;
import com.union.yunzhi.yunzhi.network.RequestCenter;

import java.util.ArrayList;

public class MyMessageActivity extends ActivityM {

    private UserModel mUser;
    private MessageModel mMessageModel;
    private SegmentTabLayout mTabLayout;
    private String[] mTitles;
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    public static void newInstance(Context context) {
        Intent intent = new Intent(context, MyMessageActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_my_message;
    }

    @Override
    protected void initWidget() {
        mUser = MeUtils.getUser();

        mTabLayout = (SegmentTabLayout) findViewById(R.id.segment_tab_layout);
        getData();

    }

    // 获取网络数据
    private void getData() {
        DialogManager.getInstnce().showProgressDialog(this);
        RequestCenter.requestMyMessage(mUser.getAccount(),
                new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        DialogManager.getInstnce().dismissProgressDialog();
                        LogUtils.d("getMyMessageData", responseObj.toString());
                        BaseMessageModel baseMessageModel = (BaseMessageModel) responseObj;
                        if (baseMessageModel.ecode == MeConstant.ECODE) {
                            mMessageModel = baseMessageModel.data;
                        } else {
                            Toast.makeText(MyMessageActivity.this, "" + baseMessageModel.emsg, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Object reasonObj) {

                        DialogManager.getInstnce().dismissProgressDialog();
                        OkHttpException okHttpException = (OkHttpException) reasonObj;
                        if (okHttpException.getEcode() == 1) {
//                            Toast.makeText(MyMessageActivity.this, "" + okHttpException.getEmsg(), Toast.LENGTH_SHORT).show();
                        } else if (okHttpException.getEcode() == -1){
                            Toast.makeText(MyMessageActivity.this, "网络连接错误", Toast.LENGTH_SHORT).show();
                        } else if (okHttpException.getEcode() == -2) {
                            Toast.makeText(MyMessageActivity.this, "解析错误" , Toast.LENGTH_SHORT).show();
                        } else if (okHttpException.getEcode() == -3) {
                            Toast.makeText(MyMessageActivity.this, "未知错误", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    // 初始化数据和fragment
    private void initAdapter() {
        mTitles = new String[]{"回复","赞","通知"};
        mFragments.add(MessageFragment.newInstance(MeConstant.MESSAGE_FRAGMENT_TAG_COMMENT, mMessageModel));
        mFragments.add(MessageFragment.newInstance(MeConstant.MESSAGE_FRAGMENT_TAG_LIKE, mMessageModel));
        mFragments.add(MessageFragment.newInstance(MeConstant.MESSAGE_FRAGMENT_TAG_INFORM, mMessageModel));

    }

    @Override
    protected void initData() {
        initAdapter();
        mTabLayout.setTabData(mTitles, this, R.id.framelayout, mFragments);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }
}
