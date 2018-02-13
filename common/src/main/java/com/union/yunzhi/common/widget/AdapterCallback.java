package com.union.yunzhi.common.widget;

/**
 * Created by Administrator on 2018/1/29.
 */

public interface AdapterCallback<Datas> {
    void update(Datas data, MyAdapter.MyViewHolder<Datas> holder);
}
