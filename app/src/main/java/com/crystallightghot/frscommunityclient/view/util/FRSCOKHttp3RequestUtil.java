package com.crystallightghot.frscommunityclient.view.util;

import com.crystallightghot.frscommunityclient.contract.RequestCallBack;
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
public class FRSCOKHttp3RequestUtil {

    public static void getWithAuthorizationHeader(String url, RequestCallBack callback) {

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
                callback.callBack(requestResult);
            } catch (IOException e) {
                e.printStackTrace();
                RequestResult requestResult = new RequestResult(false,404,"(ಥ﹏ಥ)服务器跑路了",null);
                callback.callBack(requestResult);
            }
        };
        FRSCThreadPoolUtil.executeThread(runnable);
    }
    public static void putWithAuthorizationHeader(String url, String jsonBody, RequestCallBack callback) {
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, jsonBody);
        User user = FRSCApplicationContext.getUser();
        String head = "";
        if (null != user){
            head = user.getSessionId();
        }

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .put(requestBody)
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
                callback.callBack(requestResult);
            } catch (IOException e) {
                e.printStackTrace();
                RequestResult requestResult = new RequestResult(false,404,"(ಥ﹏ಥ)服务器跑路了",null);
                callback.callBack(requestResult);
            }
        };
        FRSCThreadPoolUtil.executeThread(runnable);
    }
public static void deleteRequestWithBodyJson(String url, String jsonBody, RequestCallBack callback) {
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
                callback.callBack(requestResult);
            } catch (IOException e) {
                e.printStackTrace();
                RequestResult requestResult = new RequestResult(false,404,"(ಥ﹏ಥ)服务器跑路了",null);
                callback.callBack(requestResult);
            }
        };
        FRSCThreadPoolUtil.executeThread(runnable);
    }

    public static void postRequestWithBodyJson(String url, String jsonBody, RequestCallBack callback) {

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
                callback.callBack(requestResult);
            } catch (IOException e) {
                e.printStackTrace();
                RequestResult requestResult = new RequestResult(false,404,"(ಥ﹏ಥ)服务器跑路了",null);
                callback.callBack(requestResult);
            }
        };
        FRSCThreadPoolUtil.executeThread(runnable);
    }
}
