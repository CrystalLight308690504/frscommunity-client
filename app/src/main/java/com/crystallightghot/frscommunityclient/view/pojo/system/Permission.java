package com.crystallightghot.frscommunityclient.view.pojo.system;

import java.util.Objects;

/**
 * @Date 2022/1/10
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class Permission {

    private long permissionId;

    private String permissionName;

    private String description;

    private String code;

    private Byte type;

    private Long typeId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return permissionId == that.permissionId && Objects.equals(permissionName, that.permissionName) && Objects.equals(description, that.description) && Objects.equals(code, that.code) && Objects.equals(type, that.type) && Objects.equals(typeId, that.typeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permissionId, permissionName, description, code, type, typeId);
    }
}
