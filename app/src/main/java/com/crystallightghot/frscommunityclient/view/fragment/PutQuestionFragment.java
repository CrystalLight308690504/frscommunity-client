package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.crystallightghot.frscommunityclient.R;


public class PutQuestionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PutQuestionFragment() {
        // Required empty public constructor
    }

    /**
     * @param param1 Parameter 1.
     * @return A new instance of fragment EditeHelpAcquiredFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PutQuestionFragment newInstance(String param1) {
        PutQuestionFragment fragment = new PutQuestionFragment();
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
        return inflater.inflate(R.layout.fragment_help_edite_acquired, container, false);
    }
}