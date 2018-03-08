package com.union.yunzhi.factories.moudles.live.beans;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.InputStream;

/**
 * Created by cjw on 2018/2/22 0022.
 */

public class LiveBean {

    /**
     * 用于测试的量
     */
    public int Id;
    public Context context;

    //关于封面图片的相关量
    public String imageUrl;
    public Bitmap bitmap;
    public float width,heigh;

    public String teacherName;
    public String courseName;
    public int lineCount;



    public LiveBean(Context context,int Id){

        /**
         * Width、Height以px为单位
         */
        //关于封面图片的相关量
        this.context=context;
        this.Id=Id;
        this.imageUrl=null;
        bitmap=resourceToBitmap(context,Id);
        width=bitmap.getWidth();
        heigh=bitmap.getHeight();
    }

    public LiveBean(Context context,String imageUrl){

        // TODO 异步加载方式 没有试验过
        SimpleTarget target = new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap bit, GlideAnimation glideAnimation) {
                //这里我们拿到回掉回来的bitmap，可以加载到我们想使用到的地方
                bitmap=bit;
                width=bitmap.getWidth();
                heigh=bitmap.getHeight();
            }
        };
        this.context=context;
        Id=-1;
        Glide.with(context).load(imageUrl).asBitmap().into(target);
    }

    public static Bitmap resourceToBitmap(Context context, int Id){

        Resources resources=context.getResources();
        InputStream is=resources.openRawResource(Id);
        BitmapDrawable bitmapDrawable=new BitmapDrawable(is);

        return bitmapDrawable.getBitmap();
    }
}
