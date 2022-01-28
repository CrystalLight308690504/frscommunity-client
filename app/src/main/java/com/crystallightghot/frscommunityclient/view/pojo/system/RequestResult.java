package com.crystallightghot.frscommunityclient.view.pojo.system;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 数据响应对象
 *    {
 *      success ：是否成功
 *      code    ：返回码
 *      message ：返回信息
 *      //返回数据
 *      data：  ：{
 *
 *      }
 *    }
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestResult {

    private boolean success;//是否成功
    private Integer code;// 返回码
    private String message;//返回信息
    private Object data;// 返回数据
}
