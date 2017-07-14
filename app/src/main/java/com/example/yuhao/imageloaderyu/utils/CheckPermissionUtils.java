package com.example.yuhao.imageloaderyu.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ss on 2017/2/8.
 * 权限动态申请工具类
 */

public class CheckPermissionUtils {

    public static final int SETTINGS_REQ_CODE = 16061;

    /**
     * 于activity
     * @param activity
     * @param permissions
     * @param requestCode
     * @return
     */
    public static boolean permissionChecksFromActivity(Activity activity , String[] permissions , int requestCode){
        boolean isPermission;
        ArrayList<String> refusePermissionIndex = new ArrayList<>();
        //sdk是否需要动态申请权限
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return true;
        }


        //检查权限
        for (String permission : permissions){
            if (ActivityCompat.checkSelfPermission(activity,permission) == PackageManager.PERMISSION_DENIED){
                refusePermissionIndex.add(permission);
            }
        }
        if (refusePermissionIndex.size() <=0){
            isPermission = true;
        }else{

//            if (ActivityCompat.shouldShowRequestPermissionRationale())

//            boolean shouldShowRationale = false;
//            for (String perm : refusePermissionIndex){
//                shouldShowRationale = shouldShowRationale || ActivityCompat.shouldShowRequestPermissionRationale(activity , perm);
//            }
//            if (shouldShowRationale){
//
//
//            }else{
                ActivityCompat.requestPermissions(activity , refusePermissionIndex.toArray(new String[refusePermissionIndex.size()]) , requestCode);

//            }
                isPermission = false;

        }
        return isPermission;
    }

    /**
     * 于fragment
     * @param fragment
     * @param permissions
     * @param requestCode
     * @return
     */
    public static boolean permissionChecksFromFragment(Fragment fragment , String[] permissions , int requestCode){

        boolean isPermission;
        ArrayList<String> refusePermissionIndex = new ArrayList<>();
        //sdk是否需要动态申请权限
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return true;
        }
        for (String permission : permissions){
            if (ContextCompat.checkSelfPermission(fragment.getContext(),permission) == PackageManager.PERMISSION_DENIED){
                refusePermissionIndex.add(permission);
            }
        }
        if (refusePermissionIndex.size() <= 0){
            isPermission = true;
        }else{
            fragment.requestPermissions(refusePermissionIndex.toArray(new String[refusePermissionIndex.size()]),requestCode);
            isPermission = false;
        }
        return isPermission;

    }


    /**
     * 禁止开启权限后的提示dialog
     * @param activity
     * @param rationale
     * @param positiveButton
     * @param negativeButton
     * @param deniedPerms
     */
    public static void checkDeniedPermissionsNeverAskAgain(final Activity activity, String rationale, String positiveButton, String negativeButton, final List<String> deniedPerms) {
        AlertDialog dialog = new AlertDialog.Builder(activity)
                .setMessage(rationale)
                .setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                        Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
//                        intent.setData(uri);
//                        activity.startActivityForResult(intent , s);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(negativeButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.show();
    }
}
