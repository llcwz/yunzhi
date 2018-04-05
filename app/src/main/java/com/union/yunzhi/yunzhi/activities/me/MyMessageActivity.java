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
import com.union.yunzhi.factories.moudles.me.CommentMeModel;
import com.union.yunzhi.factories.moudles.me.LikeMeModel;
import com.union.yunzhi.factories.moudles.me.MeConstant;
import com.union.yunzhi.factories.moudles.me.MessageModel;
import com.union.yunzhi.factories.moudles.me.SystemInformModel;
import com.union.yunzhi.factories.moudles.me.UserModel;
import com.union.yunzhi.factories.okhttp.exception.OkHttpException;
import com.union.yunzhi.factories.okhttp.listener.DisposeDataListener;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.fragment.me.MessageFragment;
import com.union.yunzhi.yunzhi.manager.DialogManager;
import com.union.yunzhi.yunzhi.meutils.MeUtils;
import com.union.yunzhi.yunzhi.network.RequestCenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
                            initAdapter(mMessageModel);
                        } else {
                            Toast.makeText(MyMessageActivity.this, "" + baseMessageModel.emsg, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        mMessageModel = locationData();
                        initAdapter(mMessageModel);
                        DialogManager.getInstnce().dismissProgressDialog();
                        OkHttpException okHttpException = (OkHttpException) reasonObj;
//                        if (okHttpException.getEcode() == 1) {
////                            Toast.makeText(MyMessageActivity.this, "" + okHttpException.getEmsg(), Toast.LENGTH_SHORT).show();
//                        } else if (okHttpException.getEcode() == -1){
//                            Toast.makeText(MyMessageActivity.this, "网络连接错误", Toast.LENGTH_SHORT).show();
//                        } else if (okHttpException.getEcode() == -2) {
//                            Toast.makeText(MyMessageActivity.this, "解析错误" , Toast.LENGTH_SHORT).show();
//                        } else if (okHttpException.getEcode() == -3) {
//                            Toast.makeText(MyMessageActivity.this, "未知错误", Toast.LENGTH_SHORT).show();
//                        }

                    }
                });
    }

    // 本地数据
    private MessageModel locationData() {
        MessageModel messageModels = null;
        List<CommentMeModel> commentMeModels = new ArrayList<>();
        List<LikeMeModel> likeMeModels = new ArrayList<>();
        List<SystemInformModel> systemInformModels = new ArrayList<>();
        String[] icon = new String[] {"http://dennisallan.top/dennis_allan/Public/image/student/student_default.jpg",
        "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1201426996,1678659587&fm=27&gp=0.jpg",
        "http://dennisallan.top/dennis_allan/Public/image/student/student.jpg",
        "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=468214244,7348604&fm=27&gp=0.jpg"};
        String[] name = new String[] {"陈建伟", "郑萌", "万利", "李志能"};
        String[] title = new String[] {"武科大简介","有人看王小波的小说吗？","资源大放送","泛型"};
        String[] content = new String[] {"好文章！","不知道该说些什么，只好点个赞了。","老哥走心了","日常灌水"};
        for (int i = 0; i < 4; i++) {
            commentMeModels.add(new CommentMeModel("1",
                    "201513138036",
                    icon[i],
                    name[i],
                    "2018-04-03 " + new Random().nextInt(24) + ":" + new Random().nextInt(60) + ":" + new Random().nextInt(60),
                    content[i],
                    title[i],
                    null));
        }

        for (int i = 0; i < 4; i++) {
            likeMeModels.add(new LikeMeModel("201513138036",
                    icon[i],
                    name[i],
                    "2018-04-03 " + new Random().nextInt(24) + ":" + new Random().nextInt(60) + ":" + new Random().nextInt(60),
                    "测试" + i));
        }

        for (int i = 0; i < 4; i++) {
            systemInformModels.add(new SystemInformModel("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3693357268,602549071&fm=27&gp=0.jpg",
                    "系统",
                    "2018-04-03 " + new Random().nextInt(24) + ":" + new Random().nextInt(60) + ":" + new Random().nextInt(60),
                    "通知测试" + i));
        }
        messageModels = new MessageModel(commentMeModels, likeMeModels, systemInformModels);
        return messageModels;
    }

    // 初始化数据和fragment
    private void initAdapter(MessageModel messageModel) {
        mTitles = new String[]{"回复","赞","通知"};
        mFragments.add(MessageFragment.newInstance(MeConstant.MESSAGE_FRAGMENT_TAG_COMMENT, messageModel));
        mFragments.add(MessageFragment.newInstance(MeConstant.MESSAGE_FRAGMENT_TAG_LIKE, messageModel));
        mFragments.add(MessageFragment.newInstance(MeConstant.MESSAGE_FRAGMENT_TAG_INFORM, messageModel));

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

    @Override
    protected void initData() {

//        class MessageAdapter extends FragmentPagerAdapter {
//            public MessageAdapter(FragmentManager fm) {
//                super(fm);
//            }
//
//            @Override
//            public Fragment getItem(int position) {
//                return mFragments.get(position);
//            }
//
//            @Override
//            public int getCount() {
//                return mFragments.size();
//            }
//        }
    }
}
