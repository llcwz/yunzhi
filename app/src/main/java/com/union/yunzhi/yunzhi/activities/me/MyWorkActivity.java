package com.union.yunzhi.yunzhi.activities.me;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.union.yunzhi.common.app.ActivityM;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.me.MeModel;
import com.union.yunzhi.factories.moudles.me.WorkModel;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.adapter.MyWorkAdapter;
import com.wyt.searchbox.SearchFragment;
import com.wyt.searchbox.custom.IOnSearchClickListener;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by CrazyGZ on 2018/3/5.
 */

public class MyWorkActivity extends ActivityM {

    private List<String> mSpinnerStates = new LinkedList<>(Arrays.asList("进行中" , "已完成"));
    private List<MeModel> mWork = new ArrayList<>(); // 我的任务测试数据
    private NiceSpinner mSpinner;
    private RecyclerView mRecyclerView;
    private MyWorkAdapter mAdapter;
    public static void newInstance(Context context) {
        Intent intent = new Intent(context, MyWorkActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_my_work;
    }

    @Override
    protected void initWidget() {
        data();
        mSpinner = (NiceSpinner) findViewById(R.id.nice_spinner);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
    }

    private void data() {
        for (int i = 0; i <10; i++) {
            mWork.add(new MeModel(new WorkModel(i,
                    "任务" + i,
                    "计算机组成原理" + i,
                    "单元测试" + i,
                    "2018.3.6 16:0" + i ,
                    "2018.3.6 16:5" + i,
                    new Random().nextInt(2) == 0 ? "进行中" : "已完成",
                    "姜毅" + i,
                    "2018.3.6 1" + new Random().nextInt(9) + ":30")));
        }

        mAdapter = new MyWorkAdapter(this, mWork, new MyAdapter.AdapterListener<MeModel>() {
            @Override
            public void onItemClick(MyAdapter.MyViewHolder holder, MeModel data) {
                // TODO: 2018/3/6 调到课程下的单元测试
                Toast.makeText(MyWorkActivity.this, "查看任务", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void initData() {
        mSpinner.attachDataSource(mSpinnerStates);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MyWorkActivity.this, "" + mSpinnerStates.get(i).toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
    }
}
