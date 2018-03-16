package com.union.yunzhi.yunzhi.fragment.me;

import android.view.View;
import android.widget.TextView;

import com.union.yunzhi.common.app.FragmentM;
import com.union.yunzhi.yunzhi.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;

/**
 * Created by 62588 on 2018/3/16.
 */

public class AbilityStudyScoreFragment extends FragmentM {

    private ColumnChartView chart;
    private TextView tv_sum;//总次数
    private TextView tv_avg;
    private List<AxisValue> mAxisXValues;
    private ColumnChartData data;
    private String[] xdatas = {"软件工程", "java", "c++",".NET","数据库","android"};
    private int[]   score=new int[xdatas.length];
    private List<Column> columnList = new ArrayList<>(); //柱子列表
    private String[] colors={};
    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_my_ability_score;
    }

    @Override
    protected void initWidget(View view) {
             chart=(ColumnChartView)view.findViewById(R.id.chart_ability_score);
             tv_avg=(TextView)view.findViewById(R.id.tv_ability_score_avg);
             tv_sum=(TextView)view.findViewById(R.id.tv_ability_score_sum);
    }

    @Override
    protected void initData() {
        getAxisXLables();
        getColumnValue();
        initChart();
        tv_sum.setText("总考试次数\n"+xdatas.length);
        int sum=0;
        for(int i=0;i<score.length;i++){
            sum+=score[i];
        }
        tv_avg.setText("平均成绩\n"+sum/score.length);
    }
    private void initChart(){

        Axis x=new Axis();
        x.setValues(mAxisXValues);
        Axis y=new Axis();
        x.setName("科目");
        x.setTextSize(16);
        y.setName("成绩");
        data=new ColumnChartData(columnList);
        data.setAxisYLeft(y);
        data.setAxisXBottom(x);
        chart.setColumnChartData(data);
        chart.setInteractive(false);
        resetViewport();
    }
    private void getColumnValue(){
         List<SubcolumnValue> subcolumnValueList;
         for(int i=0;i<xdatas.length;i++){
             subcolumnValueList=new ArrayList<>();
             int ran=(int)(Math.random() * 100);
             subcolumnValueList.add(new SubcolumnValue( ran, ChartUtils.pickColor()));
             score[i]=ran;
             Column column=new Column(subcolumnValueList);
             column.setHasLabels(true);
             columnList.add(column);
         }
    }

    private void  getAxisXLables() {
        mAxisXValues = new ArrayList<>();
        for (int i = 0; i < xdatas.length; i++) {
            mAxisXValues.add(new AxisValue(i).setLabel(xdatas[i]));
        }
    }
    private void resetViewport() {
        final Viewport v = new Viewport(chart.getMaximumViewport());
        v.bottom = 0;
        v.top = 103;
        v.left = -1;
        v.right = xdatas.length ;//防止阻挡
        chart.setMaximumViewport(v);
        chart.setCurrentViewport(v);
    }
}
