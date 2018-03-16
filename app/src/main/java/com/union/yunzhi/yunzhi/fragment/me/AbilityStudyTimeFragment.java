package com.union.yunzhi.yunzhi.fragment.me;

import android.graphics.Color;
import android.graphics.Point;
import android.view.View;

import com.union.yunzhi.common.app.FragmentM;
import com.union.yunzhi.yunzhi.R;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by 62588 on 2018/3/16.
 */

public class AbilityStudyTimeFragment extends FragmentM {
    private LineChartView chart;

    private List<Line> lines;
    private int numberOfPoints = 7;
    private String[] xdatas = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    private List<AxisValue> mAxisXValues;
    private List<PointValue> mPointValues;
    private int[] time = {5, 9, 8, 3, 1, 6, 1};

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_me_ability_studytime;
    }

    @Override
    protected void initWidget(View view) {
        chart = (LineChartView) view.findViewById(R.id.chart_ability_study);
    }

    @Override
    protected void initData() {

        getAxisXLables();
        getAxisPoints();
        initChart();

    }
    private void initChart(){
        Line line = new Line(mPointValues).setColor(Color.parseColor("#4dd0e1"));
        line.setCubic(true);
        lines = new ArrayList<>();
        lines.add(line);
        chart.setInteractive(true);
        chart.setZoomType(ZoomType.VERTICAL);
        LineChartData data = new LineChartData(lines);
        Axis x = new Axis();
        x.setValues(mAxisXValues);
        Axis y = new Axis();
        y.setHasLines(false);
        data.setLines(lines);
        data.setAxisXBottom(x);
        data.setAxisYLeft(y);
        chart.setLineChartData(data);
        resetViewport();
    }
    private void resetViewport() {
        final Viewport v = new Viewport(chart.getMaximumViewport());
        v.bottom = 0;
        v.top = 12;
        v.left = 0;
        v.right = numberOfPoints - 1;
        chart.setMaximumViewport(v);
        chart.setCurrentViewport(v);
    }

    private void getAxisXLables() {
        mAxisXValues = new ArrayList<>();
        for (int i = 0; i < xdatas.length; i++) {
            mAxisXValues.add(new AxisValue(i).setLabel(xdatas[i]));
        }
    }
    private void getAxisPoints() {
        mPointValues = new ArrayList<>();
        for (int i = 0; i < time.length; i++) {
            mPointValues.add(new PointValue(i, time[i]));
        }
    }

    private void Analysis(){

    }
}