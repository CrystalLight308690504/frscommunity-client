package com.crystallightghot.frscommunityclient.view.pojo.system;

import lombok.Getter;
import lombok.Setter;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;

/**
 * @Date 2022/1/10
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@Getter
@Setter
@Entity
public class User {
    @Id
    private Long userId;

    private String oldPassword;
    private String sessionId;

    private String userName = "";

    private String email = "";

    private String password="";

    private String phoneNumber = "";

    private String profile;

    private String introduce;

    private Long credit;

    private Integer gender;

    private Date createdTime;

    private Date lastLoginTime;

    private Date loginTime;

    private String profession;

    private String description;

    private String addressIp;

    @Generated(hash = 904275209)
    public User(Long userId, String oldPassword, String sessionId, String userName,
            String email, String password, String phoneNumber, String profile,
            String introduce, Long credit, Integer gender, Date createdTime,
            Date lastLoginTime, Date loginTime, String profession,
            String description, String addressIp) {
        this.userId = userId;
        this.oldPassword = oldPassword;
        this.sessionId = sessionId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.profile = profile;
        this.introduce = introduce;
        this.credit = credit;
        this.gender = gender;
        this.createdTime = createdTime;
        this.lastLoginTime = lastLoginTime;
        this.loginTime = loginTime;
        this.profession = profession;
        this.description = description;
        this.addressIp = addressIp;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public static User getInstance() {
        return new User();
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOldPassword() {
        return this.oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfile() {
        return this.profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getIntroduce() {
        return this.introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Long getCredit() {
        return this.credit;
    }

    public void setCredit(Long credit) {
        this.credit = credit;
    }

    public Integer getGender() {
        return this.gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getCreatedTime() {
        return this.createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getLastLoginTime() {
        return this.lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getLoginTime() {
        return this.loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getProfession() {
        return this.profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddressIp() {
        return this.addressIp;
    }

    public void setAddressIp(String addressIp) {
        this.addressIp = addressIp;
    }



}
