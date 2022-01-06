package com.crystallightghot.frscommunityclient.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.util.ActivityUtile;
import com.crystallightghot.frscommunityclient.widget.EditSpinnerDialog;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultSearchedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultSearchedFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    AppCompatActivity activity;
static ResultSearchedFragment fragment;
    public ResultSearchedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment ResultSearchedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResultSearchedFragment newInstance(String param1) {

        if (null == fragment) {
            fragment = ResultSearchedFragment.newInstance("dd");
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

        init();
    }

    private void init() {
        activity = (AppCompatActivity) getActivity();
        String[] s = {"dfasf","sfsdf"};
    }

    private void showResultArrangementWayDialog(String[] s) {
        EditSpinnerDialog.newBuilder(activity)
                .setTitle("title").setText("data")
                .setDefaultItems(s)
                .setOnEditListener(new EditSpinnerDialog.OnEditListener() {
                    @Override
                    public void onEdit(String value) {

                    }
                })
                .show();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result_searched, container, false);
        ButterKnife.bind(view);
        return view;
    }

    @Override
    public void onStop() {
        ActivityUtile.hideFragment(activity,this);
        super.onStop();
    }
}