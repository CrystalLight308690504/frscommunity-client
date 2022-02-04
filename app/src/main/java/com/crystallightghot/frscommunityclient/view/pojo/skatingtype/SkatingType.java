package com.crystallightghot.frscommunityclient.view.pojo.skatingtype;

import lombok.Data;

import java.util.Objects;

/**
 * @Date 2022/2/3
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@Data
public class SkatingType {

    private long skatingTypeId;

    private String name;
    private String description;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkatingType that = (SkatingType) o;
        return skatingTypeId == that.skatingTypeId && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skatingTypeId, name, description);
    }
}
