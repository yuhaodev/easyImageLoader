package com.example.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Class: DiskCache
 * @Description:()
 * @Date: 2017/7/12  16:57
 * @Company: WKJ
 * @Author: yuhao
 * @Version: v1.0
 */

public class DiskCache implements ImageCache{
    static String cacheDir = Environment.getExternalStorageDirectory() + "/cache/";
    //从缓存中取图片
    public Bitmap get(String url){
        return BitmapFactory.decodeFile(cacheDir + url);
    }

    //将图片缓存到内存中
    public void put(String url,Bitmap bitmap){
        FileOutputStream fileOutputStream=null;
        try {
            fileOutputStream = new FileOutputStream(cacheDir + url);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(null!=fileOutputStream) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
