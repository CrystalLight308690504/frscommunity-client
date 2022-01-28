package com.crystallightghot.frscommunityclient.model;

import com.crystallightghot.frscommunityclient.contract.EditUserPasswordContract;
import com.crystallightghot.frscommunityclient.utils.ThreadPoolUtil;
import com.crystallightghot.frscommunityclient.utils.requestio.RequestIOE;
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
public class EditUserPasswordModel implements EditUserPasswordContract.Model {

    public void modifyUserPasswordByPhoneNumber(User user, EditUserPasswordContract.EditUserPasswordCallBack  callBack) {
        Gson gson = new Gson();
        String jsonUser = gson.toJson(user);
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, jsonUser);
        String url = RequestIOE.MODIFY_PASSWORD_BY_PHONE_NUMBER.getRequestIO();
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
                callBack.modifyUserPasswordByPhoneResult(requestResult);
            } catch (IOException e) {
                e.printStackTrace();
                callBack.modifyUserPasswordByPhoneResult(new RequestResult(false, null,"请求失败",null));
            }
        };
        ThreadPoolUtil.executeThread(runnable);
    }
}
