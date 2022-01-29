package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.crystallightghot.frscommunityclient.contract.BaseContract;
import com.crystallightghot.frscommunityclient.view.util.XToastUtils;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.dialog.LoadingDialog;

/**
 * @Date 2022/1/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public abstract class BaseFragment extends Fragment implements BaseContract.View {

    LoadingDialog loadingDialog;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingDialog = WidgetUtils.getLoadingDialog(getActivity())
                .setIconScale(0.4F)
                .setLoadingSpeed(8);
    }

    @Override
    public void showLoadingDialog() {
        loadingDialog.show();
    }

    @Override
    public void hideLoadingDialog() {
        loadingDialog.dismiss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        loadingDialog.dismiss();
        loadingDialog = null;
    }

    public void  showInformationToast(String informationMessage) {
        XToastUtils.info(informationMessage);
    }

    public void showErrorToast(String informationMessage) {
        XToastUtils.error(informationMessage);
    }

    public void showSuccessToast(String informationMessage) {
        XToastUtils.success(informationMessage);
    }

    public void showWarningToast(String informationMessage) {
        XToastUtils.warning(informationMessage);
    }
}
