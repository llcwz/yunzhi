package com.union.yunzhi.yunzhi.activities;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.RelativeLayout;

import com.union.yunzhi.common.app.PermissionsActivity;
import com.union.yunzhi.yunzhi.MainActivity;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.manager.SPManager;

public class LoadingActivity extends PermissionsActivity {

    private RelativeLayout mAdLayout;
    private RelativeLayout mCopyLayout;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (SPManager.getInstance().
                    getBoolean(SPManager.IS_SHOW_GUIDE, false)) {
                startActivity(new Intent(LoadingActivity.this,
                        MainActivity.class));
            } else {
             //   SPManager.getInstance().putBoolean(SPManager.IS_SHOW_GUIDE, true);
                startActivity(new Intent(LoadingActivity.this,
                        GuideActivity.class));
            }
            finish();
        }
    };

    @Override
    protected int getContentLayoutId() {
        hiddenStatusBar();
        return R.layout.main_activity_loading;
    }

    @Override
    protected void initWidget() {


        mAdLayout = (RelativeLayout) findViewById(R.id.ad_content_view);
        mCopyLayout = (RelativeLayout) findViewById(R.id.content_layout);

        /**
         * 预留广告加载
         */

        mHandler.sendEmptyMessageDelayed(0, 3000);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
