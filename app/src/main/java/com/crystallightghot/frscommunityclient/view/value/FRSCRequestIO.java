package com.crystallightghot.frscommunityclient.view.value;

/**
 * @Date 2022/1/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class FRSCRequestIO {
    private static String localHost = "http://192.168.0.100:";
//    private static  String localHost = "http://42.194.211.199:";

    public enum SystemIO {
        // 系统请求接口
        USER_LOGIN("/user/login"),
        USER_REGISTER("/user/register"),
        USER_LOGGED_OUT("/user/logout"),
        EXIST_FOLLOWER("/user/existFollower/"),
        CANCEL_FOLLOW_USER("/user/cancelFollowUser/"),
        COUNT_FOLLOWER("/user/countFollower/"),
        COUNT_USER_FOLLOW_COUNT("/user/countUserFollowCount/"),
        FOLLOW_USER("/user/followUser"),
        FIND_USERS_BY_NAME_KEY("/user/findUserByNameKey/"),
        MODIFY_USERNAME("/user/modifyUserName"),
        MODIFY_PASSWORD_BY_PHONE_NUMBER("/user/modifyUserPasswordByPhoneNumber"),
        MODIFY_PASSWORD_BY_OLD_PASSWORD("/user/modifyUserPasswordByOldPassword"),
        MODIFY_USER_GENDER("/user/modifyUserGender"),
        MODIFY_USER_DESCRIPTION("/user/modifyUserDescription"),
        MODIFY_USER_PROFILE("/user/modifyUserProfile"),
        MODIFY_USER_EMAIL("/user/modifyUserEmail");

        private final String requestInterface;

        SystemIO(String requestInterface) {
            this.requestInterface = requestInterface;
        }

        public String getRequestIO() {
            String host = "http://42.194.211.199:";
            String port = "9002";
            return host + port + requestInterface;
        }
    }

    public enum BlogIO {

        GET_SKATING_TYPE("/blog/getSkatingType"),
        // 博客请求接口
        ADD_BLOG("/blog/save"),
        DELETE_BLOG("/blog/delete"),
        MODIFY_BLOG("/blog/modifyBlog"),
        FIND_BLOGS_BY_USERID("/blog/findBlogsByUserId/"),
        FIND_BLOGS_BY_SKATING_TYPE_ID("/blog/findBlogsBySkatingTypeId/"),
        FIND_CATEGORIES_BY_USERID("/blog/findBlogCategories/"),
        FIND_BLOGS_BY_USERID_AND_CATEGORY("/blog/findBlogsByUserAndCategory/"),
        FIND_BLOGS_BY_SEARCH_KEY("/blog/findBlogsBySearchKey/"),
        ADD_BLOG_CATEGORY("/blog/addBlogCategory/"),
        DELETE_BLOG_CATEGORY("/blog/deleteBlogCategory"),
        COUNT_BLOGS("/blog/countBlogs/"),
        MODIFY_BLOG_CATEGORY("/blog/modifyBlogCategory");

        private final String requestInterface;

        BlogIO(String requestInterface) {
            this.requestInterface = requestInterface;
        }

        public String getRequestIO() {
            String host = "http://42.194.211.199:";
            String port = "9003";
            return host + port + requestInterface;
        }

    }


}
