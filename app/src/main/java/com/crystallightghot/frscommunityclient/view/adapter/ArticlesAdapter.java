package com.crystallightghot.frscommunityclient.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.fragment.ArticleContentSpecifiedFragment;
import com.crystallightghot.frscommunityclient.view.pojo.blog.Blog;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.FRSCFragmentUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * @Date 2022/1/23
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {
    ArrayList<Blog> blogs;

    public ArticlesAdapter(ArrayList<Blog> blogs) {
        this.blogs = blogs;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(FRSCApplicationContext.getActivity()).inflate(R.layout.recycle_item_articles, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.initView(blogs.get(position));
    }

    @Override
    public int getItemCount() {
        return blogs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.contentTitle)
        TextView contentTitle;
        @BindView(R.id.timeCreated)
        TextView timeCreated;
        View itemView;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            ButterKnife.bind(this, itemView);
        }

        public void initView(Blog blog) {
            contentTitle.setText(blog.getBlogTitle());
            timeCreated.setText(blog.getCreatedTime().toString());
            itemView.setOnClickListener(view -> {
                FRSCFragmentUtil.intentToFragmentAddedToBackStack(ArticleContentSpecifiedFragment.newInstance(blog));
            });

        }
    }
}