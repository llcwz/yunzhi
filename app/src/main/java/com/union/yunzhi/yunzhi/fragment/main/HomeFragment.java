package com.union.yunzhi.yunzhi.fragment.main;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.union.yunzhi.common.app.AdBrowserActivity;
import com.union.yunzhi.common.app.FragmentM;
import com.union.yunzhi.common.app.PermissionsFragment;
import com.union.yunzhi.common.constant.Constant;
import com.union.yunzhi.common.helper.GlideImageLoader;
import com.union.yunzhi.common.util.LogUtils;
import com.union.yunzhi.factories.moudles.home.homeModle;
import com.union.yunzhi.factories.moudles.home.videoClassModle;
import com.union.yunzhi.factories.moudles.home.videoModle;
import com.union.yunzhi.factories.moudles.hometest.BaseHomeModle;
import com.union.yunzhi.factories.moudles.hometest.HomeBodyModle;
import com.union.yunzhi.factories.moudles.hometest.HomeHeadModle;
import com.union.yunzhi.factories.okhttp.listener.DisposeDataListener;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.activities.SearchActivity;
import com.union.yunzhi.yunzhi.adapter.HomeAdapter;
import com.union.yunzhi.yunzhi.manager.MyQrCodeDialog;
import com.union.yunzhi.yunzhi.network.RequestCenter;
import com.union.yunzhi.yunzhi.zxing.app.CaptureActivity;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link FragmentM} subclass.
 *
 */
public class HomeFragment extends PermissionsFragment implements View.OnClickListener {

    private static final int REQUEST_QRCODE = 0x01;

    private RecyclerView recyclerView;

    private LinearLayout toolbarLayout;

    private LinearLayout mSearchLayout;




    private Banner mBanner;


    //扫码按钮
    private CircleImageView mQRcode;

    private CircleImageView Test;


    private HomeAdapter mHomeAdapter ;
    List<HomeBodyModle> list = new ArrayList<>();

    private final String TGA = "HomeFragment";

    @Override
    protected int getContentLayoutId() {
        return R.layout.main_fragment_home;
    }

    @Override
    protected void initWidget(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        toolbarLayout = (LinearLayout) view.findViewById(R.id.toolbar_layout);
        mQRcode = (CircleImageView) toolbarLayout.findViewById(R.id.cv_qrcode);
        mQRcode.setOnClickListener(this);
        Test = (CircleImageView) view.findViewById(R.id.test);
        Test.setOnClickListener(this);


        mBanner = (Banner) view.findViewById(R.id.banner);


        mSearchLayout = (LinearLayout) view.findViewById(R.id.ll_search);
        mSearchLayout.setOnClickListener(this);



        RequestCenter.requestHomeData("", "", new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                Log.i("onSuccess",responseObj.toString());
                datas(responseObj);
            }

            @Override
            public void onFailure(Object reasonObj) {
                Log.i("onFailure","error");
            }
        });

    }

    @Override
    protected void initData() {


        LogUtils.i(TGA,"initWidget");


       // mHomeAdapter = new HomeAdapter(getContext(),4);
        data();

        recyclerView.setAdapter(mHomeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Log.i("source",list.size()+"");


    }

    @Override
    protected void initArgs(Bundle bundle) {
        super.initArgs(bundle);
        mHomeAdapter = new HomeAdapter(getContext(),4);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.i(TGA,"onCreate");
    }

    public void data(){




        homeModle homeModle = new homeModle();
        homeModle.viewType = 1;
        homeModle.mVideoClassModle = new videoClassModle();
        homeModle.mVideoClassModle.iconUrl = "http://pic25.nipic.com/20121111/10204421_222218120176_2.jpg";
        homeModle.mVideoClassModle.videoClass = "test1";
        homeModle.mVideoClassModle.videoModle = new ArrayList<>();
        videoModle video = new videoModle();
        video.PhotoUrl = "http://pic25.nipic.com/20121111/10204421_222218120176_2.jpg";
        video.PortraitUrl = "http://pic25.nipic.com/20121111/10204421_222218120176_2.jpg";
        video.Title = "test_01";
        homeModle.mVideoClassModle.videoModle.add(video);
        homeModle.mVideoClassModle.videoModle.add(video);
        homeModle.mVideoClassModle.videoModle.add(video);
        homeModle.mVideoClassModle.videoModle.add(video);
     //   list.add(homeModle);

        Log.i(TGA,"data"+list.size());

        //mHomeAdapter.add(list);


    }

    public void datas(Object object){
        BaseHomeModle data = (BaseHomeModle)object;

        HomeHeadModle head = data.data.head;


        initBanner(head.ads);

        for(int i=0;i<data.data.list.size();i++){
            HomeBodyModle homeBody = data.data.list.get(i);
            list.add(homeBody);
        }

        for(int i=0;i<data.data.head.ads.size();i++){
            LogUtils.i(TGA+"    ",data.data.head.ads.get(i).toString());
        }

        for(int i=0;i<data.data.list.size();i++){
            LogUtils.i(TGA+" PhotoUrl   ",data.data.list.get(i).PhotoUrl);
            LogUtils.i(TGA+" PortraitUrl   ",data.data.list.get(i).PortraitUrl);
            LogUtils.i(TGA+" Title   ",data.data.list.get(i).Title);
            LogUtils.i(TGA+" viewType   ",data.data.list.get(i).viewType+"");
        }

       // list.add(data);

         mHomeAdapter.add(list);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cv_qrcode:
                if(hasPermission(Constant.HARDWEAR_CAMERA_PERMISSION)){
                    doOpenCamera();
                }
                else {
                    requestPermission(Constant.HARDWEAR_CAMERA_CODE, Constant.HARDWEAR_CAMERA_PERMISSION);
                }
                break;

            case R.id.ll_search:
                startActivity(new Intent(getContext(),
                        SearchActivity.class));
                break;

            case R.id.test:
                MyQrCodeDialog dialog = new MyQrCodeDialog(getContext());
                dialog.show();

                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case REQUEST_QRCODE:
                if (resultCode == Activity.RESULT_OK) {
                    String code = data.getStringExtra("SCAN_RESULT");

                    Log.i("REQUEST_QRCODE",code);

                    if (code.contains("http") || code.contains("https")) {
                      //跳转到相应的地方
                        Intent intent = new Intent(getContext(), AdBrowserActivity.class);
                        intent.putExtra(AdBrowserActivity.KEY_URL, code);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getContext(), code, Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }//end switch
    }


    @Override
    public void doOpenCamera() {
        super.doOpenCamera();
        Intent intent = new Intent(getContext(),CaptureActivity.class);
        startActivityForResult(intent, REQUEST_QRCODE);
    }


    @Override
    public void initRefreshData() {
        super.initRefreshData();
    }

    public void initBanner(ArrayList<String> list){
        mBanner.setImageLoader(new GlideImageLoader());
            mBanner.setImages(list);
            mBanner.start();
    }
}
