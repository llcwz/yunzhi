package com.union.yunzhi.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by cjw on 2018/2/26 0026.
 */

public class MyScrollView extends ScrollView {

    public interface OnScollChangedListener {

        void onScrollViewChanged(MyScrollView scrollView, int x, int y, int oldx, int oldy);

    }

    private OnScollChangedListener onScollChangedListener = null;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs,
                                int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setOnScollChangedListener(OnScollChangedListener onScollChangedListener) {
        this.onScollChangedListener = onScollChangedListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (onScollChangedListener != null) {
            onScollChangedListener.onScrollViewChanged(this, x, y, oldx, oldy);
        }
    }

}
