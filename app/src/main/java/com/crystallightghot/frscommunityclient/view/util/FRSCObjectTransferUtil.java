package com.crystallightghot.frscommunityclient.view.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Date 2022/2/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class FRSCObjectTransferUtil {

    public static <T> List<T> listMapToListObject(ArrayList<Map> lists, Class<T> clazz) {
        if (null == lists) {
            return null;
        }
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        ArrayList<T> objects = new ArrayList();
        for (int i = 0; i < lists.size(); i++) {
            Map map =  lists.get(i);
            String toJson = gson.toJson(map);
            T O = gson.fromJson(toJson, clazz);
            objects.add(O);
        }
        return objects;
    }

    public static <C> String objectToJsonString(Object o) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.toJson(o);
    }

    public static <C> C mapToObject(Map map,Class<C> clazz) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.fromJson(gson.toJson(map),clazz);
    }

    public static Gson getGsonWithTimeForm() {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    }
}
