package com.union.yunzhi.yunzhi.fragment.communication;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.union.yunzhi.common.app.FragmentM;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.communication.CommentModel;
import com.union.yunzhi.factories.moudles.communication.CommunicationConstant;
import com.union.yunzhi.factories.moudles.communication.LikeModel;
import com.union.yunzhi.factories.moudles.communication.PostModel;
import com.union.yunzhi.factories.moudles.communication.CommunicationModel;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.activities.communication.PostDetailsActivity;
import com.union.yunzhi.yunzhi.adapter.PostAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by CrazyGZ on 2018/3/9.
 */

public class PostFragment extends FragmentM {

    private static final String FRAGMENT_TAG = "TAG";
    private int mTag; // 标记fragment的生成
    private CommunicationModel mModel = new CommunicationModel(); // 测试数据
    private RecyclerView mRecyclerView;
    private PostAdapter mAdapter;

    public static PostFragment newInstance(int tag) {
        Bundle args = new Bundle();
        args.putInt(FRAGMENT_TAG,tag);
        PostFragment fragment = new PostFragment();
        fragment.setArguments(args);
        return fragment;
    }

    // 用于添加帖子后的刷新
    public void notifyList (PostModel postModel) {
        mAdapter.add(postModel);
        mAdapter.notify();
    }


    @Override
    protected void initArgs(Bundle bundle) {
        super.initArgs(bundle);
        mTag = bundle.getInt(FRAGMENT_TAG, -1);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.communication_fragment_college;
    }

