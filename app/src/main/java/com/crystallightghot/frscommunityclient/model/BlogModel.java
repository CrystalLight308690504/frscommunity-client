package com.crystallightghot.frscommunityclient.model;

import com.crystallightghot.frscommunityclient.view.util.FRSCOKHttp3RequestUtil;
import com.crystallightghot.frscommunityclient.view.value.FRSCRequstIO;

/**
 * @Date 2022/2/7
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class BlogModel {

    public void deleteBlog(String requestBody, Object respondMessageKey) {
        String url = FRSCRequstIO.DELETE_BLOG_CATEGORY.getRequestIO();
        FRSCOKHttp3RequestUtil.callDeleteRequest(url, requestBody, respondMessageKey);
    }

    public void loadSearchBlogResult(String searchText, int pagerIndex , Object respondMessageKey) {
        String url = FRSCRequstIO.FIND_BLOGS_BY_SEARCH_KEY.getRequestIO() + searchText + "/" + pagerIndex;
        FRSCOKHttp3RequestUtil.callGetRequest(url,respondMessageKey);
    }
}
