package com.crystallightghot.frscommunityclient.view.pojo.system;

import java.util.Objects;

/**
 * @Date 2022/1/10
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */

public class Menu {

    private long menuId;

    private Long parentId;

    private String menuName;

    private String description;

    private String code;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return menuId == menu.menuId && Objects.equals(parentId, menu.parentId) && Objects.equals(menuName, menu.menuName) && Objects.equals(description, menu.description) && Objects.equals(code, menu.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuId, parentId, menuName, description, code);
    }
}
