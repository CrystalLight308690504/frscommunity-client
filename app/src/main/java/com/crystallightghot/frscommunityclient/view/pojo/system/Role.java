package com.crystallightghot.frscommunityclient.view.pojo.system;

import lombok.Data;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @Date 2022/1/10
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */

@Data
@Entity
public class Role {

    private long roleId;
    @Id
    private long userId;
    private String roleName;
    private String code;
    private String description;

    private Date createdTime;

    @Generated(hash = 139522072)
    public Role(long roleId, long userId, String roleName, String code, String description, Date createdTime) {
        this.roleId = roleId;
        this.userId = userId;
        this.roleName = roleName;
        this.code = code;
        this.description = description;
        this.createdTime = createdTime;
    }

    @Generated(hash = 844947497)
    public Role() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return roleId == role.roleId && Objects.equals(roleName, role.roleName) && Objects.equals(description, role.description) && Objects.equals(createdTime, role.createdTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleName, description, createdTime);
    }

    public long getRoleId() {
        return this.roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedTime() {
        return this.createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
