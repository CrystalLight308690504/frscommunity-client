package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.crystallightghot.frscommunityclient.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditMyDescriptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditMyDescriptionFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;

    public EditMyDescriptionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment EditMyDescriptionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditMyDescriptionFragment newInstance(String param1) {
        EditMyDescriptionFragment fragment = new EditMyDescriptionFragment();
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
        return inflater.inflate(R.layout.fragment_edit_my_description, container, false);
    }
}