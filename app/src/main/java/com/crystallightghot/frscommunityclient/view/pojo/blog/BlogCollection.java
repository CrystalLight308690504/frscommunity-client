package com.crystallightghot.frscommunityclient.view.pojo.blog;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Date 2022/2/23
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@Data
public class BlogCollection {

    private long collectionId;

    private Long userId;

    private Long blogId;

    private Timestamp createdTime;
}
