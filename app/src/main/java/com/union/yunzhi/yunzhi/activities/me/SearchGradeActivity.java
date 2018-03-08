package com.union.yunzhi.yunzhi.activities.me;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.union.yunzhi.common.app.ActivityM;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.me.GradeModel;
import com.union.yunzhi.factories.moudles.me.MeModel;
import com.union.yunzhi.factories.moudles.me.UnitGradeModel;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.adapter.SearchGradeAdapter;
import com.wyt.searchbox.SearchFragment;
import com.wyt.searchbox.custom.IOnSearchClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SearchGradeActivity extends ActivityM implements Toolbar.OnMenuItemClickListener {

    private Toolbar mToolbar;
    private List<MeModel> mGrades = new ArrayList<>();
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
        data();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
    }

    private void data() {

        for (int i = 0; i < 20; i++) {
            List<UnitGradeModel> unitGradeModels = new ArrayList<>();
            for (int j = 0; j < new Random().nextInt(4) + 1; j++) {
                unitGradeModels.add(new UnitGradeModel(j, "单元测试" + j, "" + new Random().nextInt(50)));
            }
            mGrades.add(new MeModel(new GradeModel(i,
                    "计算机组成原理" + i,
                    "" + new Random().nextInt(100),
                    unitGradeModels
                    )));
        }
        mAdapter = new SearchGradeAdapter(this, mGrades, null);
    }

    @Override
    protected void initData() {
        mToolbar.inflateMenu(R.menu.search_grade_item);
        mToolbar.setOnMenuItemClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        SearchFragment searchFragment = SearchFragment.newInstance();
        searchFragment.setOnSearchClickListener(new IOnSearchClickListener() {
            @Override
            public void OnSearchClick(String keyword) {
                // TODO: 2018/3/6 关键字搜索
            }
        });
        searchFragment.show(getSupportFragmentManager(), SearchFragment.TAG);
        return false;
    }
}
