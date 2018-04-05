package com.union.yunzhi.yunzhi.activities.me;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.union.yunzhi.common.app.ActivityM;
import com.union.yunzhi.common.util.LogUtils;
import com.union.yunzhi.factories.moudles.me.BaseGradeModel;
import com.union.yunzhi.factories.moudles.me.GradeModel;
import com.union.yunzhi.factories.moudles.me.MeConstant;
import com.union.yunzhi.factories.moudles.me.UnitGradeModel;
import com.union.yunzhi.factories.moudles.me.UserModel;
import com.union.yunzhi.factories.okhttp.exception.OkHttpException;
import com.union.yunzhi.factories.okhttp.listener.DisposeDataListener;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.adapter.SearchGradeAdapter;
import com.union.yunzhi.yunzhi.manager.DialogManager;
import com.union.yunzhi.yunzhi.manager.UserManager;
import com.union.yunzhi.yunzhi.meutils.MeUtils;
import com.union.yunzhi.yunzhi.network.RequestCenter;
import com.wyt.searchbox.SearchFragment;
import com.wyt.searchbox.custom.IOnSearchClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SearchGradeActivity extends ActivityM implements Toolbar.OnMenuItemClickListener {

    private UserModel mUser;
    private Toolbar mToolbar;
    private List<GradeModel> mGradeModels = new ArrayList<>(); // 存放服务器拉下来的成绩数据
    private List<GradeModel> mSearchGrades = new ArrayList<>(); // 存放搜索出来的成绩数据
    private TextView mNoGrade;
    private RecyclerView mRecyclerView;
    private SearchGradeAdapter mAdapter;

    public static void newInstance(Context context) {
        Intent intent = new Intent(context, SearchGradeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_search_grade;
    }

    @Override
    protected void initWidget() {
        mUser = MeUtils.getUser();

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mNoGrade = (TextView) findViewById(R.id.tv_no_grade);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);

        getData();
    }

    // 获取成绩
    private void getData() {
        DialogManager.getInstnce().showProgressDialog(this);
        RequestCenter.requestGrade(mUser.getAccount(),
                new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        DialogManager.getInstnce().dismissProgressDialog();
                        LogUtils.d("getGradeData", responseObj.toString());
                        BaseGradeModel baseGradeModel = (BaseGradeModel) responseObj;
                        if (baseGradeModel.ecode == MeConstant.ECODE) {
                            mGradeModels = baseGradeModel.data;
                            initAdapter(mGradeModels);
                        } else {
                            Toast.makeText(SearchGradeActivity.this, "" + baseGradeModel.emsg, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        mGradeModels = locationData();
                        initAdapter(mGradeModels);
                        DialogManager.getInstnce().dismissProgressDialog();
                        OkHttpException okHttpException = (OkHttpException) reasonObj;
//                        if (okHttpException.getEcode() == 1) {
////                            Toast.makeText(SearchGradeActivity.this, "" + okHttpException.getEmsg(), Toast.LENGTH_SHORT).show();
//                        } else if (okHttpException.getEcode() == -1){
//                            Toast.makeText(SearchGradeActivity.this, "网络连接错误", Toast.LENGTH_SHORT).show();
//                        } else if (okHttpException.getEcode() == -2) {
//                            Toast.makeText(SearchGradeActivity.this, "解析错误" , Toast.LENGTH_SHORT).show();
//                        } else if (okHttpException.getEcode() == -3) {
//                            Toast.makeText(SearchGradeActivity.this, "未知错误", Toast.LENGTH_SHORT).show();
//                        }

                    }
                });
    }

    private List<GradeModel> locationData() {
        List<GradeModel> gradeModels = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            gradeModels.add(new GradeModel("测试课程" + i,
                    "" + new Random().nextInt(100),
                    getUnitGrade()));
        }
        return gradeModels;
    }

    private List<UnitGradeModel> getUnitGrade() {
        List<UnitGradeModel> unitGradeModels = new ArrayList<>();
        int j = new Random().nextInt(5);
        for (int i = 0; i < j; i++) {
            unitGradeModels.add(new UnitGradeModel("单元测试" + i, "" + new Random().nextInt(100)));
        }
        return unitGradeModels;
    }


    /**
     * 初始化数据和适配器
     */
    private void initAdapter(List<GradeModel> gradeModels) {
        if (gradeModels == null) {
            gradeModels = new ArrayList<>();
        }
        MeUtils.showNoMessage(gradeModels.size(), mNoGrade, mRecyclerView, "成绩成绩");
        mAdapter = new SearchGradeAdapter(this, gradeModels, null);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mToolbar.setTitle("成绩查询");
        mToolbar.inflateMenu(R.menu.search_grade_item);
        mToolbar.setOnMenuItemClickListener(this);

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        SearchFragment searchFragment = SearchFragment.newInstance();
        searchFragment.setOnSearchClickListener(new IOnSearchClickListener() {
            @Override
            public void OnSearchClick(String keyword) {
                // TODO: 2018/3/10 搜索栏要改
                if (mGradeModels != null) { // 如果有成绩
                    for (GradeModel gradeModel : mGradeModels) {
                        if (gradeModel.getCourse().indexOf(keyword) > 0) {
                            mSearchGrades.add(gradeModel);
                        }
                    }
                    notifyList(mSearchGrades);
                } else {
                    Toast.makeText(SearchGradeActivity.this, "暂时还没有成绩可查询", Toast.LENGTH_SHORT).show();
                }
            }
        });
        searchFragment.show(getSupportFragmentManager(), SearchFragment.TAG);
        return false;
    }

    /**
     * 刷新表列
     * @param searchGrades
     */
    private void notifyList(List<GradeModel> searchGrades) {
        mAdapter.clear();
        mAdapter.add(searchGrades);
        mAdapter.notify();
    }
}
