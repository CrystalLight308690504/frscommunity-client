package com.crystallightghot.frscommunityclient.view.pojo.system;

import java.util.Objects;

/**
 * @Date 2022/1/10
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */

public class Button {

    private long btnId;

    private String btnName;

    private String description;
    private String code;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Button button = (Button) o;
        return btnId == button.btnId && Objects.equals(btnName, button.btnName) && Objects.equals(description, button.description) && Objects.equals(code, button.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(btnId, btnName, description, code);
    }
}
