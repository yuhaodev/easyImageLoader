package com.example.yuhao.imageloaderyu;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.imageloader.DiskCache;
import com.example.imageloader.DoubleCache;
import com.example.imageloader.ImageLoader;
import com.example.imageloader.MemoryCache;
import com.example.yuhao.imageloaderyu.utils.CheckPermissionUtils;
import com.example.yuhao.imageloaderyu.utils.PerimssionConfig;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (CheckPermissionUtils.permissionChecksFromActivity(MainActivity.this, new String[]{PerimssionConfig.PERMISSION_WRITE_EXTERNAL_STORAGE}, PerimssionConfig.CODE_WRITE_EXTERNAL_STORAGE)) {


            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView iv_myimage = (ImageView) findViewById(R.id.iv_myimage);
        ImageLoader imageLoader = new ImageLoader();
//        imageLoader.setUseDiskCache(true);
//        imageLoader.setImageCache(new MemoryCache());
//        imageLoader.setImageCache(new DiskCache());
        imageLoader.setImageCache(new DoubleCache());
        String url = "http://api.lovbook.net//static/image/cover.png";
        imageLoader.displayImage(url,iv_myimage);
    }

    /**
     * 处理权限申请回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        List<String> deniedPermission = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                deniedPermission.add(permissions[i]);
            }
        }
        if (deniedPermission.size() > 0) {
//            Toast.makeText(GifActivity.this, "为了您正常使用,请点击'设置'-'作書'-'权限'-打开存储权限", Toast.LENGTH_SHORT).show();
//            ToastUtil.showToast(GifActivity.this, "为了您正常使用,请点击'设置'-'作書'-'权限'-打开存储权限", Toast.LENGTH_SHORT);
        }

    }
}
