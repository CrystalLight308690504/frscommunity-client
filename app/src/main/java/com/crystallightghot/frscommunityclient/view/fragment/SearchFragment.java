package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.activity.SearchActivity;
import com.crystallightghot.frscommunityclient.view.util.ActivityUtile;
import com.google.android.material.textfield.TextInputEditText;
import com.qmuiteam.qmui.widget.QMUIFloatLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
    static SearchFragment fragment;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.top_bar_back)
    ImageButton topBarBack;
    @BindView(R.id.input_box)
    TextInputEditText inputBox;
    @BindView(R.id.btn_search)
    TextView btnSearch;


    @BindView(R.id.rv_search_history)
    RecyclerView lvSearchHistory;

    SearchActivity activity;
    @BindView(R.id.hot_searches)
    QMUIFloatLayout hotSearches;

    final int FRAGMENTCONTERID = R.id.fragment_container;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }


    public static SearchFragment newInstance(String string) {
        if (null == fragment) {
            fragment = new SearchFragment();
            Bundle args = new Bundle();
            args.putString(ARG_PARAM1, string);
            fragment.setArguments(args);
        }
        return fragment;
    }


    private void init() {
        activity = (SearchActivity) getActivity();
        setSearchHistories();
        topBarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Log.d("数量", "回退栈数量: " + fragmentManager.getBackStackEntryCount());
                if (fragmentManager.getBackStackEntryCount() == 1) { // 剩下的只有空的activity
                    getActivity().finish();
                } else {
                    fragmentManager.popBackStack();
                }
                Log.d("数量", "回退栈数量 退栈后: " + fragmentManager.getBackStackEntryCount());
            }
        });

    }

    public void setSearchHistories() {


        TextView textView = new TextView(activity);
        textView.setText("轮滑");
        hotSearches.addView(textView);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
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

    @OnClick(R.id.btn_search)
    public void onClick(View view) {
        ResultSearchedFragment resultSearchedFragment = new ResultSearchedFragment();
        ActivityUtile.showFragment(resultSearchedFragment, activity,null,FRAGMENTCONTERID);
    }
}