package com.crystallightghot.frscommunityclient.view.util;

import com.crystallightghot.frscommunityclient.contract.RespondCallBck;
import com.crystallightghot.frscommunityclient.view.pojo.system.RequestResult;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;

/**
 * @Date 2022/1/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class OKHttpRequestUtil {

    public static void getWithAuthorizationHeader(String url, RespondCallBck callback) {

        User user = FRSCApplicationContext.getUser();
        String head = "";
        if (null != user){
            head = user.getSessionId();
        }

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .addHeader("Authorization","FRSC"+head)
                .url(url)
                .build();
        Runnable runnable = () -> {
            try {
                Response response = client.newCall(request).execute();
                String string = response.body().string();
                Gson gson = new Gson();
                // 获取返回结果信息
                RequestResult requestResult = gson.fromJson(string, RequestResult.class);
                boolean success = requestResult.isSuccess();
                if (success) {//如果成功
                    callback.success(requestResult.getMessage(), requestResult.getData());
                } else {// 请求失败
                    callback.failure(requestResult.getMessage());
                }
            } catch (IOException e) {
                e.printStackTrace();
                callback.failure("失败");
            }
        };
        ThreadPoolUtil.executeThread(runnable);
    }
public static void deleteRequestWithBodyJson(String url, String jsonBody, RespondCallBck callback) {
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, jsonBody);
        User user = FRSCApplicationContext.getUser();

        String head = "";
        if (null != user){
            head = user.getSessionId();
        }

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .delete(requestBody)
                .addHeader("Authorization","FRSC"+head)
                .url(url)
                .build();
        Runnable runnable = () -> {
            try {
                Response response = client.newCall(request).execute();
                String string = response.body().string();
                Gson gson = new Gson();
                // 获取返回结果信息
                RequestResult requestResult = gson.fromJson(string, RequestResult.class);
                boolean success = requestResult.isSuccess();
                if (success) {//如果成功
                    callback.success(requestResult.getMessage(), requestResult.getData());
                } else {// 请求失败
                    callback.failure(requestResult.getMessage());
                }
            } catch (IOException e) {
                e.printStackTrace();
                callback.failure("失败");
            }
        };
        ThreadPoolUtil.executeThread(runnable);
    }

    public static void postRequestWithBodyJson(String url, String jsonBody, RespondCallBck callback) {

        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, jsonBody);

        User user = FRSCApplicationContext.getUser();

        String head = "";
        if (null != user){
            head = user.getSessionId();
        }

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .post(requestBody)
                .addHeader("Authorization","FRSC"+head)
                .url(url)
                .build();
        Runnable runnable = () -> {
            try {
                Response response = client.newCall(request).execute();
                String string = response.body().string();
                Gson gson = new Gson();
                // 获取返回结果信息
                RequestResult requestResult = gson.fromJson(string, RequestResult.class);
                boolean success = requestResult.isSuccess();
                if (success) {//如果成功
                    callback.success(requestResult.getMessage(), requestResult.getData());
                } else {// 请求失败
                    callback.failure(requestResult.getMessage());
                }
            } catch (IOException e) {
                e.printStackTrace();
                callback.failure("失败");
            }
        };
        ThreadPoolUtil.executeThread(runnable);
    }
}
