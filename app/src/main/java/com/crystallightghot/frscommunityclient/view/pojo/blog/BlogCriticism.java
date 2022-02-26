package com.crystallightghot.frscommunityclient.view.pojo.blog;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Date 2022/2/22
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@Data
public class BlogCriticism {


    private long criticismId;

    private Long blogId;

    private Long userId;

    private String content;

    private Long nextContentId;

    private Timestamp createdTime;


}
