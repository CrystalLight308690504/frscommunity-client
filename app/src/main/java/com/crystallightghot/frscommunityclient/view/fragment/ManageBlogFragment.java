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
import com.crystallightghot.frscommunityclient.view.adapter.ManageBlogRecycleViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ManageBlogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManageBlogFragment extends Fragment {

    @BindView(R.id.rvLists)
    RecyclerView rvLists;
    ManageBlogRecycleViewAdapter adapter = new ManageBlogRecycleViewAdapter();

    public ManageBlogFragment() {
    }


    // TODO: Rename and change types and number of parameters
    public static ManageBlogFragment newInstance() {
        ManageBlogFragment fragment = new ManageBlogFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        rvLists.setAdapter(adapter);
    }
}