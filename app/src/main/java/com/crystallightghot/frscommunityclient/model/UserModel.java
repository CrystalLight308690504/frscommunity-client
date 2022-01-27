package com.crystallightghot.frscommunityclient.model;

import com.crystallightghot.frscommunityclient.contract.UserContract;
import com.crystallightghot.frscommunityclient.utils.ThreadPoolUtil;
import com.crystallightghot.frscommunityclient.utils.requestio.RequestIOE;
import com.crystallightghot.frscommunityclient.view.pojo.system.Result;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;

/**
 * @Date 2022/1/27
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class UserModel implements UserContract.Model {

    @Override
    public void modifyUsername(User user, UserContract.UserRespondCallBack userRespondCallBack) {
        Gson gson = new Gson();
        String jsonUser = gson.toJson(user);
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, jsonUser);
        String url = RequestIOE.MODIFY_USERNAME.getRequestIO();

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

                // 获取返回结果信息
                Result result = gson.fromJson(string, Result.class);
                boolean success = result.isSuccess();
                if (success) {//如果成功
                    userRespondCallBack.modifyUsernameSuccess(result.getMessage(), result.getData());
                } else {// 请求失败
                    userRespondCallBack.modifyUsernameFailed(result.getMessage());
                }
            } catch (IOException e) {
                e.printStackTrace();
                userRespondCallBack.modifyUsernameFailed("失败");
            }
        };
        ThreadPoolUtil.executeThread(runnable);
    }
}
