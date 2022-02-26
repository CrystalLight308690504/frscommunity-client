package com.crystallightghot.frscommunityclient.view.pojo.blog;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Objects;

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

    private Long blogId;

    private Timestamp createdTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogApplause that = (BlogApplause) o;
        return clickApplauseId == that.clickApplauseId && Objects.equals(userId, that.userId) && Objects.equals(blogId, that.blogId) && Objects.equals(createdTime, that.createdTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, blogId, createdTime, clickApplauseId);
    }
}
