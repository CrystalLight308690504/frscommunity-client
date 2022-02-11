package com.crystallightghot.frscommunityclient.view.pojo.blog;

import com.crystallightghot.frscommunityclient.view.pojo.skatingtype.SkatingType;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @Date 2022/2/3
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */

@Data
public class Blog {

    private long blogId;

    private SkatingType skatingType;

    private BlogCategory blogCategory;

    private String blogTitle;

    private String content;

    private Blog nextContent;

    private Long rightId = 1L;

    private Timestamp createdTime;

    User user;
}
