package com.union.yunzhi.common.app;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.union.yunzhi.common.R;
import com.union.yunzhi.common.constant.Constant;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by meng on 2018/3/9.
 * @function 动态权限集成
 */

public abstract class PermissionsActivity extends ActivityM {
    //权限回调标志
    private static final int RC = 0x0100;


    /******************************************************************
     *
     *
     * 动态权限，没有该权限不能进入程序。
     *
     *
     ******************************************************************/


    /**
     * 检查是否有读写录音的权限
     *
     * @param context
     * @return
     */
    private static boolean haveRecordAudioPerm(Context context) {
        //准备需要检查的权限
        String[] perms = new String[]{
                Manifest.permission.RECORD_AUDIO
        };
        return EasyPermissions.hasPermissions(context, perms);
    }

    @Override
    protected void initWindows() {
        super.initWindows();
        if(Build.VERSION.SDK_INT >= 23){
            //Log.i("initWindows","initWindows");
            haveAllPerm(this,getSupportFragmentManager());
        }
    }

    /**
     * 检查是否有写的权限
     *
     * @param context
     * @return
     */
    private static boolean haveWritePrem(Context context) {
        //准备需要检查的权限
        String[] perms = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        return EasyPermissions.hasPermissions(context, perms);
    }


    /**
     * 检查是否具有全部权限
     *
     * @param context Context
     * @param manager FragmentManager
     * @return 是否具有权限
     */
    public  boolean haveAllPerm(Context context, FragmentManager manager) {
        //检查是否全部权限
        boolean haveAll =
                haveWritePrem(context);
                       // && haveRecordAudioPerm(context);

        //如果没有则显示当前申请权限的界面
        if (!haveAll) {
            requestPerm();
        }else {
           // Toast.makeText(this,"成功s",Toast.LENGTH_SHORT).show();
        }
        return haveAll;
    }

    public  boolean haveAllPerm(Context context) {
        //检查是否全部权限
        boolean haveAll =
                haveWritePrem(context);
        return haveAll;
    }


    /**
     * 申请权限的方法
     */
    @AfterPermissionGranted(RC)
    public   void requestPerm() {
        String[] perms = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
              //  Manifest.permission.RECORD_AUDIO
        };

        if(EasyPermissions.hasPermissions(this,perms)){
            Toast.makeText(this,"成功",Toast.LENGTH_SHORT).show();

        }else{
            //动态的申请权限
            EasyPermissions.requestPermissions(this,getString(R.string.permision),
                    RC,perms);
            Toast.makeText(this,"申请",Toast.LENGTH_SHORT).show();
        }
    }


    /****************************************************************
     *
     * 触发式权限，程序进入可以不提前申请，但当用户触发时必须申请
     *
     *****************************************************************/

    /****************************************************************
     *
     * 触发式权限，程序进入可以不提前申请，但当用户触发时必须申请
     *
     *****************************************************************/

    /**
     * 申请指定的权限.
     */
    public void requestPermission(int code, String... permissions) {

        if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(permissions, code);
        }
    }

    /**
     * 判断是否有指定的权限
     */
    public boolean hasPermission(String... permissions) {

        for (String permisson : permissions) {
            if (ContextCompat.checkSelfPermission(this, permisson)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }


    /**
     * @function 权限回掉的部分
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Constant.HARDWEAR_CAMERA_CODE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    doOpenCamera();
                }
                break;
        }

    }

    /**
     * 业务逻辑处理方法
     */
    public void doOpenCamera() {

    }


}
