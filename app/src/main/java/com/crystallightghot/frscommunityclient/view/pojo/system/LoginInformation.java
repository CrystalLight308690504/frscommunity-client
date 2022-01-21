package com.crystallightghot.frscommunityclient.view.pojo.system;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @Date 2022/1/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@Entity
public class LoginInformation {
    @Id(autoincrement = true)
    private Long id;
    private Long userId;
    private int login ;
    @Generated(hash = 551509364)
    public LoginInformation(Long id, Long userId, int login) {
        this.id = id;
        this.userId = userId;
        this.login = login;
    }
    @Generated(hash = 917130239)
    public LoginInformation() {
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
    public int getLogin() {
        return this.login;
    }
    public void setLogin(int login) {
        this.login = login;
    }


}
