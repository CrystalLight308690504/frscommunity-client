package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.crystallightghot.frscommunityclient.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PutBlogConfirmFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PutBlogConfirmFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.icCollection)
    TextView icCollection;
    @BindView(R.id.spinnerCollection)
    AppCompatSpinner spinnerCollection;
    @BindView(R.id.icCategory)
    TextView icCategory;
    @BindView(R.id.spinnerCategory)
    AppCompatSpinner spinnerCategory;
    @BindView(R.id.btnPosition)
    Button btnPosition;


    private String mParam1;

    public PutBlogConfirmFragment() {

    }

    public static PutBlogConfirmFragment newInstance(String param1) {
        PutBlogConfirmFragment fragment = new PutBlogConfirmFragment();
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
        View view = inflater.inflate(R.layout.fragment_put_blog_confirm, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("ddd");
        list.add("d4d");
        list.add("dd44");
        list.add("dd44");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_default_item, R.id.text1, list);
        spinnerCategory.setAdapter(arrayAdapter);
        spinnerCategory.setOnItemSelectedListener(new CategorySpinnerItemSelectedListener());
        spinnerCollection.setAdapter(arrayAdapter);
        spinnerCollection.setOnItemSelectedListener(new CollectionSpinnerItemSelectedListener());
    }

    @OnClick(R.id.btnPosition)
    public void onClick() {
        getActivity().onBackPressed();
    }

    class CollectionSpinnerItemSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    class CategorySpinnerItemSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}