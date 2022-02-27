package com.crystallightghot.frscommunityclient.view.adapter;

import android.graphics.drawable.Drawable;
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
import com.crystallightghot.frscommunityclient.view.fragment.ArticlesFragment;
import com.crystallightghot.frscommunityclient.view.pojo.blog.Blog;
import com.crystallightghot.frscommunityclient.view.pojo.blog.BlogCategory;
import com.crystallightghot.frscommunityclient.view.pojo.blog.BlogCollection;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.FRSCFragmentUtil;
import com.crystallightghot.frscommunityclient.view.util.FRSCImagePatternChangeUtil;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2022/2/27
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class MyBlogCollectionAdapter extends RecyclerView.Adapter<MyBlogCollectionAdapter.ViewHolder> {
    @Getter
    final List<BlogCollection> blogCollections = new ArrayList<>();


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(FRSCApplicationContext.getActivity()).inflate(R.layout.recycle_item_article_lists, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.init(blogCollections.get(position), position);
    }

    @Override
    public int getItemCount() {
        return blogCollections.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.profile)
        RadiusImageView profile;
        @BindView(R.id.user_name)
        TextView userName;
        @BindView(R.id.put_date)
        TextView putDate;
        @BindView(R.id.articleType)
        TextView articleType;
        @BindView(R.id.article_title)
        TextView articleTitle;
        @BindView(R.id.article_content)
        TextView articleContent;
        private int position;
        private BlogCollection blogCollection;
        private Blog blog;
        private User user;


        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void init(BlogCollection blogCollection, int position) {
            this.position = position;
            this.blogCollection = blogCollection;
            blog = blogCollection.getBlog();
            user = blog.getUser();

            itemView.setOnClickListener(view -> {
                FRSCFragmentUtil.intentToFragmentAddedToBackStack(ArticleContentSpecifiedFragment.newInstance(blog));
            });
            Drawable drawable = FRSCImagePatternChangeUtil.getDrawableFromBase64(blog.getUser().getProfile());
            profile.setImageDrawable(drawable);
            userName.setText(user.getUserName());
            putDate.setText(blogCollection.getCreatedTime().toString());
            articleType.setText(blog.getSkatingType().getName());
            articleTitle.setText(blog.getBlogTitle());
            articleContent.setText(blog.getContent());
        }
    }

}
