package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.adapter.HelpViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HelpNeededFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HelpNeededFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    Unbinder bind;

    static HelpNeededFragment blogFragment;
    @BindView(R.id.tbSkatingTypes)
    TabLayout tbSkatingTypes;
    @BindView(R.id.more_list)
    ImageButton moreList;
    @BindView(R.id.btnSearch)
    ImageButton btnSearch;
    @BindView(R.id.viewPager)
    ViewPager2 viewPager;

    public HelpNeededFragment() {
        // Required empty public constructor
    }

    public static HelpNeededFragment newInstance(String param1) {

        if (null == blogFragment) {
            blogFragment = new HelpNeededFragment();
            Bundle args = new Bundle();
            args.putString(ARG_PARAM1, param1);
            blogFragment.setArguments(args);
        }
        return blogFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_help_needed, container, false);
        bind = ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        String[] types = getResources().getStringArray(R.array.tags_values);
        viewPager.setAdapter(new HelpViewPagerAdapter(this));
        new TabLayoutMediator(tbSkatingTypes, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull @NotNull TabLayout.Tab tab, int position) {
                tab.setText(types[position]);
            }
        }).attach();



    }

    @OnClick({R.id.more_list, R.id.btnSearch})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.more_list:
                break;
            case R.id.btnSearch:
                break;
            default:
                break;
        }
    }
}
