package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.util.FRSCFragmentUtil;
import com.google.android.material.textfield.TextInputEditText;

/**
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class PutBlogContentFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.btnPosition)
    AppCompatButton btnPosition;
    @BindView(R.id.titleAcquired)
    TextInputEditText titleAcquired;
    @BindView(R.id.s1)
    LinearLayout s1;

    // TODO: Rename and change types of parameters
    private String mParam1;

    public PutBlogContentFragment() {
        // Required empty public constructor
    }

    public static PutBlogContentFragment newInstance(String param1) {
        PutBlogContentFragment fragment = new PutBlogContentFragment();
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
        View view = inflater.inflate(R.layout.fragment_put_blog, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.btn_back, R.id.btnPosition, R.id.titleAcquired})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                getActivity().onBackPressed();
                break;
            case R.id.btnPosition:
                FRSCFragmentUtil.intentToFragmentNoAddedToBackStack(PutBlogConfirmFragment.newInstance(""));
                break;
            case R.id.titleAcquired:
                break;
        }
    }
}