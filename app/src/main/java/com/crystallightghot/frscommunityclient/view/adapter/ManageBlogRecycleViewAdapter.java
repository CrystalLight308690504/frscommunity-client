package com.crystallightghot.frscommunityclient.view.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.presenter.ManageBlogRecycleViewAdapterViewHolderPresenter;
import com.crystallightghot.frscommunityclient.view.fragment.ArticleContentSpecifiedFragment;
import com.crystallightghot.frscommunityclient.view.pojo.blog.Blog;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.FRSCIntentUtil;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2022/3/9
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class ManageBlogRecycleViewAdapter extends RecyclerView.Adapter<ManageBlogRecycleViewAdapter.ViewHolder> {

    @Getter
    List<Blog> blogs = new ArrayList<>();


    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(FRSCApplicationContext.getActivity()).inflate(R.layout.recycle_item_manage_blog, parent, false);
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
        @BindView(R.id.btnIsShowed)
        Button btnIsShowed;
        @BindView(R.id.tvIsShowed)
        TextView tvIsShowed;

        private Blog blog;
        ManageBlogRecycleViewAdapterViewHolderPresenter presenter;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            presenter = new ManageBlogRecycleViewAdapterViewHolderPresenter(this);
            itemView.setOnClickListener(view -> FRSCIntentUtil.intentToSingleFragmentActivity(ArticleContentSpecifiedFragment.newInstance(blog)));
        }

        public void initView(Blog blog) {
            this.blog = blog;
            String profileBase64 = blog.getUser().getProfile();
            byte[] decodedString = Base64.decode(profileBase64, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            profile.setImageDrawable(new BitmapDrawable(decodedByte));
            userName.setText(blog.getUser().getUserName());
            putDate.setText(blog.getCreatedTime().toString());
            articleType.setText(blog.getSkatingType().getName());
            articleTitle.setText(blog.getBlogTitle());
            articleContent.setText(blog.getContent());
            if (blog.getIsShowed() == 1) {
                tvIsShowed.setText("可见");
                btnIsShowed.setSelected(false);
                btnIsShowed.setText("不可见");
            } else {
                tvIsShowed.setText("不可见");
                btnIsShowed.setSelected(true);
                btnIsShowed.setText("可见");
            }
        }

        @OnClick({R.id.profile, R.id.btnIsShowed})
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.profile:
                    break;
                case R.id.btnIsShowed:
                    btnIsShowed.setSelected(!btnIsShowed.isSelected());
                    if (btnIsShowed.isSelected()) {
                        presenter.changIsShowed(blog.getBlogId(), false);
                        btnIsShowed.setText("可见");
                    } else {
                        btnIsShowed.setText("不可见");
                        presenter.changIsShowed(blog.getBlogId(), true);
                    }
                    break;
            }
        }
    }
}