    @Override
    protected void initWidget(View view) {
        initAdapterData(mTag);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler);
    }

    // 初始化适配器和数据
    private void initAdapterData(int tag) {
        data(tag);
        mAdapter = new PostAdapter(getContext(), mModel.getPostModels(), new MyAdapter.AdapterListener<PostModel>() {

            @Override
            public void onItemClick(MyAdapter.MyViewHolder holder, PostModel data) {
                // TODO: 2018/3/9  跳转到详情
                PostDetailsActivity.newInstance(getActivity(), data);
            }

            @Override
            public void onItemLongClick(MyAdapter.MyViewHolder holder, PostModel data) {

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

    /**
     * 这里是网络请求数据
     * 根据tag来获取不同的数据
     * @param tag
     */
    private void data(int tag) {
        List<PostModel> postModels = new ArrayList<>();
        List<CommentModel> commentModels = new ArrayList<>();
        List<LikeModel> likeModels = new ArrayList<>();
        switch (tag) {
            case CommunicationConstant.TAG_COLLEGE:
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

                    postModels.add(new PostModel("" + i,
                            mTag,
                            "https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=5b81e5e30ef41bd5da53eff269e1e6f6/d439b6003af33a87cfcf02d2c65c10385243b553.jpg",
                            "张三" + i,
                            "2018.3.5 16:2" + i,
                            "后勤携手龙舟队 敲鼓赶鱼拉开捕鱼序幕" + i,
                            "12月8日，沁湖传来阵阵鼓声。在后勤集团黄家湖物业服务中心书记杨方武的指导下，后勤集团工作人员正和龙舟队一起敲鼓赶鱼，为冬至捕鱼做准备。\n" +
                                    "\n" +
                                    " \n" +
                                    "\n" +
                                    " “往年都是后勤工作人员直接起网进行捕捞，但今年有龙舟帮我们捕鱼！”杨方武高兴地告诉记者。据了解，今年沁湖鱼数多，加之沁湖面积大，在整个湖撒网捕鱼难度较大。为解决这一难题，后勤集团携手龙舟队，利用龙舟队击鼓时的鼓声和桨的划动，将沁湖桥以西的鱼群赶至沁湖桥以东，方便冬至捕鱼。\n" +
                                    "\n" +
                                    " \n" +
                                    "\n" +
                                    " 下午2点，两艘龙舟顺利下水，每艘龙舟载有四人，一人击鼓，三人划桨。在龙舟队员的通力配合下，两艘龙舟从靠近恒大楼的水域开始击鼓赶鱼，一直将鱼赶到沁湖桥的东边。而后勤工作人员早已在东侧撒好渔网，等鱼群过来后便将其困于网中。\n" +
                                    "\n" +
                                    " \n" +
                                    "\n" +
                                    " 在近一个小时的赶鱼过程中，龙舟队成员们干劲十足，丝毫没有松懈。在锣鼓声中，许多红鲤鱼露出红色的脊背，从龙舟旁游过。坐在龙舟上，摄影记者计开禹也拿起船桨，加入赶鱼的队伍。计开禹表示在划船时胳膊十分酸痛，在赶鱼过程中，他深切地感受到了捕鱼的不易。\n" +
                                    "\n" +
                                    " \n" +
                                    "\n" +
                                    " “敲鼓赶鱼，在某种意义上也是向大家宣布又到了一年一度吃鱼的时候。” 杨方武介绍说。周围经过的同学们也纷纷驻足观看，医学院17级学生王欣激动地表示，早就听闻学校有吃鱼的活动，盼了那么久终于能吃到鱼了。\n" +
                                    "\n",
                            commentModels,
                            likeModels
                            ));
                }
                break;
            case CommunicationConstant.TAG_NOTE:

                for (int i = 0; i < 10; i++) {

                    likeModels.add(new LikeModel("" + i,
                            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1520750308948&di=b68aae83d50e75960a908bb44e904d95&imgtype=0&src=http%3A%2F%2Fimg2.utuku.china.com%2F298x0%2Fent%2F20170914%2Fc2d9a577-7d96-409b-916e-a3fc6f1fab90.png",
                            "敌人的盟友" + i,
                            "2018.3.11 11:5" + new Random().nextInt(9)));

                    commentModels.add(new CommentModel("" +i,
                            "https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/crop%3D56%2C0%2C636%2C420%3Bc0%3Dbaike80%2C5%2C5%2C80%2C26/sign=561a042db6096b63955604103106b168/c8ea15ce36d3d539ea5cccc13287e950352ab04b.jpg",
                            "水军" + i,
                            "2018.3.16 12:0" + i,
                            "不知道说什么，只好提前给大家拜个年了" + i,
                            likeModels));
                    postModels.add(new PostModel("" +i,
                            mTag,
                            "https://i2.sanwen.net/l/1520510319.jpg",
                            "徐军" + i,
                            "2018.3.5 16:2" + i,
                            "珍惜福报" + i,
                            "在琐碎平凡的现实生活中，如果不加强福报感恩教育，就经常会发生“树欲静而风不止，子欲养而亲不待！”这凄楚的悲哀。\n" +
                                    "\n" +
                                    "珍惜福报源于知恩、报恩。妈妈听说女儿要回娘家吃饭，一大早就急着奔市场开开心心地挑选食材，回到家里就忙忙碌碌地做饭，这样的场景，时常浮现在人们眼前。生命中有人这样关爱你、为你付出，你又该怎样珍惜福报呢？因为感恩，才会珍惜，珍惜当下拥有的一切。\n" +
                                    "\n" +
                                    "厨房事，看似平庸，其实高尚。每一顿美味的饭菜，皆要精选食材、细心清洗、精心切配、忍热耐寒，才能够烹饪而出。撑肠挂肚后，餐桌上、厨房上的残渣剩汁，是谁耐劳将它们清洗、整理？试想，如果某一天为我们做家务的人罢工了，这个家的温暖，还会在吗？\n" +
                                    "\n" +
                                    "君子以俭德避难，不可荣以禄。小时候外婆给我讲过一个故事，说从前湖南老家有个富农，经常将剩饭倒在水沟里，日积月累，常年不觉。富农的邻居是一户贫农人家，勤劳朴实节俭。那邻居每天都收拾水沟里的剩饭，将它们捞起、洗净、晒干，不知不觉地积攒了不少。\n" +
                                    "\n" +
                                    "天有不测风云，人有旦夕祸福。富农因奢靡无度，家道败落，竟落到三餐不继之苦。贫农见他落寞可怜，便经常接济他，富农千恩万谢，感激不尽。贫农却说：“不用谢，这些米其实都是您之前丢弃的，我只是把它们洗净保存起来。”富农看着碗中的米，涕泪横流，悔恨交加，幡然醒悟。如今虽然物质生活丰富了，我们也不该浪费粮食。居安思危，惜福得福，是为平安。( 文章阅读网：www.sanwen.net )\n" +
                                    "\n" +
                                    "“俭”有三益。第一，安分守己，无求于人，可以养廉；第二，减我身心之俸，以酬极苦之人，可以广德；第三，忍不足于目前，留有余于他日，可以福后。\n" +
                                    "\n" +
                                    "一粥一饭，当思来之不易；半丝半缕，恒念物力维艰。惜福、感恩让麻木变得珍贵和感人，牢记生命中为我们付出的人、事、物。每一粒米都饱含着日，店员昼夜销售，母亲早起购买，经过无数道爱心工序，才能够热腾腾地盛在碗里，暖在肚里，越是细微思索，越是热泪盈眶。\n" +
                                    "\n" +
                                    "岁寒知松柏，患难见真情。记得二十多年前，夏日某天突患重病，发热乏力，早早就寝。恍惚间，感受到妻子用手探额试温，这天夜里，妻子数次服侍喝水，在额头上反复晾上湿毛巾，她说：“夏天体乏很干燥，你一定要多喝水，可不能这样一直干烧下去。”第二天，安然烧退，脱离危险。南国湿热，按不住病子的心，若无妻子精心照顾，宁有日后的安康？将近半个月，妻子不是熬汤辅助调养，就是煮开胃软食施予欢喜，岂能不感恩戴德？\n" +
                                    "\n" +
                                    "“贫贱之交不可忘，糟糠之妻不下堂。”是华夏民族善德恩义。人生虽无常，天地常有情。祸福只在冥冥，并非腾空而降。珍惜福报，感恩所有帮助过你的人，故当积善成流，趋吉避凶，必能广得福报，兴旺康达。",
                            commentModels,
                            likeModels));
                }
                break;
            default:
        }
        mModel.setPostModels(postModels);
    }


    @Override
    protected void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
    }
}
