package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.presenter.PutBlogContentPresenter;
import com.crystallightghot.frscommunityclient.view.pojo.blog.Blog;
import com.crystallightghot.frscommunityclient.view.util.FRSCFragmentUtil;
import com.crystallightghot.frscommunityclient.view.util.XToastUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.nanchen.compresshelper.StringUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class PutBlogContentFragment extends BaseFragment {

    // TODO: Rename parameter arguments, choose names that match

    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.btnPosition)
    AppCompatButton btnPosition;
    @BindView(R.id.ieTitle)
    TextInputEditText ieTitle;
    @BindView(R.id.ieContent)
    TextInputEditText ieContent;

    PutBlogContentPresenter presenter;

    @Getter
    String blogTitle;
    @Getter
    String blogContent;
    @Getter
    @Setter
    private  Blog blog;

    // TODO: Rename and change types of parameters
    private String mParam1;

    public PutBlogContentFragment() {
        presenter = new PutBlogContentPresenter(this);
        // Required empty public constructor
    }

    public PutBlogContentFragment(Blog blog) {
        this.blog = blog;
        presenter = new PutBlogContentPresenter(this);
        // Required empty public constructor
    }

    public static PutBlogContentFragment newInstance(String param1) {
        PutBlogContentFragment fragment = new PutBlogContentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public static Fragment newInstance(Blog blog) {
        PutBlogContentFragment fragment = new PutBlogContentFragment(blog);
        Bundle args = new Bundle();
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
        View view = inflater.inflate(R.layout.fragment_put_blog, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    public void initView() {
        if (null != blog) {
            ieTitle.setText(blog.getBlogTitle());
            ieContent.setText(blog.getContent());
        }
    }

    @OnClick({R.id.btn_back, R.id.btnPosition})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                getActivity().onBackPressed();
                break;
            case R.id.btnPosition:
                blogTitle = ieTitle.getText().toString();
                blogContent = ieContent.getText().toString();
                if (StringUtil.isEmpty(blogTitle) ) {
                    XToastUtils.warning("请输入标题");
                    return;
                }else if (StringUtil.isEmpty(blogContent)) {
                    XToastUtils.warning("请输入内容");
                    return;
                }
                FRSCFragmentUtil.intentToFragmentAddedToBackStack(PutBlogConfirmFragment.newInstance(""));
                break;
        }
    }

}