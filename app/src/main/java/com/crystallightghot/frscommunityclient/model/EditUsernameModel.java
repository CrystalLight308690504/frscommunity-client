package com.crystallightghot.frscommunityclient.model;

import com.crystallightghot.frscommunityclient.contract.EditUserNameContract;
import com.crystallightghot.frscommunityclient.view.util.ThreadPoolUtil;
import com.crystallightghot.frscommunityclient.view.enums.RequestIOE;
import com.crystallightghot.frscommunityclient.view.pojo.system.RequestResult;
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
public class EditUsernameModel implements EditUserNameContract.Model {

    @Override
    public void modifyUsername(User user, EditUserNameContract.UserRespondCallBack userRespondCallBack) {
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
                userRespondCallBack.modifyUsernameResult(requestResult);
            } catch (IOException e) {
                e.printStackTrace();
                userRespondCallBack.modifyUsernameResult(new RequestResult(false, null,"请求失败",null));
            }
        };
        ThreadPoolUtil.executeThread(runnable);
    }
}
