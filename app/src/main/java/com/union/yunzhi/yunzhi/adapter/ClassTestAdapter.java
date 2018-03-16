package com.union.yunzhi.yunzhi.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.classfication.ClassConst;
import com.union.yunzhi.factories.moudles.classfication.beans.CourseTestBean;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.activities.classfication.CourseTestActivity;

import java.util.List;

/**
 * Created by 62588 on 2018/3/9.
 */

public class ClassTestAdapter extends MyAdapter<CourseTestBean> {

    private Context context;

    public ClassTestAdapter(List<CourseTestBean> dataList, AdapterListener listener, Context context) {
        super(dataList, listener);
        this.context = context;
    }


    @Override
    protected int getItemViewType(int position, CourseTestBean data) {
        if(data.viewType== ClassConst.TSET_VIEW_TITLE)
            return R.layout.item_class_test_head;
        else
            return R.layout.item_class_test_body;
    }

    @Override
    protected MyViewHolder<CourseTestBean> onCreateViewHolder(View root, int viewType) {
        if(viewType==R.layout.item_class_test_head)
            return new titleViewHolder(root);
        else
            return new contentViewHolder(root);
    }

    class titleViewHolder extends MyViewHolder<CourseTestBean>{
        public TextView chapter;
        public titleViewHolder(View itemView) {
            super(itemView);
            chapter=(TextView)itemView.findViewById(R.id.item_examine_chapter);
        }

        @Override
        protected void onBind(CourseTestBean data, int position) {
            chapter.setText(data.chapter);
        }
    }
    class contentViewHolder extends MyViewHolder<CourseTestBean>{
        public TextView testname;
        public TextView deadline;
        public TextView score;
        public TextView start_btn;
        public contentViewHolder(View itemView) {
            super(itemView);
            testname=(TextView)itemView.findViewById(R.id.item_examine_name);
            deadline=(TextView)itemView.findViewById(R.id.item_examine_deadline);
            score=(TextView)itemView.findViewById(R.id.item_examine_score);
            start_btn=(TextView)itemView.findViewById(R.id.item_examine_submit_btn);
        }

        @Override
        protected void onBind(CourseTestBean data, int position) {
            testname.setText("    "+data.name);
            deadline.setText("    截止日期："+data.data);
           // score.setText(data.score);
            start_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"点击了",Toast.LENGTH_SHORT).show();
                    CourseTestActivity.newInstance(context);
                }
            });
        }
    }
}
