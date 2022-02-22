package com.crystallightghot.frscommunityclient.model;

import com.crystallightghot.frscommunityclient.presenter.UserSearchResultRecyclerViewAdapterViewHolderPresenter;
import com.crystallightghot.frscommunityclient.view.pojo.system.UserFollower;
import com.crystallightghot.frscommunityclient.view.util.FRSCOKHttp3RequestUtil;
import com.crystallightghot.frscommunityclient.view.util.FRSCObjectTransferUtil;
import com.crystallightghot.frscommunityclient.view.value.FRSCRequestIO;

/**
 * @Date 2022/2/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class UserModel {
    public <K> void  findUserByNameKey(String searchText, int pagerIndex, K respondMessageKey) {
        String url = FRSCRequestIO.SystemIO.FIND_USERS_BY_NAME_KEY.getRequestIO() + searchText + "/" + pagerIndex;
        FRSCOKHttp3RequestUtil.callGetRequest(url,respondMessageKey);
    }

    public void followUser(UserFollower userFollower,  Object respondMessageKey) {
        String url = FRSCRequestIO.SystemIO.FOLLOW_USER.getRequestIO();
        String requestBody = FRSCObjectTransferUtil.ObjectToJsonString(userFollower);
        FRSCOKHttp3RequestUtil.callPostRequest(url, requestBody,respondMessageKey);
    }

    public void cancelFollower(Long userId, Long userFollowedId, Object respondMessageKey) {
        String url = FRSCRequestIO.SystemIO.CANCEL_FOLLOW_USER.getRequestIO() + userId + "/" + userFollowedId;
        FRSCOKHttp3RequestUtil.callDeleteRequest(url,"",respondMessageKey);
    }

    public void loadFollowerCount(Long userId, Object respondMessageKey) {
        String url = FRSCRequestIO.SystemIO.COUNT_FOLLOWER.getRequestIO() + userId;
        FRSCOKHttp3RequestUtil.callGetRequest(url,respondMessageKey);
    }

    public void checkIfFollowed(Long userId, Long userFollowedId, Object respondMessageKey) {
        String url = FRSCRequestIO.SystemIO.EXIST_FOLLOWER.getRequestIO() + userId + "/" + userFollowedId;
        FRSCOKHttp3RequestUtil.callGetRequest(url,respondMessageKey);
    }
}
