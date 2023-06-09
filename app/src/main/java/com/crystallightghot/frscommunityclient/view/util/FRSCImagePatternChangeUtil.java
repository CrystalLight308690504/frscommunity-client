package com.crystallightghot.frscommunityclient.view.util;

import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;

/**
 * @author crystallightghost
 * @date 2022/1/6
 * @Version: 1.0
 * description：
 */
/*
 * @Description TODO
 * @Date 2022/1/6 8:55
 * @Created by CrystalLightGhost
 */
public class FRSCImagePatternChangeUtil {

    /**
     * Drawable转换成一个Bitmap
     *方法一
     * @param drawable drawable对象
     * @return
     */
    public static final Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap( drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static Bitmap rotateImage(Bitmap bitmap, int angleRotated){
         Matrix matrix = new Matrix();
        // 设置旋转角度
        matrix.setRotate(angleRotated);
        // 重新绘制Bitmap
        Bitmap   bitmapAfter = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return bitmapAfter;
    }

    public static Drawable getDrawableFromBase64(String base64Image) {
        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return new BitmapDrawable(decodedByte);
    }

}
