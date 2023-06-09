package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.activity.BaseActivity;
import com.crystallightghot.frscommunityclient.view.activity.BaseFragmentActivity;
import com.crystallightghot.frscommunityclient.view.util.FRSCFragmentUtil;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeSearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeSearchFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.top_bar_back)
    ImageButton topBarBack;
    @BindView(R.id.input_box)
    TextInputEditText inputBox;
    @BindView(R.id.btn_search)
    TextView btnSearch;


    @BindView(R.id.rv_search_history)
    RecyclerView lvSearchHistory;

    BaseActivity activity;


    private String mParam1;
    List<Fragment> fragments;

    public HomeSearchFragment() {
        // Required empty public constructor
    }


    public static HomeSearchFragment newInstance(String string) {

        return new HomeSearchFragment();
    }

    private void init() {
        activity = (BaseActivity) getActivity();

        topBarBack.setOnClickListener( view -> activity.onBackPressed());
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
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @OnClick(R.id.btn_search)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_search:
                FRSCFragmentUtil.intentToFragmentAddedToBackStack(HomeSearchResultsFragment.newInstance("SearchResultsFragment"), (BaseFragmentActivity) getActivity());
                break;
            default:
                break;
        }
    }

}