package com.union.yunzhi.yunzhi.fragment.classfication;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.union.yunzhi.common.app.FragmentM;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.classfication.CustomLinearLayoutManager;
import com.union.yunzhi.factories.moudles.classfication.beans.question.QuestionBean;
import com.union.yunzhi.factories.moudles.communication.CommentModel;
import com.union.yunzhi.factories.moudles.communication.LikeModel;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.activities.classfication.QuestionDetailsActivity;
import com.union.yunzhi.yunzhi.adapter.ClassQuestionAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by cjw on 2018/3/8 0008.
 */

public class ClassQuestionFragment extends FragmentM implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private FloatingActionButton mFloatingActionButton;
    private ClassQuestionAdapter mAdapter;
    private List<QuestionBean> mQuestionBeen = new ArrayList<>(); // 测试数据

    public static ClassQuestionFragment newInstance() {
        return new ClassQuestionFragment();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.class_fragment_details_question;
    }

    @Override
    protected void initWidget(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rec_question);
        mFloatingActionButton = (FloatingActionButton) view.findViewById(R.id.float_action_button);
    }

    @Override
    protected void initData() {
        initAdapter();
        CustomLinearLayoutManager linearLayoutManager=new CustomLinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mFloatingActionButton.setOnClickListener(this);
    }

    // 初始化适配器数据
    private void data() {
        List<CommentModel> commentModels = new ArrayList<>();
        List<LikeModel> likeModels = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            likeModels.add(new LikeModel("" + i,
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1520750308948&di=b68aae83d50e75960a908bb44e904d95&imgtype=0&src=http%3A%2F%2Fimg2.utuku.china.com%2F298x0%2Fent%2F20170914%2Fc2d9a577-7d96-409b-916e-a3fc6f1fab90.png",
                    "敌人的盟友" + i,
                    "2018.3.11 11:5" + new Random().nextInt(9)));

            commentModels.add(new CommentModel("" + i,
                    "https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/crop%3D56%2C0%2C636%2C420%3Bc0%3Dbaike80%2C5%2C5%2C80%2C26/sign=561a042db6096b63955604103106b168/c8ea15ce36d3d539ea5cccc13287e950352ab04b.jpg",
                    "水军" + i,
                    "2018.3.16 12:0" + i,
                    "不知道说什么，只好提前给大家拜个年了" + i,
                    likeModels));

            mQuestionBeen.add(new QuestionBean(""+ i,
                    "" + i,
                    "http://img2.imgtn.bdimg.com/it/u=470200890,2528990396&fm=27&gp=0.jpg",
                    "诸葛亮" + i,
                    "2018.3.5 16:2" + i,
                    "这内容对得起这标题！给老师赞一个嘞！嘿——走起！",
                    commentModels,
                    likeModels
            ));
        }

    }

    // 初始化适配器
    private void initAdapter() {
        data();
        mAdapter = new ClassQuestionAdapter(getActivity(), mQuestionBeen, new MyAdapter.AdapterListener<QuestionBean>() {
            @Override
            public void onItemClick(MyAdapter.MyViewHolder holder, QuestionBean data) {
                QuestionDetailsActivity.newInstance(getActivity(), data);
            }

            @Override
            public void onItemLongClick(MyAdapter.MyViewHolder holder, QuestionBean data) {

            }

            @Override
            public Boolean setAddActionContinue() {
                return null;
            }

            @Override
            public void updataUI(Object object) {

            }
        });
    }

    // 点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.float_action_button:

                break;
            default:
        }
    }
}
