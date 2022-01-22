package com.crystallightghot.frscommunityclient.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.crystallightghot.frscommunityclient.R;
import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MineBlogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MineBlogFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.btnAddPackage)
    TextView btnAddPackage;
    @BindView(R.id.rvMyBlogs)
    RecyclerView rvMyBlogs;

    Activity activity;

    // TODO: Rename and change types of parameters
    private String mParam1;

    public MineBlogFragment() {
        // Required empty public constructor
    }

    public static MineBlogFragment newInstance(String param1) {
        MineBlogFragment fragment = new MineBlogFragment();
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
        View view = inflater.inflate(R.layout.fragment_mine_blog, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        activity = getActivity();

        LinearLayoutManager layoutCompat = new LinearLayoutManager(activity);
        rvMyBlogs.setLayoutManager(layoutCompat);

        BlogsPackageAdapter adapter = new BlogsPackageAdapter();
        rvMyBlogs.setAdapter(adapter);
    }

    @OnClick({R.id.btnBack, R.id.btnAddPackage, R.id.rvMyBlogs})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                activity.onBackPressed();
                break;
            case R.id.btnAddPackage:
                break;
        }
    }

    class BlogsPackageAdapter extends RecyclerView.Adapter<BlogsPackageAdapter.ViewHolder> {



        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(activity).inflate(R.layout.recycle_item_my_package,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 20;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.tvPackageName)
            TextView tvPackageName;
            @BindView(R.id.tvTotal)
            TextView tvTotal;
            @BindView(R.id.tvPrivilege)
            TextView tvPrivilege;
            @BindView(R.id.arrow)
            ImageView arrow;
            @BindView(R.id.rvLists)
            RecyclerView rvLists;
            public ViewHolder(@NonNull @NotNull View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                tvPackageName.setText("589746645645656");
            }
        }
    }
}