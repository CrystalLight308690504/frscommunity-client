package com.crystallightghot.frscommunityclient.model;

import com.crystallightghot.frscommunityclient.contract.EditUserProfileContract;
import com.crystallightghot.frscommunityclient.view.enums.FRSCRequestIOE;
import com.crystallightghot.frscommunityclient.view.pojo.system.RequestResult;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.crystallightghot.frscommunityclient.view.util.FRSCThreadPoolUtil;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;

/**
 * @Date 2022/1/29
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class EditUserProfileModel implements EditUserProfileContract.Model {
    public void modifyUserProfile(User user, EditUserProfileContract.EditUserProfileCallBack callBack) {
        Gson gson = new Gson();
        String jsonUser = gson.toJson(user);
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, jsonUser);
        String url = FRSCRequestIOE.MODIFY_USER_PROFILE.getRequestIO();

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

            RequestResult requestResult;
            try {
                Response response = client.newCall(request).execute();
                String string = response.body().string();
                // 获取返回结果信息
                requestResult = gson.fromJson(string, RequestResult.class);
                callBack.modifyUserProfileResult(requestResult);
            } catch (IOException e) {
                e.printStackTrace();
                callBack.modifyUserProfileResult(new RequestResult(false, null,"服务器跑路了",null));
            }
        };
        FRSCThreadPoolUtil.executeThread(runnable);
    }
}
