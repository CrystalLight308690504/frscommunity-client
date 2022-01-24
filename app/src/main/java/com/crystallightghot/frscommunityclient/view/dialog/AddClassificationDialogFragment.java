package com.crystallightghot.frscommunityclient.view.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.crystallightghot.frscommunityclient.R;
import com.xuexiang.xui.widget.button.switchbutton.SwitchButton;

/**
 * @Date 2022/1/23
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class AddClassificationDialogFragment extends DialogFragment {
    @BindView(R.id.btnOpened)
    SwitchButton btnOpened;
    @BindView(R.id.btnCancel)
    Button btnCancel;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = requireActivity().getLayoutInflater().inflate(R.layout.dialog_add_classification, null);
        ButterKnife.bind(this, view);
        builder.setView(view);
        return builder.create();
    }

    @OnClick({R.id.btnOpened, R.id.btnCancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnOpened:
                break;
            case R.id.btnCancel:
                this.dismiss();
                break;
            default:
                break;
        }
    }
}
