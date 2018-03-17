package com.union.yunzhi.yunzhi.meutils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.union.yunzhi.factories.moudles.me.UserModel;
import com.union.yunzhi.yunzhi.manager.UserManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CrazyGZ on 2018/3/13.
 */

public class MeUtils {
    public static UserModel getUser() {
        if (UserManager.getInstance().hasLogined()) {
            return UserManager.getInstance().getUser();
        } else {
            return null;
        }
    }

    public interface OnShowPalleteListener {
        /**
         *
         * @param bitmap url返回的bitmap
         * @param swatches 分别为:
         *                 1、有活力的 2、有活力的暗色 3、有活力的亮色  4、柔和的 5、柔和的暗色 6、柔和的亮色
         */
        void show(Bitmap bitmap,List<Palette.Swatch> swatches);
    }

    // 网络图片的地址
    public static void showPalette(Context context, String url, final OnShowPalleteListener listener) {
        Glide.with(context)
                .load(url)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        if (resource != null) {
                            List<Palette.Swatch> swatches = new ArrayList<Palette.Swatch>();
                            Palette palette = Palette.from(resource).generate();
                            if (palette != null) {
                                Palette.Swatch vibrant = palette.getVibrantSwatch();//有活力的
                                Palette.Swatch vibrantDark = palette.getDarkVibrantSwatch();//有活力的，暗色
                                Palette.Swatch vibrantLight = palette.getLightVibrantSwatch();//有活力的，亮色
                                Palette.Swatch muted = palette.getMutedSwatch();//柔和的
                                Palette.Swatch mutedDark = palette.getDarkMutedSwatch();//柔和的，暗色
                                Palette.Swatch mutedLight = palette.getLightMutedSwatch();//柔和的,亮色
                                swatches.add(vibrant);
                                swatches.add(vibrantDark);
                                swatches.add(vibrantLight);
                                swatches.add(muted);
                                swatches.add(mutedDark);
                                swatches.add(mutedLight);
                                listener.show(resource, swatches);
                            } else {
                                listener.show(resource,swatches);
                            }
                        }
                    }
                });
    }

    public static void showNoMessage(int size,TextView noMessage, String hint) {
        if (size == 0) {
            noMessage.setText(hint);
            noMessage.setVisibility(View.VISIBLE);
        } else {
            noMessage.setVisibility(View.GONE);
        }
    }
}
