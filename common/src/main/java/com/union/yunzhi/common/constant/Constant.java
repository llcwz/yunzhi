package com.union.yunzhi.common.constant;

import android.Manifest;
import android.os.Environment;

/**
 * Created by meng on 2018/3/9.
 */

public class Constant {
    /**
     * 权限常量相关
     */
    public static final int HARDWEAR_CAMERA_CODE = 0x01;
    public static final String[] HARDWEAR_CAMERA_PERMISSION = new String[]{Manifest.permission.CAMERA};


    /**
     * 整个应用文件下载保存路径
     * 自行添加对应路径
     */
    public static String APP_PHOTO_DIR = Environment.
            getExternalStorageDirectory().getAbsolutePath().
            concat("/yunzhi_business/photo/");
}
