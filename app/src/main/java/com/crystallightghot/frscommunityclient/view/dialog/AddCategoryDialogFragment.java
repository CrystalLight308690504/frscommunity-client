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
import com.crystallightghot.frscommunityclient.view.message.TransportDataMessage;
import com.crystallightghot.frscommunityclient.view.util.FRSCEventBusUtil;
import com.crystallightghot.frscommunityclient.view.util.XToastUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.nanchen.compresshelper.StringUtil;

import java.util.Map;
import java.util.TreeMap;

/**
 * @Date 2022/1/23
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class AddCategoryDialogFragment extends DialogFragment {


    @BindView(R.id.btnCancel)
    Button btnCancel;
    @BindView(R.id.ieCategoryName)
    TextInputEditText ieCategoryName;
    @BindView(R.id.ieDescription)
    TextInputEditText ieDescription;
    @BindView(R.id.btnPosition)
    Button btnPosition;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = requireActivity().getLayoutInflater().inflate(R.layout.dialog_add_classification, null);
        ButterKnife.bind(this, view);
        builder.setView(view);
        init();
        return builder.create();
    }

    private void init() {
    }

    @OnClick({R.id.btnCancel, R.id.btnPosition})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCancel:
                dismiss();
                break;
             case R.id.btnPosition:

                 String categoryName = ieCategoryName.getText().toString();
                 String description = ieDescription.getText().toString();
                 if (StringUtil.isEmpty(categoryName)) {
                     XToastUtils.info("请填写分类名字");
                     return;
                 }
                 Map<String, String> data = new TreeMap();
                 data.put("categoryName", categoryName);
                 data.put("description", description);
                 TransportDataMessage transportDataMessage = new TransportDataMessage(data,this);
                 FRSCEventBusUtil.sendMessage(transportDataMessage);

                dismiss();
                break;
            default:
                break;
        }
    }
}
