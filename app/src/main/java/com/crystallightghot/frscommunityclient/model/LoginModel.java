package com.crystallightghot.frscommunityclient.model;

import com.crystallightghot.frscommunityclient.contract.LoginContract;
import com.crystallightghot.frscommunityclient.contract.RequestCallBack;
import com.crystallightghot.frscommunityclient.presenter.LoginPresenter;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.FRSCThreadPoolUtil;
import com.crystallightghot.frscommunityclient.view.util.FRSCOKHttp3RequestUtil;
import com.crystallightghot.frscommunityclient.view.value.FRSCRequestIO;
import com.crystallightghot.frscommunityclient.view.pojo.system.RequestResult;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @Date 2022/1/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class LoginModel implements LoginContract.Model {


    public LoginModel() {
    }

    public void login(String userJson, RequestCallBack callback) {
        String url = FRSCRequestIO.SystemIO.USER_LOGIN.getRequestIO();
        FRSCOKHttp3RequestUtil.postRequestWithBodyJson(url, userJson, callback);
    }

    public void isLogin(LoginPresenter presenter) {
        String url = FRSCRequestIO.SystemIO.USER_LOGIN.getRequestIO();
        User user = FRSCApplicationContext.getUser();
        String head = "";
        if (null != user) {
            head = user.getSessionId();
        }

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .addHeader("Authorization", "FRSC" + head)
                .url(url)
                .build();
        Runnable runnable = () -> {
            try {
                Response response = client.newCall(request).execute();
                String string = response.body().string();
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                // 获取返回结果信息
                RequestResult requestResult = gson.fromJson(string, RequestResult.class);
                boolean success = requestResult.isSuccess();
                if (success) {//如果成功

                } else {// 请求失败

                }
            } catch (IOException e) {
                e.printStackTrace();

            }
        };
        FRSCThreadPoolUtil.executeThread(runnable);
    }


}
