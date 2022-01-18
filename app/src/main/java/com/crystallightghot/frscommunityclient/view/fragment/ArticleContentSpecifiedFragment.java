package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.crystallightghot.frscommunityclient.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ArticleContentSpecifiedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArticleContentSpecifiedFragment extends Fragment {
    static ArticleContentSpecifiedFragment fragment;

    public ArticleContentSpecifiedFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ArticleContentSpecifiedFragment newInstance(String param1) {
        if (null == fragment){
            fragment = new ArticleContentSpecifiedFragment();
            Bundle args = new Bundle();
            fragment.setArguments(args);
        }
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article_content_spefied, container, false);
    }
}