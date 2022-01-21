package com.crystallightghot.frscommunityclient.utils.requestio;

import com.crystallightghot.frscommunityclient.contract.RespondCallBck;
import com.crystallightghot.frscommunityclient.utils.ThreadPoolUtil;
import com.crystallightghot.frscommunityclient.view.pojo.system.Result;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;

/**
 * @Date 2022/1/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class RequestDataUtil {
    public static void postRequestWithBodyJson(String url, String jsonBody, RespondCallBck callback) {

        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, jsonBody);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .post(requestBody)
                .url(url)
                .build();
        Runnable runnable = () -> {
            try {
                Response response = client.newCall(request).execute();
                String string = response.body().string();
                Gson gson = new Gson();
                // 获取返回结果信息
                Result result = gson.fromJson(string, Result.class);
                boolean success = result.isSuccess();
                if (success) {//如果成功
                    callback.success(result.getMessage(), result.getData());
                } else {// 请求失败
                    callback.failure(result.getMessage());
                }
            } catch (IOException e) {
                e.printStackTrace();
                callback.failure("失败");
            }
        };
        ThreadPoolUtil.executeThread(runnable);
    }
}
