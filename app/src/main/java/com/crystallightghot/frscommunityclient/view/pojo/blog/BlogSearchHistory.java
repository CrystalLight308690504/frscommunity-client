package com.crystallightghot.frscommunityclient.view.pojo.blog;

import lombok.Data;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;

/**
 * @Date 2022/2/20
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@Entity
@Data
public class BlogSearchHistory {
    @Id(autoincrement = true)
    @Generated
    Long id ;
    Long userId;
    String searchText;
    Date createdTime;
    @Generated(hash = 481773427)
    public BlogSearchHistory(Long id, Long userId, String searchText,
            Date createdTime) {
        this.id = id;
        this.userId = userId;
        this.searchText = searchText;
        this.createdTime = createdTime;
    }
    @Generated(hash = 1576745356)
    public BlogSearchHistory() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return this.userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getSearchText() {
        return this.searchText;
    }
    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
    public Date getCreatedTime() {
        return this.createdTime;
    }
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
