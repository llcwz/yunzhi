package com.union.yunzhi.yunzhi.fragment.me;

import android.view.View;

import com.union.yunzhi.common.app.FragmentM;
import com.union.yunzhi.yunzhi.R;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * Created by 62588 on 2018/3/16.
 */

public class AbilityCapacityFragment extends FragmentM {
    private PieChartView  chart;
    private List<SliceValue> values;
    private String[] xdatas = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri"};
    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_my_ability_capacity;
    }

    @Override
    protected void initWidget(View view) {
           chart=(PieChartView)view.findViewById(R.id.chart_ability_capacity);
    }

    @Override
    protected void initData() {
        initSliceValue();
        initchart();
    }
    private void initSliceValue(){
        values=new ArrayList<>();
        for(int i=0;i<xdatas.length;i++){
            int _c=i%5;
            SliceValue sliceValue = new SliceValue((float) Math.random() * 30 + 15, ChartUtils.COLORS[_c]);
            sliceValue.setLabel(xdatas[i]);
            values.add(sliceValue);
        }
    }
    private void initchart(){
        PieChartData data=new PieChartData(values);
        data.setHasLabels(true);
       // data.setHasLabelsOnlyForSelected(true);
        data.setHasCenterCircle(true);
        data.setSlicesSpacing(2);
        data.setCenterText1("知识评估");
        chart.setPieChartData(data);
    }
}
