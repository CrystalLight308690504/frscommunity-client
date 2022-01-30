package com.crystallightghot.frscommunityclient.view.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.presenter.EditUserProfilePresenter;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.XToastUtils;
import com.wildma.pictureselector.PictureBean;
import com.wildma.pictureselector.PictureSelector;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditUserProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditUserProfileFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.icLog)
    RadiusImageView icLog;

    EditUserProfilePresenter presenter;
    private String mParam1;

    public EditUserProfileFragment() {
        presenter = new EditUserProfilePresenter(this);

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment EditUserProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditUserProfileFragment newInstance(String param1) {
        EditUserProfileFragment fragment = new EditUserProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_user_profile, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        Drawable userProfile = FRSCApplicationContext.getUserProfile();
        icLog.setImageDrawable(userProfile);
    }

    @OnClick({R.id.icLog})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.icLog:

                chosePictureAction();
                break;

        }
    }

    private void chosePictureAction() {
        PictureSelector
                .create(this, PictureSelector.SELECT_REQUEST_CODE)
                .selectPicture(true, 100, 100, 1, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*结果回调*/
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                PictureBean pictureBean = data.getParcelableExtra(PictureSelector.PICTURE_RESULT);
                if (pictureBean.isCut()) {
                    presenter.modifyUserProfile( pictureBean.getPath());
                } else {
                    XToastUtils.warning("请裁剪你的图片");
                }
            }
        }
    }

}