package com.union.yunzhi.yunzhi.activities;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.union.yunzhi.common.app.ActivityM;
import com.union.yunzhi.common.helper.ViewHelper;
import com.union.yunzhi.common.util.LogUtils;
import com.union.yunzhi.factories.moudles.BaseModel;
import com.union.yunzhi.yunzhi.R;

import java.util.ArrayList;

public class SearchActivity extends ActivityM implements ViewHelper.onFinshListener,View.OnClickListener {


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
    private RecyclerView historyListView;
    //private SearchAdapter historyAdapter;

    /**
     * 既没有历史纪绿，也没有开始搜索，空间面
     */
    private LinearLayout emptyLayout;


    /**
     * 正在搜索界面
     */
    private LinearLayout searchingLayout;
    private RecyclerView searchingListView;
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
        //侧滑销毁view
        View root = findViewById(R.id.searchview);
        if (root != null) {
            ViewHelper viewHelper = new ViewHelper(root, this, this);
            viewHelper.listener();
        }


        /**
         * UI初始化
         */
        inputEditText = (EditText) findViewById(R.id.fund_search_view);
        cancelView = (TextView) findViewById(R.id.cancel_view);


        /**
         * 历史界面初始化
         */
        historyLayout = (LinearLayout) findViewById(R.id.fund_history_layout);
        clearHistoryView = (TextView) historyLayout
                .findViewById(R.id.delect_histroy_view);
        historyListView = (RecyclerView) historyLayout
                .findViewById(R.id.history_list_view);

       ;

        // 空界面View
        emptyLayout = (LinearLayout) findViewById(R.id.empty_layout);

        // 正在搜索View
        searchingLayout = (LinearLayout) findViewById(R.id.fund_search_layout);
        searchingListView = (RecyclerView) searchingLayout
                .findViewById(R.id.fund_list_view);
        searchingEmptyLayout = (LinearLayout) searchingLayout
                .findViewById(R.id.fund_search_empty_layout);
        searchNoView = (TextView) searchingEmptyLayout
                .findViewById(R.id.seach_no_fund_info);

        cancelView.setOnClickListener(this);
        clearHistoryView.setOnClickListener(this);
        inputEditText.addTextChangedListener(watcher);

        decideWhichMode();
    }

    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            String selections = inputEditText.getText().toString();



        }
    };

    @Override
    protected void initData() {

    }

    @Override
    public void toFinshView() {
        LogUtils.i("toFinshView", "toFinshView");
        finish();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.cancel_view:
                finish();
                break;
            case R.id.delect_histroy_view:
                break;
        }
    }


    /**
     * decide to which mode
     */
    private void decideWhichMode() {
        if (getHistoryData() == 0) {
            entryEmptyMode();
        } else {
            entryHistoryMode();
        }
    }

    private int getHistoryData() {
        return 0;
    }

    /**
     * 进入空模式
     */
    private void entryEmptyMode() {
//        DBDataHelper.getInstance().delete(DBHelper.FUND_BROWSE_TABLE, null,
//                null);
        searchingLayout.setVisibility(View.GONE);
        historyLayout.setVisibility(View.GONE);
        emptyLayout.setVisibility(View.VISIBLE);
    }
    /**
     * 进入历史记录模式
     */
    private void entryHistoryMode() {
        emptyLayout.setVisibility(View.GONE);
        searchingLayout.setVisibility(View.GONE);
        historyLayout.setVisibility(View.VISIBLE);

    }

    private String getNoFundInfo(String info) {
        String sourceInfo = "<font color= #666666>"
                + getString(R.string.search_no_title) + "</font>"
                + "<font color= #ff3b3b>" + info + "</font>"
                + "<font color= #666666>" + getString(R.string.search_no_end)
                + "</font>";
        return sourceInfo;
    }

}
