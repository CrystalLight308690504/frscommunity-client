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
import com.crystallightghot.frscommunityclient.presenter.EditUserDescriptionPresenter;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.XToastUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditMyDescriptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditMyDescriptionFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.ieDescription)
    TextInputEditText ieDescription;
    @BindView(R.id.btnPosition)
    Button btnPosition;
    @BindView(R.id.log)
    RadiusImageView log;

    private String mParam1;
    User user;
    EditUserDescriptionPresenter presenter;

    public EditMyDescriptionFragment() {
        user = FRSCApplicationContext.getUser();
        presenter = new EditUserDescriptionPresenter(this);
    }

    public static EditMyDescriptionFragment newInstance(String param1) {
        EditMyDescriptionFragment fragment = new EditMyDescriptionFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_my_description, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {

        ieDescription.setText(user.getDescription());
        log.setImageDrawable(FRSCApplicationContext.getUserProfile());

    }

    @OnClick(R.id.btnPosition)
    public void onClick() {
        String description = ieDescription.getText().toString();

        if (description.equals("")) {
            XToastUtils.warning("请填写自己的描述");
        }else {
            presenter.modifyUserDescription(description);
        }


    }

    public void clearDataInput() {
        ieDescription.setText("");
    }
}