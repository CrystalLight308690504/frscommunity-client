package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.crystallightghot.frscommunityclient.R;
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
public class EditUserProfileFragment extends Fragment {
    EditUserProfileFragment fragment = this;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.icLog)
    RadiusImageView icLog;
    @BindView(R.id.btnPosition)
    Button btnPosition;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditUserProfileFragment() {
        
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
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_user_profile, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.icLog, R.id.btnPosition})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.icLog:

                chosePictureAction();
                break;

            case R.id.btnPosition:
                break;
            default:
        }
    }

    private void chosePictureAction() {
        List<LocalMedia> mSelectList = new ArrayList<>();
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .theme(R.style.XUIPictureStyle)
                .maxSelectNum(8)
                .minSelectNum(1)
                .selectionMode(PictureConfig.MULTIPLE)
                .previewImage(true)
                .isCamera(true)
                .enableCrop(false)
                .compress(true)
                .previewEggs(true)
                .selectionMedia(mSelectList)
                .forResult(PictureConfig.CHOOSE_REQUEST);;

    }
}