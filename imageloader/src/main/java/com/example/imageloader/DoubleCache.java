package com.example.imageloader;

import android.graphics.Bitmap;

/**
 * @Class: DoubleCache
 * @Description:(TODO)
 * @Date: 2017/7/12  17:16
 * @Company: WKJ
 * @Author: yuhao
 * @Version: v1.0
 */

public class DoubleCache {
    ImageCache mMemorycache = new ImageCache();
    DiskCache mDiskCache = new DiskCache();
    //先从内存缓存中获取图片，如果没有，再从SD卡中获取
    public Bitmap get(String url){
        Bitmap bitmap = mMemorycache.get(url);
        if(null==bitmap){
            bitmap = mDiskCache.get(url);
        }
            return bitmap;
    }

    //将图片缓存到内存和SD卡中
    public void put(String url,Bitmap bmp){
        mMemorycache.put(url, bmp);
        mDiskCache.put(url,bmp);
    }
}
