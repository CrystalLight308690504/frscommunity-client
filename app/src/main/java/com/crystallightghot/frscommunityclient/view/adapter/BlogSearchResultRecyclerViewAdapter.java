package com.crystallightghot.frscommunityclient.view.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.fragment.ArticleContentSpecifiedFragment;
import com.crystallightghot.frscommunityclient.view.pojo.blog.Blog;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.FRSCFragmentUtil;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author crystallightghost
 * @date 2022/1/5
 * @Version: 1.0
 * description：
 */
public class BlogSearchResultRecyclerViewAdapter extends RecyclerView.Adapter<BlogSearchResultRecyclerViewAdapter.MyViewHolder> {

    @Getter
    List<Blog> blogs;
    public BlogSearchResultRecyclerViewAdapter() {
        this.blogs = new ArrayList<>();
    }

    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(FRSCApplicationContext.getActivity()).inflate(R.layout.recycle_item_article_lists, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.initView(blogs.get(position));
    }

    @Override
    public int getItemCount() {
        return blogs.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView userNameTV;
        TextView putDateTV;
        TextView articleStyle;
        TextView articleTitleTV;
        TextView articleContentTV;
        @BindView(R.id.profile)
        RadiusImageView profile;
        @BindView(R.id.image1)
        RadiusImageView image1;
        View itemView;
        Blog blog;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            profile = itemView.findViewById(R.id.profile);
            userNameTV = itemView.findViewById(R.id.user_name);
            putDateTV = itemView.findViewById(R.id.put_date);
            articleStyle = itemView.findViewById(R.id.articleType);
            articleTitleTV = itemView.findViewById(R.id.article_title);
            articleContentTV = itemView.findViewById(R.id.article_content);
            image1 = itemView.findViewById(R.id.image1);
            itemView.setOnClickListener((view) -> FRSCFragmentUtil.intentToFragmentAddedToBackStack(ArticleContentSpecifiedFragment.newInstance(blog)));
        }

        public void initView(Blog blog) {
            this.blog = blog;
            String profileBase64 = blog.getUser().getProfile();
            byte[] decodedString = Base64.decode(profileBase64, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            profile.setImageDrawable(new BitmapDrawable(decodedByte));
            userNameTV.setText(blog.getUser().getUserName());
            putDateTV.setText(blog.getCreatedTime().toString());
            articleStyle.setText(blog.getSkatingType().getName());
            articleTitleTV.setText(blog.getBlogTitle());
            articleContentTV.setText(blog.getContent());
            userNameTV.setText(blog.getUser().getUserName());

        }
    }
}
