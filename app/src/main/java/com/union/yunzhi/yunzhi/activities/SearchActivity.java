package com.union.yunzhi.yunzhi.activities;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.union.yunzhi.common.app.ActivityM;
import com.union.yunzhi.common.helper.ViewHelper;
import com.union.yunzhi.common.util.LogUtils;
import com.union.yunzhi.factories.moudles.BaseModel;
import com.union.yunzhi.yunzhi.R;

import java.util.ArrayList;

public class SearchActivity extends ActivityM implements ViewHelper.onFinshListener {


    private static final int MAX_HISTORY_RECORD = 10;

    /**
     * 公共UI
     */
    private EditText inputEditText;
    private TextView cancelView;

    /**
     * 历史记录相关UI
     */
    private TextView clearHistoryView;
    private LinearLayout historyLayout;
    private ListView historyListView;
    //private SearchAdapter historyAdapter;

    /**
     * 既没有历史纪绿，也没有开始搜索，空间面
     */
    private LinearLayout emptyLayout;


    /**
     * 正在搜索界面
     */
    private LinearLayout searchingLayout;
    private ListView searchingListView;
   // private SearchAdapter searchingAdapter;
    private LinearLayout searchingEmptyLayout;
    private TextView searchNoView;

    /**
     * data
     */
    private ArrayList<BaseModel> historyListData;
    private ArrayList<BaseModel> searchingListData;
    //private ProductModel fundModel;


    @Override
    protected int getContentLayoutId() {
        return R.layout.main_activity_search;
    }

    @Override
    protected void initWidget() {

        changeStatusBarColor(R.color.white);

        View root = findViewById(R.id.searchview);
        if(root !=null){
            Log.i("SearchActivity","ok");
            ViewHelper viewHelper = new ViewHelper(root,this,this);
            viewHelper.listener();
        }

        inputEditText = (EditText) findViewById(R.id.fund_search_view);
        cancelView = (TextView) findViewById(R.id.cancel_view);

        historyLayout = (LinearLayout) findViewById(R.id.fund_history_layout);







    }

    @Override
    protected void initData() {

    }

    @Override
    public void toFinshView() {
        LogUtils.i("toFinshView","toFinshView");
        finish();
    }
}
