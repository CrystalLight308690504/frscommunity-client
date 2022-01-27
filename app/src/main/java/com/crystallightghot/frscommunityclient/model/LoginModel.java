package com.crystallightghot.frscommunityclient.model;

import com.crystallightghot.frscommunityclient.contract.LoginContract;
import com.crystallightghot.frscommunityclient.contract.RespondCallBck;
import com.crystallightghot.frscommunityclient.presenter.LoginPresenter;
import com.crystallightghot.frscommunityclient.utils.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.utils.ThreadPoolUtil;
import com.crystallightghot.frscommunityclient.utils.requestio.OKHttpRequestUtil;
import com.crystallightghot.frscommunityclient.utils.requestio.RequestIOE;
import com.crystallightghot.frscommunityclient.view.pojo.system.Result;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.google.gson.Gson;
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

    public void login(String userJson, RespondCallBck callback) {
        String url = RequestIOE.USER_Login.getRequestIO();
        OKHttpRequestUtil.postRequestWithBodyJson(url, userJson, callback);
    }

    public void isLogin(LoginPresenter presenter) {
        String url = RequestIOE.USER_Login.getRequestIO();
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
                Gson gson = new Gson();
                // 获取返回结果信息
                Result result = gson.fromJson(string, Result.class);
                boolean success = result.isSuccess();
                if (success) {//如果成功

                } else {// 请求失败

                }
            } catch (IOException e) {
                e.printStackTrace();

            }
        };
        ThreadPoolUtil.executeThread(runnable);
    }


}
