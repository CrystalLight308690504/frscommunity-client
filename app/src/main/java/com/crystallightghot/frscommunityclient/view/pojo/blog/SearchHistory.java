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
public class SearchHistory {
    @Id(autoincrement = true)
    @Generated
    Long id ;
    Long userId;
    String searchText;
    int historyType; // 0 为blog 1// 为问答
    Date createdTime;
    @Generated(hash = 1998079834)
    public SearchHistory(Long id, Long userId, String searchText, int historyType,
            Date createdTime) {
        this.id = id;
        this.userId = userId;
        this.searchText = searchText;
        this.historyType = historyType;
        this.createdTime = createdTime;
    }
    @Generated(hash = 1905904755)
    public SearchHistory() {
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
    public int getHistoryType() {
        return this.historyType;
    }
    public void setHistoryType(int historyType) {
        this.historyType = historyType;
    }
}
