package com.crystallightghot.frscommunityclient.view.pojo.system;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * @Date 2022/2/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@Data
public class UserFollower {

    private long followerId;
    private Long userId;
    private Long userFollowedId;
    private Timestamp createdTime;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserFollower that = (UserFollower) o;
        return followerId == that.followerId && Objects.equals(userId, that.userId) && Objects.equals(userFollowedId, that.userFollowedId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(followerId, userId, userFollowedId);
    }
}
