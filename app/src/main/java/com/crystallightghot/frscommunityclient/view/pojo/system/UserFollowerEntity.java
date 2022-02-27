package com.crystallightghot.frscommunityclient.view.pojo.system;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Date 2022/2/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */

@Data
public class UserFollowerEntity {

    private long followerId;

    private User user;
    private User userFollowed;

    private Timestamp createdTime;
}
