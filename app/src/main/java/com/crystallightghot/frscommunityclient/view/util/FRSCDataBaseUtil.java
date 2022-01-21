package com.crystallightghot.frscommunityclient.view.util;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import com.crystallightghot.frscommunityclient.view.pojo.system.DaoMaster;
import com.crystallightghot.frscommunityclient.view.pojo.system.DaoSession;

/**
 * @Date 2022/1/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class FRSCDataBaseUtil {

    // 当前使用的activity
   public static Activity activity;
    public static DaoSession getWriteDaoSession(){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(activity,"FRSC_db");
        SQLiteDatabase database = helper.getWritableDatabase();
        return new DaoMaster(database).newSession();
    }

    public static DaoSession getReadDaoSession() {

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(activity, "FRSC_db");
        SQLiteDatabase database = helper.getReadableDatabase();
        return new DaoMaster(database).newSession();
    }

}
