package com.crystallightghot.frscommunityclient.view.pojo.system;

import java.util.Objects;

/**
 * @Date 2022/1/10
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class Api {

    private long apiId;

    private String methodName;

    private String url;
    private String code;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Api api = (Api) o;
        return apiId == api.apiId && Objects.equals(methodName, api.methodName) && Objects.equals(url, api.url) && Objects.equals(code, api.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apiId, methodName, url, code);
    }
}
