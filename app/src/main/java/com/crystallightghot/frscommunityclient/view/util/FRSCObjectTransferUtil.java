package com.crystallightghot.frscommunityclient.view.util;

import com.crystallightghot.frscommunityclient.view.pojo.blog.Blog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;

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

    public static <T> List<T> ListMapToListObject(ArrayList<Map> lists, Class<T> clazz) {
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
}
