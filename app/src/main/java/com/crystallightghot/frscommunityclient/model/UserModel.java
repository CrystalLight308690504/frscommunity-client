package com.crystallightghot.frscommunityclient.model;

import com.crystallightghot.frscommunityclient.presenter.ManageUserFragmentPresenter;
import com.crystallightghot.frscommunityclient.presenter.ManageUserRecycleViewAdapterViewHolderPresenter;
import com.crystallightghot.frscommunityclient.view.pojo.system.UserFollower;
import com.crystallightghot.frscommunityclient.view.util.FRSCOKHttp3RequestUtil;
import com.crystallightghot.frscommunityclient.view.util.FRSCGsonUtil;
import com.crystallightghot.frscommunityclient.view.value.FRSCRequestIO;

/**
 * @Date 2022/2/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class UserModel {
    public <K> void findUserByNameKey(String searchText, int pagerIndex, K respondMessageKey) {
        String url = FRSCRequestIO.SystemIO.FIND_USERS_BY_NAME_KEY.getRequestIO() + searchText + "/" + pagerIndex;
        FRSCOKHttp3RequestUtil.callGetRequest(url, respondMessageKey);
    }

    public void followUser(UserFollower userFollower, Object respondMessageKey) {
        String url = FRSCRequestIO.SystemIO.FOLLOW_USER.getRequestIO();
        String requestBody = FRSCGsonUtil.objectToJsonString(userFollower);
        FRSCOKHttp3RequestUtil.callPostRequest(url, requestBody, respondMessageKey);
    }

    public void cancelFollower(Long userId, Long userFollowedId, Object respondMessageKey) {
        String url = FRSCRequestIO.SystemIO.CANCEL_FOLLOW_USER.getRequestIO() + userId + "/" + userFollowedId;
        FRSCOKHttp3RequestUtil.callDeleteRequest(url, "", respondMessageKey);
    }

    public void loadFollowerCount(Long userId, Object respondMessageKey) {
        String url = FRSCRequestIO.SystemIO.COUNT_FOLLOWER.getRequestIO() + userId;
        FRSCOKHttp3RequestUtil.callGetRequest(url, respondMessageKey);
    }

    public void checkIfFollowed(Long userId, Long userFollowedId, Object respondMessageKey) {
        String url = FRSCRequestIO.SystemIO.EXIST_FOLLOWER.getRequestIO() + userId + "/" + userFollowedId;
        FRSCOKHttp3RequestUtil.callGetRequest(url, respondMessageKey);
    }

    public void loadFollowUserCount(Long userId, Object respondMessageKey) {
        String url = FRSCRequestIO.SystemIO.COUNT_USER_FOLLOW_COUNT.getRequestIO() + userId;
        FRSCOKHttp3RequestUtil.callGetRequest(url,respondMessageKey);
    }

    public void loadFanOfUserCount(Long userId, Object respondMessageKey) {
        String url = FRSCRequestIO.SystemIO.COUNT_FOLLOWER.getRequestIO()+ userId;
        FRSCOKHttp3RequestUtil.callGetRequest(url,respondMessageKey);
    }

    public void isLogin(String sessionId, Object respondMessageKey) {
        String url = FRSCRequestIO.SystemIO.IS_LOGIN.getRequestIO() +sessionId;
        FRSCOKHttp3RequestUtil.callGetRequest(url, respondMessageKey);
    }

    public void findUserById(Long userId, Object respondMessageKey) {
        String url = FRSCRequestIO.SystemIO.FIND_USERS_BY_ID.getRequestIO() + userId;
        FRSCOKHttp3RequestUtil.callGetRequest(url,respondMessageKey);

    }

    public void loadUsersFollowed(Long userId, int pagerIndex, Object respondMessageKey) {
        String url = FRSCRequestIO.SystemIO.FIND_USERS_FOLLOWED.getRequestIO() + userId + "/" + pagerIndex;
        FRSCOKHttp3RequestUtil.callGetRequest(url,respondMessageKey);


    }  public void loadUsersFan(Long userId, int pagerIndex, Object respondMessageKey) {
        String url = FRSCRequestIO.SystemIO.FIND_USERS_FAN.getRequestIO() + userId + "/" + pagerIndex;
        FRSCOKHttp3RequestUtil.callGetRequest(url,respondMessageKey);
    }


    public void loadUsers(int pagerIndex, Object respondMessageKey) {
        String url = FRSCRequestIO.SystemIO.FIND_USERS.getRequestIO() + pagerIndex;
        FRSCOKHttp3RequestUtil.callGetRequest(url,respondMessageKey);
    }

    public void changeRole(long userId, long roleId, Object respondMessageKey) {
        String url = FRSCRequestIO.SystemIO.CHANG_USER_ROLE.getRequestIO() + userId + "/" + roleId;
        FRSCOKHttp3RequestUtil.callPostRequest(url,"",respondMessageKey);
    }
}
