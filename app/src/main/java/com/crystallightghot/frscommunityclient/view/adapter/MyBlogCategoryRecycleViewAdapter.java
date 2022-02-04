package com.crystallightghot.frscommunityclient.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.fragment.ArticlesFragment;
import com.crystallightghot.frscommunityclient.view.pojo.blog.BlogCategory;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.FRSCFragmentUtil;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @Date 2022/1/23
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class MyBlogCategoryRecycleViewAdapter extends RecyclerView.Adapter<MyBlogCategoryRecycleViewAdapter.ViewHolder> {
    List<BlogCategory> blogCategories;

    public MyBlogCategoryRecycleViewAdapter(List<BlogCategory> blogCategories) {
        this.blogCategories = blogCategories;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(FRSCApplicationContext.getActivity()).inflate(R.layout.recycle_item_my_category, parent, false);
        ButterKnife.bind(this, view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.init(blogCategories.get(position));
    }

    @Override
    public int getItemCount() {
        return blogCategories.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvPackageName)
        TextView tvPackageName;
        @BindView(R.id.tvTotal)
        TextView tvTotal;
        @BindView(R.id.tvPrivilege)
        TextView tvPrivilege;
        @BindView(R.id.ivArrow)
        ImageView ivArrow;
        @BindView(R.id.rvLists)
        RecyclerView rvLists;

        BlogCategory category;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void init(BlogCategory category) {
            this.category = category;
            tvPackageName.setText(category.getCategoryName());

            itemView.setOnClickListener(view -> {
                FRSCFragmentUtil.intentToFragmentAddedToBackStack(ArticlesFragment.newInstance(category.getCategoryId()));
            });
        }
    }
}
