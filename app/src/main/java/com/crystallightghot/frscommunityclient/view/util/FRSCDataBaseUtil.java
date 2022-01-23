package com.crystallightghot.frscommunityclient.view.util;

import android.database.sqlite.SQLiteDatabase;
import com.crystallightghot.frscommunityclient.utils.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.pojo.system.DaoMaster;
import com.crystallightghot.frscommunityclient.view.pojo.system.DaoSession;

/**
 * @Date 2022/1/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class FRSCDataBaseUtil {

    public static DaoSession getWriteDaoSession(){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(FRSCApplicationContext.activity,"FRSC_db");
        SQLiteDatabase database = helper.getWritableDatabase();
        return new DaoMaster(database).newSession();
    }

    public static DaoSession getReadDaoSession() {

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(FRSCApplicationContext.activity, "FRSC_db");
        SQLiteDatabase database = helper.getReadableDatabase();
        return new DaoMaster(database).newSession();
    }

}
