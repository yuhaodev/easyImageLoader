package com.example.imageloader;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * @Class: MemoryCache
 * @Description:()
 * @Date: 2017/7/20  13:31
 * @Company: TWKJ
 * @Author: yuhao
 * @Version: v1.0
 */

public class MemoryCache implements ImageCache{

    //    //图片LRU缓存
    LruCache<String,Bitmap> mImageCache;

    public MemoryCache() {
        //初始化LRU緩存
        mImageCache = new LruCache<>(40);
    }

    @Override
    public Bitmap get(String url) {
        return mImageCache.get(url);
    }

    @Override
    public void put(String url, Bitmap bmp) {
        mImageCache.put(url, bmp);
    }
}
