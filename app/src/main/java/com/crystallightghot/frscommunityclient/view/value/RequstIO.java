package com.crystallightghot.frscommunityclient.view.value;

/**
 * @Date 2022/1/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public enum RequstIO {

    // 系统请求接口
    USER_Login("/user/login","9002"),
    USER_REGISTER("/user/register","9002"),
    USER_UNLOGIN("/user/logout","9002"),
    MODIFY_USERNAME("/user/modifyUserName","9002"),
    MODIFY_PASSWORD_BY_PHONE_NUMBER("/user/modifyUserPasswordByPhoneNumber","9002"),
    MODIFY_PASSWORD_BY_OLD_PASSWORD("/user/modifyUserPasswordByOldPassword","9002"),
    MODIFY_USER_GENDER("/user/modifyUserGender","9002"),
    MODIFY_USER_DESCRIPTION("/user/modifyUserDescription","9002"),
    MODIFY_USER_PROFILE("/user/modifyUserProfile","9002"),
    MODIFY_USER_EMAIL("/user/modifyUserEmail","9002"),
    GET_SKATING_TYPE("/user/getSkatingType","9002"),

    // 博客请求接口
    ADD_BLOG("/blog/save","9003"),
    DELETE_BLOG("/blog/delete","9003"),
    MODIFY_BLOG("/blog/modifyBlog","9003"),
    FIND_BLOGS_BY_USERID("/blog/findBlogsByUserId/","9003"),
    FIND_BLOGS_BY_SKATING_TYPE_ID("/blog/findBlogsBySkatingTypeId/","9003"),
    FIND_CATEGORIES_BY_USERID("/blog/findBlogCategories/","9003"),
    FIND_BLOGS_BY_USERID_AND_CATEGORY("/blog/findBlogsByUserAndCategory/","9003"),
    ADD_BLOG_CATEGORY("/blog/addBlogCategory/","9003"),
    DELETE_BLOG_CATEGORY("/blog/deleteBlogCategory", "9003"),
    MODIFY_BLOG_CATEGORY("/blog/modifyBlogCategory","9003" );

    private String host = "http://192.168.0.103:";
//    private String host = "http://42.194.211.199:";
    private String port;
    private String requestInterface;

    private RequstIO(String requestInterface, String port) {
        this.requestInterface = requestInterface;
        this.port = port;
    }

    public String getRequestIO() {
        return host + port  + requestInterface;
    }


}
