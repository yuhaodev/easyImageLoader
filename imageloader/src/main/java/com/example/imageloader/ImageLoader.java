package com.example.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Class: ImageLoader
 * @Description:()
 * @Date: 2017/7/12  15:44
 * @Company: TWKJ
 * @Author: yuhao
 * @Version: v1.0
 */

public class ImageLoader {
    //图片缓存
    ImageCache mImageCache = new MemoryCache();
    //    LruCache<String,Bitmap> mImageCache;
    //SD卡缓存
    DiskCache mDiskCache = new DiskCache();
    //双缓存
    DoubleCache mDoubleCache = new DoubleCache();

    boolean isUseDiskCache = false;
    boolean isUseDoubleCache = false;
    //线程池，数量为CPU的数量
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

//    public ImageLoader() {
//        initImageCache();
//    }
    public void setImageCache(ImageCache cache){
        mImageCache = cache;
    }

    public  void displayImage(String imageUrl,ImageView imageView){
        Bitmap bitmap = mImageCache.get(imageUrl);
        if(null!=bitmap){
            imageView.setImageBitmap(bitmap);
            return;
        }
        //图片没缓存，提交到线程池下载图片
        submitLoadRequest(imageUrl,imageView);


    }

    private void submitLoadRequest(final String ImageUrl,final ImageView imageView){

        imageView.setTag(ImageUrl);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImage(ImageUrl);
                if (null == bitmap) {
                    return;
                }
                if(imageView.getTag().equals(ImageUrl)){
                    imageView.setImageBitmap(bitmap);
                }
                mImageCache.put(ImageUrl,bitmap);
            }
        });

    }

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
//    public void setUseDiskCache(boolean useDiskCache){
//        isUseDiskCache = useDiskCache;
//    }
//    public void setmDoubleCache(boolean useDoubleCache){
//        isUseDoubleCache = useDoubleCache;
//    }

//     public void displayImage(final String url, final ImageView imageView){
//         //判断使用哪种缓存方式
////         Bitmap bitmap = isUseDiskCache ? mDiskCache.get(url) : mImageCache.get(url);
//         Bitmap bitmap = null;
//         if(isUseDoubleCache){
//             bitmap = mDoubleCache.get(url);
//
//         }else if(isUseDiskCache){
//             bitmap = mDiskCache.get(url);
//         }else {
//             bitmap = mImageCache.get(url);
//         }
//
//
////         Bitmap bitmap=mImageCache.get(url);
//         if(null!=bitmap){
//             imageView.setImageBitmap(bitmap);
//             return;
//         }
//         imageView.setTag(url);
//         Log.e("url", " 图片已经添加tag               " );
//         mExecutorService.submit(new Runnable() {
//             @Override
//             public void run() {
//                 Log.e("url", "null==bitmap;" );
//                 Bitmap bitmap = downloadImage(url);
//                 if(null==bitmap){
//                     Log.e("url", "null==bitmap;" );
//                     return;
//                 }else{
//                     Log.e("url", "null!=bitmap;" );
//                 }
//                 if(imageView.getTag().equals(url)){
//                     imageView.setImageBitmap(bitmap);
//                     Log.e("url", " 图片已经加载上去                coon.disconnect();" );
//                     mImageCache.put(url, bitmap);
//                 }else{
//                     Log.e("url", "!!!!        imageView.getTag().equals(url);" );
//                 }
//             }
//         });
//     }
     public Bitmap downloadImage(String imageUrl){
         Bitmap bitmap=null;
         try {
             URL url = new URL(imageUrl);
             Log.e("url", "url=" + url);
             HttpURLConnection coon = (HttpURLConnection) url.openConnection();
             bitmap = BitmapFactory.decodeStream(coon.getInputStream());
             Log.e("url", "                 coon.disconnect();" );
             coon.disconnect();
         } catch (MalformedURLException e) {
             e.printStackTrace();
             Log.e("url", "MalformedURLException;" +e.toString());
         } catch (IOException e) {
             e.printStackTrace();
             Log.e("url", "IOException;" +e.toString());
         }
         return bitmap;
     }
}
