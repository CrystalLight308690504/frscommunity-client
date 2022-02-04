package com.crystallightghot.frscommunityclient.view.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Date 2022/1/19
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class RegisterMessage extends RequestMessage {
    String phoneNumber;
    String password;
}
