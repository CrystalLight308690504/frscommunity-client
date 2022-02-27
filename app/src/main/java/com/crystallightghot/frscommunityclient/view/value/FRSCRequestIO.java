package com.crystallightghot.frscommunityclient.view.value;

/**
 * @Date 2022/1/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class FRSCRequestIO {
    private static String localHost = "http://192.168.0.101:";
//    private static  String localHost = "http://42.194.211.199:";

    public enum SystemIO {
        // 系统请求接口
        USER_LOGIN("/user/login"),
        IS_LOGIN("/user/isLogined/"),
        USER_REGISTER("/user/register"),
        USER_LOGGED_OUT("/user/logout"),
        EXIST_FOLLOWER("/user/existFollower/"),
        CANCEL_FOLLOW_USER("/user/cancelFollowUser/"),
        COUNT_FOLLOWER("/user/countFollower/"),
        COUNT_USER_FOLLOW_COUNT("/user/countUserFollowCount/"),
        FOLLOW_USER("/user/followUser"),
        FIND_USERS_FOLLOWED("/user/findUserFollowed/"),
        FIND_USERS_FAN("/user/findUserFan/"),
        FIND_USERS_BY_NAME_KEY("/user/findUserByNameKey/"),
        FIND_USERS_BY_ID("/user/findUserByUserId/"),
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
//            String host = "http://42.194.211.199:";
            String host = localHost;
            String port = "9002";
            return host + port + requestInterface;
        }
    }

    public enum BlogIO {

        GET_SKATING_TYPE("/blog/getSkatingType"),
        // 博客请求接口
        ADD_BLOG("/blog/save"),
        ADD_BLOG_CATEGORY("/blog/addBlogCategory/"),
        APPLAUSE_BLOG("/blog/applauseBlog/"),
        CANCEL_APPLAUSE_BLOG("/blog/cancelApplauseBlog/"),
        COLLECTION_BLOG("/blog/collectionBlog"),
        CANCEL_COLLECTION_BLOG("/blog/cancelCollectionBlog"),
        COUNT_BlOG_APPLAUSE_COUNT("/blog/countBlogApplause/"),
        COUNT_BLOGS_BY_CATEGORY("/blog/countBlogsByCategoryId/"),
        COUNT_BLOGS("/blog/countBlogs/"),
        CRITICISE_BLOG("/blog/criticiseBlog"),
        DELETE_BLOG_CRITICISM("/blog/deleteBlogCriticism"),
        FIND_BLOG_CRITICISMS("/blog/findBlogCriticisms/"),
        DELETE_BLOG("/blog/delete"),
        DELETE_BLOG_CATEGORY("/blog/deleteBlogCategory"),
        MODIFY_BLOG("/blog/modifyBlog"),
        FIND_BLOGS_BY_USERID("/blog/findBlogsByUserId/"),
        FIND_BLOGS_COLLECTED("/blog/findBlogsCollected/"),
        FIND_BLOGS_BY_SKATING_TYPE_ID("/blog/findBlogsBySkatingTypeId/"),
        FIND_CATEGORIES_BY_USERID("/blog/findBlogCategories/"),
        FIND_BLOGS_BY_USERID_AND_CATEGORY("/blog/findBlogsByUserAndCategory/"),
        FIND_BLOGS_BY_SEARCH_KEY("/blog/findBlogsBySearchKey/"),
        IS_COLLECTION_BLOG("/blog/isCollectionBlog/"),
        IS_APPLAUSE_BLOG("/blog/isApplauseBlog/"),
        MODIFY_BLOG_CATEGORY("/blog/modifyBlogCategory");

        private final String requestInterface;

        BlogIO(String requestInterface) {
            this.requestInterface = requestInterface;
        }

        public String getRequestIO() {
//            String host = "http://42.194.211.199:";
            String host = localHost;
            String port = "9003";
            return host + port + requestInterface;
        }

    }


}
