package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.presenter.MyBlogPresenter;
import com.crystallightghot.frscommunityclient.view.adapter.ArticlesAdapter;
import com.crystallightghot.frscommunityclient.view.pojo.blog.Blog;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ArticlesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArticlesFragment extends BaseFragment {

    MyBlogPresenter presenter;
    Long categoryId;
    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.articles)
    RecyclerView articles;

    public ArticlesFragment(Long categoryId) {
        this.categoryId = categoryId;
        presenter = new MyBlogPresenter(this);
    }


    public static ArticlesFragment newInstance(Long categoryId) {
        ArticlesFragment fragment = new ArticlesFragment(categoryId);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_articles, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    public void initView() {
        loadingData(categoryId);
    }

    public void loadingData(Long categoryId) {
        presenter.loadingBlogs(categoryId);
    }

    public void addDataToList(ArrayList<Blog> blogs) {
        articles.setAdapter(new ArticlesAdapter(blogs));
    }


}