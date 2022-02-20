package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.viewpager2.widget.ViewPager2;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.activity.BaseFragmentActivity;
import com.crystallightghot.frscommunityclient.view.adapter.SearchResultViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.android.material.textfield.TextInputEditText;


public class SearchResultsFragment extends BaseFragment {

    static SearchResultsFragment allSearchResultsFragment;
    @BindView(R.id.top_bar_back)
    ImageButton topBarBack;
    @BindView(R.id.input_box)
    TextInputEditText inputBox;
    @BindView(R.id.btn_search)
    TextView btnSearch;
    @BindView(R.id.searchResultType)
    TabLayout searchResultType;

    BaseFragmentActivity activity;
    @BindView(R.id.viewPager)
    ViewPager2 viewPager;

    public SearchResultsFragment(String searchText) {
        this.searchText = searchText;
    }

    String searchText;

    public SearchResultsFragment() {
        // Required empty public constructor
    }

    public static SearchResultsFragment newInstance(String searchText) {

        return new SearchResultsFragment(searchText);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_resultes, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        inputBox.setText(searchText);
        if (inputBox.length() != 0) {
            btnSearch.setEnabled(true);
        }
        activity = (BaseFragmentActivity) getActivity();
        topBarBack.setOnClickListener(view -> activity.onBackPressed());
        inputBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0) {
                    btnSearch.setEnabled(false);
                } else {
                    btnSearch.setEnabled(true);
                }
            }
        });

        String[] tabTitles = activity.getResources().getStringArray(R.array.searchResultType);
        viewPager.setAdapter(new SearchResultViewPagerAdapter(this, searchText));
        new TabLayoutMediator(searchResultType, viewPager, (tab, position) -> tab.setText(tabTitles[position])
        ).attach();
    }

    @OnClick({R.id.top_bar_back, R.id.btn_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.top_bar_back:
                getActivity().onBackPressed();
                break;
            case R.id.btn_search:
                searchText = inputBox.getText().toString();
                viewPager.setAdapter(new SearchResultViewPagerAdapter(this, searchText));
                break;
        }
    }
}