package com.union.yunzhi.yunzhi.activities.me;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.union.yunzhi.common.app.ActivityM;
import com.union.yunzhi.factories.moudles.me.GradeModel;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.adapter.SearchGradeAdapter;
import com.union.yunzhi.yunzhi.manager.UserManager;
import com.wyt.searchbox.SearchFragment;
import com.wyt.searchbox.custom.IOnSearchClickListener;

import java.util.ArrayList;
import java.util.List;

public class SearchGradeActivity extends ActivityM implements Toolbar.OnMenuItemClickListener {

    private UserManager mUserManager;
    private Toolbar mToolbar;
    private List<GradeModel> mGradeModels = new ArrayList<>(); // 存放服务器拉下来的成绩数据
    private List<GradeModel> mSearchGrades = new ArrayList<>(); // 存放搜索出来的成绩数据
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
        mUserManager = UserManager.getInstance();
        mGradeModels = mUserManager.getUser().data.getGradeModels();
        initAdapter();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
    }

    /**
     * 初始化数据和适配器
     */
    private void initAdapter() {
        mAdapter = new SearchGradeAdapter(this, mGradeModels, null);
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
