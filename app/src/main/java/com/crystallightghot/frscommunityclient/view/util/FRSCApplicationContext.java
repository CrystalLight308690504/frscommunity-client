package com.crystallightghot.frscommunityclient.view.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import androidx.appcompat.app.AppCompatActivity;
import com.crystallightghot.frscommunityclient.view.activity.BaseFragmentActivity;
import com.crystallightghot.frscommunityclient.view.activity.MainActivity;
import com.crystallightghot.frscommunityclient.view.pojo.skatingtype.SkatingType;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;

import java.util.List;

/**
 * @Date 2022/1/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */

public class FRSCApplicationContext {

    private static AppCompatActivity activity;
    private static MainActivity mainActivity;
    private static User user;
    private static BaseFragmentActivity baseFragmentActivity;
    private static List<SkatingType> skatingTypes;

    public static MainActivity getMainActivity() {
        return mainActivity;
    }

    public static void setMainActivity(MainActivity mainActivity) {
        FRSCApplicationContext.mainActivity = mainActivity;
    }

    public static Activity getActivity() {
        return activity;
    }

    public static void setActivity(AppCompatActivity activity) {
        FRSCApplicationContext.activity = activity;
    }

    public static BaseFragmentActivity getBaseFragmentActivity() {
        return baseFragmentActivity;
    }

    public static void setBaseFragmentActivity(BaseFragmentActivity baseFragmentActivity) {
        FRSCApplicationContext.baseFragmentActivity = baseFragmentActivity;
    }

    public static List<SkatingType> getSkatingTypes() {
        return skatingTypes;
    }

    public static void setSkatingTypes(List<SkatingType> skatingTypes) {
        FRSCApplicationContext.skatingTypes = skatingTypes;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        FRSCApplicationContext.user = user;
    }

    public static Drawable getUserProfile() {
        if (null != user) {
            String userProfileBase64 = user.getProfile();
            byte[] decodedString = Base64.decode(userProfileBase64, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            return new BitmapDrawable(decodedByte);
        } else {
            return null;
        }
    }


}
