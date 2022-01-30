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
import com.crystallightghot.frscommunityclient.view.activity.BaseFragmentActivity;
import com.crystallightghot.frscommunityclient.view.activity.SingleFragmentActivity;
import com.crystallightghot.frscommunityclient.view.util.FRSCFragmentUtil;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HelpSearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HelpSearchFragment extends BaseFragment {
    static HelpSearchFragment fragment;

    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.top_bar_back)
    ImageButton topBarBack;
    @BindView(R.id.input_box)
    TextInputEditText inputBox;
    @BindView(R.id.btn_search)
    TextView btnSearch;


    @BindView(R.id.rv_search_history)
    RecyclerView lvSearchHistory;

    SingleFragmentActivity activity;


    private String mParam1;
    List<Fragment> fragments;

    public HelpSearchFragment() {
        // Required empty public constructor
    }


    public static HelpSearchFragment newInstance(String string) {

        return new HelpSearchFragment();
    }

    private void init() {
        activity = (SingleFragmentActivity) getActivity();

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
                FRSCFragmentUtil.intentToFragmentAddedToBackStack(HelpSearchResultsFragment.newInstance("SearchResultsFragment"), (BaseFragmentActivity) getActivity());
                break;
            default:
                break;
        }
    }

}