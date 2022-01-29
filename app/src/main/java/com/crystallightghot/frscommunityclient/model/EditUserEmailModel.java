package com.crystallightghot.frscommunityclient.model;

import com.crystallightghot.frscommunityclient.contract.EditUserEmailContract;
import com.crystallightghot.frscommunityclient.presenter.EditUserEmailPresenter;
import com.crystallightghot.frscommunityclient.view.util.ThreadPoolUtil;
import com.crystallightghot.frscommunityclient.view.enums.RequestIOE;
import com.crystallightghot.frscommunityclient.view.pojo.system.RequestResult;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;

/**
 * @Date 2022/1/28
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class EditUserEmailModel implements EditUserEmailContract.Model {

    public void modifyUserEmail(User user, EditUserEmailPresenter callBack) {
        Gson gson = new Gson();
        String jsonUser = gson.toJson(user);
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, jsonUser);
        String url = RequestIOE.MODIFY_USER_EMAIL.getRequestIO();
        String head = "";
        if (null != user) {
            head = user.getSessionId();
        }

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .put(requestBody)
                .addHeader("Authorization", "FRSC" + head)
                .url(url)
                .build();
        Runnable runnable = () -> {

            RequestResult requestResult;
            try {
                Response response = client.newCall(request).execute();
                String string = response.body().string();
                // 获取返回结果信息
                requestResult = gson.fromJson(string, RequestResult.class);
                callBack.modifyUserEmailResult(requestResult);
            } catch (IOException e) {
                e.printStackTrace();
                callBack.modifyUserEmailResult(new RequestResult(false, null, "请求失败", null));
            }
        };
        ThreadPoolUtil.executeThread(runnable);
    }

}
