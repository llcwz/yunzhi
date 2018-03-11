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
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.me.MeConstant;
import com.union.yunzhi.factories.moudles.me.MeModel;
import com.union.yunzhi.factories.moudles.me.WorkModel;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.adapter.MyWorkAdapter;
import com.union.yunzhi.yunzhi.manager.UserManager;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by CrazyGZ on 2018/3/5.
 */

public class WorkActivity extends ActivityM implements Toolbar.OnMenuItemClickListener {

    private UserManager mUserManager;
    private MeModel mMeModel;
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
        mUserManager = UserManager.getInstance();
        mMeModel = mUserManager.getUser().data;
        data();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mSpinner = (NiceSpinner) findViewById(R.id.nice_spinner);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
    }

    // 初始化适配器
    private void data() {
            mAdapter = new MyWorkAdapter(this, mMeModel.getWorkModels(), new MyAdapter.AdapterListener<WorkModel>() {
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
        if (mUserManager.getPerson().getPriority() == MeConstant.PRIORITY_TEACHER) { // 如果是老师登进，则给予权限发布新的任务
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
        if (mMeModel.getWorkModels() != null) { // 如果有任务
            for (WorkModel workModel : mMeModel.getWorkModels()) {
                if (workModel.getState().equals(mSpinnerStates.get(i).toString())) {
                    workModels.add(workModel);
                }
            }
        }
        mAdapter.clear();
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
        NewWorkActivity.newInstance(this);
        return false;
    }
}
