package com.union.yunzhi.yunzhi.fragment.classfication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.union.yunzhi.common.app.FragmentM;
import com.union.yunzhi.yunzhi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 62588 on 2018/3/9.
 */

public class ClassTestContentFragment extends FragmentM implements View.OnClickListener{

    private TextView title;//题目
    private ImageView iv;//题目图片
    private List<TextView> tv_chooses;
    private int pos=-1;//当前是第几个题目
    public static final String TITLE="title";
    public static final String CHOOSE="choose";
    public static final String POS="pos";
    public View.OnClickListener onclick;

    public int last_choose=0;//判断当前是否选择了，0代表没有选择

    public int NORMAL_COLOR=R.color.white;
    public int CLICK_COLOR=R.color.colorPrimary;

    @Override
    public void onClick(View v) {
        Switch(last_choose,NORMAL_COLOR);
        switch(v.getId()){
            case  R.id.test_content_a:
                listener.OnChoose(pos,1);
                last_choose=1;
                break;
            case R.id.test_content_b:
                listener.OnChoose(pos,2);
                last_choose=2;
                break;
            case R.id.test_content_c:
                listener.OnChoose(pos,3);
                last_choose=3;
                break;
            case R.id.test_content_d:
                listener.OnChoose(pos,4);
                last_choose=4;
                break;
        }
        Switch(last_choose,CLICK_COLOR);
    }


    public interface  OnChooseListener{
         public void OnChoose(int pos,int choose);//当点击了选项后被调用
    }

    public OnChooseListener listener;



    public static ClassTestContentFragment newInstance(String title, List<String> chooses, int pos) {

        Bundle args = new Bundle();
        ClassTestContentFragment fragment = new ClassTestContentFragment();
        args.putString(TITLE,title);
        args.putInt(POS,pos);
        for(int i=0;i<chooses.size();i++){
            args.putString(CHOOSE+i,chooses.get(i));
        }
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getContentLayoutId() {
        return R.layout.class_fragment_detail_test_content;
    }
    @Override
    protected void initWidget(View view) {
            tv_chooses=new ArrayList<>();
            title=(TextView)view.findViewById(R.id.item_examine_content_title);
            tv_chooses.add((TextView)view.findViewById(R.id.test_content_a));
            tv_chooses.add((TextView)view.findViewById(R.id.test_content_b));
            tv_chooses.add((TextView)view.findViewById(R.id.test_content_c));
            tv_chooses.add((TextView)view.findViewById(R.id.test_content_d));
            for(int i=0;i<tv_chooses.size();i++){
                tv_chooses.get(i).setOnClickListener(this);
            }
    }
    @Override
    protected void initData() {
      /*  onclick=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Switch(last_choose,NORMAL_COLOR);
                 Toast.makeText(getContext(),"ssss",Toast.LENGTH_SHORT).show();
                 switch(v.getId()){
                     case  R.id.test_content_a:
                          listener.OnChoose(pos,1);
                          last_choose=1;
                         break;
                     case R.id.test_content_b:
                         listener.OnChoose(pos,2);
                         last_choose=2;
                         break;
                     case R.id.test_content_c:
                         listener.OnChoose(pos,3);
                         last_choose=3;
                         break;
                     case R.id.test_content_d:
                         listener.OnChoose(pos,4);
                         last_choose=4;
                         break;
                 }
                 Switch(last_choose,CLICK_COLOR);
            }
        };*/
        pos=getArguments().getInt(POS);
        String _title=getArguments().getString(TITLE);
        title.setText("  "+pos+"、  "+_title);
        char select='A';
        for(int i=0;i<4;i++){
            tv_chooses.get(i).setText(select+"、 "+getArguments().getString(CHOOSE+i));
            select++;
        }
    }
    public void Switch(int p,int color){
        if(p==0)
            return ;
        tv_chooses.get(p-1).setBackgroundColor(getResources().getColor(color));
    }
    public void setListener(OnChooseListener listener) {
        this.listener = listener;
    }
}
