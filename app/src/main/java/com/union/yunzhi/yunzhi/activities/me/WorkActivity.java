package com.union.yunzhi.yunzhi.activities.me;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.union.yunzhi.common.app.ActivityM;
import com.union.yunzhi.common.util.LogUtils;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.me.BaseWorkModel;
import com.union.yunzhi.factories.moudles.me.MeConstant;
import com.union.yunzhi.factories.moudles.me.UserModel;
import com.union.yunzhi.factories.moudles.me.WorkModel;
import com.union.yunzhi.factories.okhttp.listener.DisposeDataListener;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.activities.communication.AddPostActivity;
import com.union.yunzhi.yunzhi.adapter.MyWorkAdapter;
import com.union.yunzhi.yunzhi.manager.DialogManager;
import com.union.yunzhi.yunzhi.manager.UserManager;
import com.union.yunzhi.yunzhi.meutils.MeUtils;
import com.union.yunzhi.yunzhi.network.RequestCenter;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by CrazyGZ on 2018/3/5.
 */

public class WorkActivity extends ActivityM implements Toolbar.OnMenuItemClickListener {

    private UserModel mUser;
    private List<WorkModel> mWorkModels = new ArrayList<>();
    private List<String> mSpinnerStates = new LinkedList<>(Arrays.asList("进行中" , "已完成")); // spinner的填充内容
    private Toolbar mToolbar;
    private NiceSpinner mSpinner;
    private RecyclerView mRecyclerView;
    private MyWorkAdapter mAdapter;


    public static void newInstance(Context context) {
        Intent intent = new Intent(context, WorkActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_my_work;
    }

    @Override
    protected void initWidget() {
        mUser = MeUtils.getUser();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mSpinner = (NiceSpinner) findViewById(R.id.nice_spinner);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);

        getData();

    }

    // 获取网络数据
    private void getData() {
        DialogManager.getInstnce().showProgressDialog(this);
        RequestCenter.requestMyWork(mUser.getAccount(),
                new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        DialogManager.getInstnce().dismissProgressDialog();
                        LogUtils.d("getMyWorkData", responseObj.toString());
                        BaseWorkModel baseWorkModel = (BaseWorkModel) responseObj;
                        if (baseWorkModel.ecode == MeConstant.ECODE) {
                            mWorkModels = baseWorkModel.data;
                        } else {
                            Toast.makeText(WorkActivity.this, "" + baseWorkModel.emsg, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        DialogManager.getInstnce().dismissProgressDialog();
                        Toast.makeText(WorkActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // 初始化适配器
    private void initAdapter() {
            mAdapter = new MyWorkAdapter(this, mWorkModels, new MyAdapter.AdapterListener<WorkModel>() {
                @Override
                public void onItemClick(MyAdapter.MyViewHolder holder, WorkModel data) {
                    // TODO: 2018/3/6 跳到课程下的单元测试
                    Toast.makeText(WorkActivity.this, "查看任务", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onItemLongClick(MyAdapter.MyViewHolder holder, WorkModel data) {

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

    @Override
    protected void initData() {
        initAdapter();
        if (mUser.getPriority() == MeConstant.PRIORITY_TEACHER) { // 如果是老师登进，则给予权限发布新的任务
            mToolbar.inflateMenu(R.menu.me_add_work_item);
            mToolbar.setOnMenuItemClickListener(this);
        }
        mSpinner.attachDataSource(mSpinnerStates);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                notifyList(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                notifyList(0); // 默认选择
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * 根据spinner选择的状态更新列表
     * @param i spinner状态当前的位置
     */
    private void notifyList(int i) {
        List<WorkModel> workModels = new ArrayList<>();
        if (mWorkModels != null) { // 如果有任务
            for (WorkModel workModel : mWorkModels) {
                if (workModel.getState().equals(mSpinnerStates.get(i).toString())) {
                    workModels.add(workModel);
                }
            }
        }
        notifyList(workModels);
    }

    /**
     * @function 更新列表
     * @param workModels
     */
    private void notifyList(List<WorkModel> workModels) {
        mAdapter.clear();
        mAdapter.add(workModels);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case NewWorkActivity.REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    WorkModel workModel = data.getParcelableExtra(NewWorkActivity.TAG);
                    if (workModel != null) {
                        addWork(workModel);
                    }
                }
                break;
            default:
        }
    }

    /**
     * 有新的任务
     * @param workModels
     */
    private void addWork(WorkModel workModels) {
        mAdapter.add(workModels);
        mAdapter.notify();
    }

    /**
     * 教师发布新的任务
     * @param item
     * @return
     */
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        startActivityForResult(new Intent(this, NewWorkActivity.class), NewWorkActivity.REQUEST_CODE);
        return false;
    }


}
