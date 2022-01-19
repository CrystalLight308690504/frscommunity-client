package com.crystallightghot.frscommunityclient.view.pojo.system;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @Date 2022/1/10
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@Getter
@Setter
@NoArgsConstructor

public class User  implements Serializable {

    private long userId;

    private String sessionId;

    private String userName = "";

    private String email = "";

    private String password="";

    private String phoneNumber = "";

    private String profile;

    private String introduce;

    private Long credit;

    private Boolean gender;

    private Timestamp createdTime;

    private Timestamp lastLoginTime;

    private Timestamp loginTime;

    private String profession;

    private byte[] description;

    private String addressIp;

    public static User getInstance() {
        return new User();
    }
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(phoneNumber, user.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, email, password, phoneNumber);
    }
}
