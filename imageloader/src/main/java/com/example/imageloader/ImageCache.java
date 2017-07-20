package com.example.imageloader;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * @Class: ImageCache
 * @Description:()
 * @Date: 2017/7/12  16:20
 * @Company: WKJ
 * @Author: yuhao
 * @Version: v1.0
 */

public interface ImageCache {
//    //图片LRU缓存
//    LruCache<String,Bitmap> mImageCache;
//
//    public ImageCache() {
//        initImageCache();
//    }
//
//    private void initImageCache(){
//            //计算可使用的最大内存
//        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
//        //取四分之一的可用内存为缓存
//        int cacheSize=maxMemory/4;
//        mImageCache=new LruCache<String,Bitmap>(cacheSize){
//            @Override
//            protected int sizeOf(String key, Bitmap bitmap) {
//                return bitmap.getRowBytes()*bitmap.getHeight()/1024;
//            }
//        };
//     }
//     public void put(String url,Bitmap bitmap){
//         mImageCache.put(url, bitmap);
//
//     }
//     public Bitmap get(String url){
//         return mImageCache.get(url);
//     }
    public Bitmap get(String url);
    public void put(String url,Bitmap bmp);

}
