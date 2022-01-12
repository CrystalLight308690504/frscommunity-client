package com.crystallightghot.frscommunityclient.widget;

import android.content.Context;
import androidx.appcompat.widget.AppCompatButton;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Date 2022/1/9
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class RoundButton extends AppCompatButton {

    public RoundButton(Context context) {

        super(context);
    }


}
