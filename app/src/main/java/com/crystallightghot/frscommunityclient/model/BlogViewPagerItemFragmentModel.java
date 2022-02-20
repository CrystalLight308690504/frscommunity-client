package com.crystallightghot.frscommunityclient.model;

import com.crystallightghot.frscommunityclient.view.pojo.skatingtype.SkatingType;
import com.crystallightghot.frscommunityclient.view.util.FRSCOKHttp3RequestUtil;
import com.crystallightghot.frscommunityclient.view.value.FRSCRequstIO;

/**
 * @Date 2022/2/11
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class BlogViewPagerItemFragmentModel {
    public void loadingBlogs(SkatingType skatingType, Object respondMessageKey, int index) {
        String url = FRSCRequstIO.FIND_BLOGS_BY_SKATING_TYPE_ID.getRequestIO() + skatingType.getSkatingTypeId() + "/" +index;
        FRSCOKHttp3RequestUtil.callGetRequest(url,respondMessageKey);
    }
}
