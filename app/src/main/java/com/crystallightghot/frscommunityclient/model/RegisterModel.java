package com.crystallightghot.frscommunityclient.model;

import com.crystallightghot.frscommunityclient.contract.IRegisterContract;
import com.crystallightghot.frscommunityclient.utils.NetConfigurationInterface;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.crystallightghot.frscommunityclient.view.util.ThreadPoolUtil;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;

/**
 * @Date 2022/1/19
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class RegisterModel implements IRegisterContract.Model {


    @Override
    public void registerUser(User user, final IRegisterContract.LoadDataCallBack callback) {

        Gson gson = new Gson();
        String userJson = gson.toJson(user);
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, userJson);

        OkHttpClient client = new OkHttpClient();
        String url = NetConfigurationInterface.getRegisterUserUrl();
        Request request = new Request.Builder()
                .post(requestBody)
                .url(url)
                .build();
        Runnable runnable = () -> {
            try {
                Response response = client.newCall(request).execute();
                callback.success(response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
                callback.failure();
            }
        };
        ThreadPoolUtil.executeThread(runnable);
    }
}
