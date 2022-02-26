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
import butterknife.OnClick;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.presenter.BlogCriticismAdapterViewHolderPresenter;
import com.crystallightghot.frscommunityclient.view.pojo.blog.BlogCriticism;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.FRSCImagePatternChangeUtil;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2022/2/26
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class BlogCriticismAdapter extends RecyclerView.Adapter<BlogCriticismAdapter.ViewHolder> {

    @Getter
    List<BlogCriticism> blogCriticisms = new ArrayList<>();

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(FRSCApplicationContext.getActivity()).inflate(R.layout.recycle_item_criticism, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.initView(blogCriticisms.get(position), position);
    }

    @Override
    public int getItemCount() {
        return blogCriticisms.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivUserProfile)
        RadiusImageView ivUserProfile;
        @BindView(R.id.tvUserName)
        TextView tvUserName;
        @BindView(R.id.tvCriticismContent)
        TextView tvCriticismContent;
        @BindView(R.id.btnDeleteCriticism)
        TextView btnDeleteCriticism;
        @BindView(R.id.btnReplyCriticism)
        TextView btnReplyCriticism;
        @BindView(R.id.tvCreatedTime)
        TextView tvCreatedTime;

        int position;
        private BlogCriticism blogCriticism;
        BlogCriticismAdapterViewHolderPresenter presenter;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            presenter = new BlogCriticismAdapterViewHolderPresenter(this);
        }

        public void initView(BlogCriticism blogCriticism, int position) {
            this.position = position;
            this.blogCriticism = blogCriticism;
            if(blogCriticism.getUser().getUserId().equals(FRSCApplicationContext.getUser().getUserId())) {
                btnDeleteCriticism.setVisibility(View.VISIBLE);
                btnReplyCriticism.setVisibility(View.INVISIBLE);
            }else {
                btnDeleteCriticism.setVisibility(View.INVISIBLE);
                btnReplyCriticism.setVisibility(View.INVISIBLE);
            }
            tvUserName.setText(blogCriticism.getUser().getUserName());
            Drawable userProfile = FRSCImagePatternChangeUtil.getDrawableFromBase64(blogCriticism.getUser().getProfile());
            ivUserProfile.setImageDrawable(userProfile);
            tvCriticismContent.setText(blogCriticism.getContent());
            tvCreatedTime.setText(blogCriticism.getCreatedTime().toString());
        }

        public void deleteBlogCriticiseSuccess(){
            notifyItemRemoved(position);
        }


        @OnClick({R.id.btnDeleteCriticism, R.id.btnReplyCriticism})
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnDeleteCriticism:
                    presenter.deleteBlogCriticise(blogCriticism);
                    break;
                case R.id.btnReplyCriticism:
                    break;
            }
        }
    }
}
