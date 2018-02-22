package com.union.yunzhi.yunzhi.live;

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

    private  int Id;
    private String imageUrl;
    private Bitmap bitmap;
    private Context context;
    private float width,heigh;

    public LiveBean(Context context,int Id){

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
        Glide.with(context).load(imageUrl).asBitmap().into(target);
        Id=-1;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Context getContext() {
        return context;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeigh() {
        return heigh;
    }

    public void setHeigh(float heigh) {
        this.heigh = heigh;
    }

    public static Bitmap resourceToBitmap(Context context, int Id){

        Resources resources=context.getResources();
        InputStream is=resources.openRawResource(Id);
        BitmapDrawable bitmapDrawable=new BitmapDrawable(is);

        return bitmapDrawable.getBitmap();
    }
}
