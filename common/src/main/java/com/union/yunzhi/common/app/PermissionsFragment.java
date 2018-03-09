package com.union.yunzhi.common.app;

import android.Manifest;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.union.yunzhi.common.R;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by meng on 2018/3/9.
 * @function 动态权限集成
 */

public abstract class PermissionsFragment extends FragmentM {
    //权限回调标志
    private static final int RC = 0x0100;

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
                haveWritePrem(context)
                        && haveRecordAudioPerm(context);

        //如果没有则显示当前申请权限的界面
        if (!haveAll) {
            requestPerm();
        }
        return haveAll;
    }


    /**
     * 申请权限的方法
     */
    @AfterPermissionGranted(RC)
    private  void requestPerm() {
        String[] perms = new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO

        };

        if(EasyPermissions.hasPermissions(getContext(),perms)){
            Toast.makeText(getContext(),"成功",Toast.LENGTH_SHORT).show();

        }else{
            //动态的申请权限
            EasyPermissions.requestPermissions(this,getString(R.string.permision),
                    RC,perms);
        }
    }
}
