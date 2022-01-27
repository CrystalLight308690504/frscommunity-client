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
import com.crystallightghot.frscommunityclient.utils.FRSCApplicationContext;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditUserNameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditUserNameFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.ieEmail)
    TextInputEditText ieUserName;
    @BindView(R.id.btnModify)
    Button btnModify;

    private String mParam1;

    public EditUserNameFragment() {
        // Required empty public constructor
    }

    public static EditUserNameFragment newInstance(String param1) {
        EditUserNameFragment fragment = new EditUserNameFragment();
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
        View view = inflater.inflate(R.layout.fragment_edite_user_name, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        ieUserName.setText(FRSCApplicationContext.getUser().getUserName());
    }

    @OnClick(R.id.btnModify)
    public void onClick() {

    }
}