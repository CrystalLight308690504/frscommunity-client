package com.crystallightghot.frscommunityclient.view.pojo.blog;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Date 2022/2/3
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */

@Data
public class BlogApplause {
    private long clickApplauseId;
    private Long userId;
    private Long userOfBlogId;

    private Blog blog;

    private Timestamp createdTime;

}
