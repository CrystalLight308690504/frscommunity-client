package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.adapter.HelpViewPagerAdapter;
import com.crystallightghot.frscommunityclient.view.util.FRSCIntentUtil;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HelpFragment#newInstance} factory method to
 * create an instance of this fragment.
 * @author 30869
 */
public class HelpFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    Unbinder bind;

    static HelpFragment blogFragment;
    @BindView(R.id.tbSkatingTypes)
    TabLayout tbSkatingTypes;
    @BindView(R.id.more_list)
    ImageButton moreList;
    @BindView(R.id.btnSearch)
    ImageButton btnSearch;
    @BindView(R.id.viewPager)
    ViewPager2 viewPager;

    public HelpFragment() {
        // Required empty public constructor
    }

    public static HelpFragment newInstance(String param1) {

        return new HelpFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_help_needed, container, false);
        bind = ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        String[] types = getResources().getStringArray(R.array.skatingType);
        viewPager.setAdapter(new HelpViewPagerAdapter(this));
        new TabLayoutMediator(tbSkatingTypes, viewPager, (tab, position) -> tab.setText(types[position])).attach();
    }

    @OnClick({R.id.more_list, R.id.btnSearch})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.more_list:
                FRSCIntentUtil.intentToSingleFragmentActivity(SkatingTypeFragment.newInstance(""));
                break;
            case R.id.btnSearch:
                FRSCIntentUtil.intentToSingleFragmentActivity(HelpSearchFragment.newInstance(""));
                break;
            default:
                break;
        }
    }
}
