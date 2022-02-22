package com.crystallightghot.frscommunityclient.model;

import com.crystallightghot.frscommunityclient.contract.EditUserNameContract;
import com.crystallightghot.frscommunityclient.view.value.FRSCString;
import com.crystallightghot.frscommunityclient.view.util.FRSCThreadPoolUtil;
import com.crystallightghot.frscommunityclient.view.value.FRSCRequestIO;
import com.crystallightghot.frscommunityclient.view.pojo.system.RequestResult;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        String jsonUser = gson.toJson(user);
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, jsonUser);
        String url = FRSCRequestIO.SystemIO.MODIFY_USERNAME.getRequestIO();

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
                userRespondCallBack.modifyUsernameResult(new RequestResult(false, null, FRSCString.SERVICE_UN_ONLINE.getString(),null));
            }
        };
        FRSCThreadPoolUtil.executeThread(runnable);
    }
}
