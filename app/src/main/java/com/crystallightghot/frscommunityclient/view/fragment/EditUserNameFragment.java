package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import com.crystallightghot.frscommunityclient.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditUserNameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditUserNameFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

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
    }
}