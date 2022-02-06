package com.crystallightghot.frscommunityclient.presenter;

import android.os.Build;
import com.crystallightghot.frscommunityclient.contract.EditUserProfileContract;
import com.crystallightghot.frscommunityclient.model.EditUserProfileModel;
import com.crystallightghot.frscommunityclient.view.value.MessageCode;
import com.crystallightghot.frscommunityclient.view.fragment.EditUserProfileFragment;
import com.crystallightghot.frscommunityclient.view.message.RequestMessage;
import com.crystallightghot.frscommunityclient.view.message.UserChangedMessage;
import com.crystallightghot.frscommunityclient.view.pojo.system.RequestResult;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.crystallightghot.frscommunityclient.view.pojo.system.UserDao;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.FRSCDataBaseUtil;
import com.crystallightghot.frscommunityclient.view.util.FRSCEventBusUtil;
import com.crystallightghot.frscommunityclient.view.util.XToastUtils;
import com.nanchen.compresshelper.CompressHelper;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.*;
import java.util.Base64;

/**
 * @Date 2022/1/29
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class EditUserProfilePresenter implements EditUserProfileContract.Presenter, EditUserProfileContract.EditUserProfileCallBack {
    EditUserProfileFragment view;
    EditUserProfileModel model;

    public EditUserProfilePresenter(EditUserProfileFragment view) {
        this.view = view;
        model = new EditUserProfileModel();
        FRSCEventBusUtil.register(this);
    }

    public void modifyUserProfile(String profilePath) {

        // 压缩图片
        File newFile = CompressHelper.getDefault(view.getContext()).compressToFile(new File(profilePath));

        try {
            FileInputStream inpt = new FileInputStream(newFile);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] b = new byte[1200];
            int length;
            while ((length = inpt.read(b)) != -1) {
                out.write(b, 0, length);
            }
            inpt.close();
            out.close();

            User user = FRSCApplicationContext.getUser();
            byte[] bytes = out.toByteArray();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                String s = Base64.getEncoder().encodeToString(bytes);
                user.setProfile(s);
                // 修改数据库user
                model.modifyUserProfile(user, this);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void modifyUserProfileResult(RequestResult requestResult) {

        RequestMessage message = new RequestMessage(requestResult, MessageCode.MODIFY_USER_PROFILE_RESULT);
        FRSCEventBusUtil.sendMessage(message);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(RequestMessage message) {
        if (message.getMessageKey() == MessageCode.MODIFY_USER_PROFILE_RESULT) {
            view.hideLoadingDialog();
            if (message.isSuccess()) {
                XToastUtils.success("修改成功");
                // 修改保存在本地数据库的user
                User user = FRSCApplicationContext.getUser();
                UserDao userDao = FRSCDataBaseUtil.getWriteDaoSession().getUserDao();
                userDao.save(user);

                UserChangedMessage userChangedMessage = new UserChangedMessage();
                FRSCEventBusUtil.sendMessage(userChangedMessage);
            } else {
                XToastUtils.error(message.getMessage());
            }
        }
    }
}
