package com.crystallightghot.frscommunityclient.view.fragment;

import android.app.Activity;
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
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditUserProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditUserProfileFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.icLog)
    RadiusImageView icLog;

    List<LocalMedia> mSelectList = new ArrayList<>();
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
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .theme(R.style.XUIPictureStyle)
                .selectionMode(PictureConfig.SINGLE)
                .previewImage(true)
                .isCamera(true)
                .cropWH(50,50)
                .showCropFrame(true)
                .enableCrop(false)
                .compress(true)
                .previewEggs(true)
                .selectionMedia(mSelectList)
                .maxSelectNum(1)
                .selectionMode(PictureConfig.SINGLE)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择
                    mSelectList = PictureSelector.obtainMultipleResult(data);
                    LocalMedia media = mSelectList.get(0);
                    String path = media.getPath();
                    presenter.modifyUserProfile(path);
                    break;
                default:
                    break;
            }
        }
    }
}