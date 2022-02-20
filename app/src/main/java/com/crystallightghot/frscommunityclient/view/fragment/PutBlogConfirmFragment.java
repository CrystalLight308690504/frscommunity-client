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
import com.crystallightghot.frscommunityclient.presenter.PutBlogConfirmPresenter;
import com.crystallightghot.frscommunityclient.presenter.PutBlogContentPresenter;
import com.crystallightghot.frscommunityclient.view.message.TransportDataMessage;
import com.crystallightghot.frscommunityclient.view.pojo.blog.BlogCategory;
import com.crystallightghot.frscommunityclient.view.pojo.skatingtype.SkatingType;
import com.crystallightghot.frscommunityclient.view.util.FRSCEventBusUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PutBlogConfirmFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PutBlogConfirmFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.icCollection)
    TextView icCollection;
    @BindView(R.id.spinnerCategory)
    AppCompatSpinner spinnerCategory;
    @BindView(R.id.icCategory)
    TextView icCategory;
    @BindView(R.id.spinnerSkatingType)
    AppCompatSpinner spinnerSkatingType;
    @BindView(R.id.btnPosition)
    Button btnPosition;

    PutBlogConfirmPresenter presenter;
    ArrayList<BlogCategory> blogCategories;
    ArrayList<SkatingType> skatingTypes;
    int categoryPosition = 0;
    int skatingTypePosition = 0;

    boolean loadingBlogCategoriesSuccess;
    boolean loadingSkatingTypeSuccess;
    private String mParam1;

    public PutBlogConfirmFragment() {
        presenter = new PutBlogConfirmPresenter(this);

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
        presenter.loadingCategory();
        presenter.loadingSkatingType();
    }

    @OnClick(R.id.btnPosition)
    public void onClick() {
        Map<String, Object> data = new HashMap<>();
        data.put("blogCategory", blogCategories.get(categoryPosition));
        data.put("skatingType",skatingTypes.get(skatingTypePosition));
        TransportDataMessage message = new TransportDataMessage(data, PutBlogContentPresenter.RespondMessageKey.PUT_BLOG_CONFIRM_REQUEST);
        FRSCEventBusUtil.sendMessage(message);
        getActivity().onBackPressed();
    }

    public void loadingBlogCategories(ArrayList blogCategoriesName) {
        loadingBlogCategoriesSuccess = true;
        if (loadingSkatingTypeSuccess) {
            btnPosition.setEnabled(true);
        }else {
            btnPosition.setEnabled(false);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(getContext(), R.layout.spinner_default_item, R.id.text1, blogCategoriesName);
        spinnerCategory.setAdapter(arrayAdapter);
        spinnerCategory.setOnItemSelectedListener(new CategorySpinnerItemSelectedListener());
    }

    public void loadingSkatingType(ArrayList<String> skatingTypesName) {
        loadingSkatingTypeSuccess = true;
        if (loadingBlogCategoriesSuccess ) {
            btnPosition.setEnabled(true);
        }else {
            btnPosition.setEnabled(false);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(getContext(), R.layout.spinner_default_item, R.id.text1, skatingTypesName);
        spinnerSkatingType.setAdapter(arrayAdapter);
        spinnerSkatingType.setOnItemSelectedListener(new SkatingTypeSpinnerItemSelectedListener());

    }

    public void getCategoryList(ArrayList<BlogCategory> categories) {
        this.blogCategories = categories;
    }

    public void getSkatingTypeList(ArrayList<SkatingType> skatingTypes) {
        this.skatingTypes = skatingTypes;
    }


    class SkatingTypeSpinnerItemSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            skatingTypePosition = pos;

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    class CategorySpinnerItemSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            categoryPosition = pos;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }


}