package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.activity.HomeActivity;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SomethingFoundFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SomethingFoundFragment extends Fragment {
    static SomethingFoundFragment fragment;
    private static final String ARG_PARAM1 = "param1";

    HomeActivity activity;
    @BindView(R.id.viewPager)
    ViewPager2 viewPager;

    @Getter
    List<DemoObjectFragment> fragments = new ArrayList<>();
    DemoObjectFragment lastFragment;

    private String mParam1;

    public SomethingFoundFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SomethingFoundFragment newInstance(String param1) {
        if (null == fragment) {
            fragment = new SomethingFoundFragment();
            Bundle args = new Bundle();
            args.putString(ARG_PARAM1, param1);
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }

    }

    private void init() {
        activity = (HomeActivity) getActivity();
        DemoCollectionAdapter demoCollectionAdapter = new DemoCollectionAdapter(this);
        viewPager.setAdapter(demoCollectionAdapter);
        if (fragments.size() > 0)
             lastFragment = fragments.get(0);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            int lastScrolledInstance = 0;
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Log.d("TAG", "====onPageSelected ====     position >> " +position);
                DemoObjectFragment fragment = fragments.get(position);
                fragment.startVideo();
                if (null != lastFragment){
                    lastFragment.pauseVideo();
                }
                lastFragment = fragment;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

                super.onPageScrollStateChanged(state);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_somthing_found, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

     class DemoCollectionAdapter extends FragmentStateAdapter {
        public DemoCollectionAdapter(Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            // Return a NEW fragment instance in createFragment(int)
            DemoObjectFragment fragment = new DemoObjectFragment(position);
            Bundle args = new Bundle();
            // Our object is just an integer :-P
            args.putInt(DemoObjectFragment.ARG_OBJECT, position + 1);
            fragment.setArguments(args);
            fragments.add(fragment);
            return fragment;
        }

        @Override
        public int getItemCount() {
            return 6;
        }
    }


}