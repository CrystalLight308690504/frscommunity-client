package com.crystallightghot.frscommunityclient.view.adapter;

import android.graphics.drawable.Drawable;
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
import com.crystallightghot.frscommunityclient.presenter.UserSearchResultRecyclerViewAdapterViewHolderPresenter;
import com.crystallightghot.frscommunityclient.view.fragment.UserInformationFragment;
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
 * @author crystallightghost
 * @date 2022/1/5
 * @Version: 1.0
 * description：
 */
public class UserSearchResultRecyclerViewAdapter extends RecyclerView.Adapter<UserSearchResultRecyclerViewAdapter.MyViewHolder> {

    @Getter
    List<User> users = new ArrayList<>();

    public UserSearchResultRecyclerViewAdapter() {
    }

    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(FRSCApplicationContext.getActivity()).inflate(R.layout.recycle_item_search_user, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.initView(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @OnClick({R.id.ivProfile, R.id.btnFollow})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivProfile:
                break;
            case R.id.btnFollow:
                break;
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        UserSearchResultRecyclerViewAdapterViewHolderPresenter presenter;
        View itemView;
        User user;
        @BindView(R.id.ivProfile)
        RadiusImageView ivProfile;
        @BindView(R.id.tvUserName)
        TextView tvUserName;
        @BindView(R.id.btnFollow)
        Button btnFollow;
        @BindView(R.id.tvArticleCount)
        TextView tvArticleCount;
        @BindView(R.id.tvFollowerCount)
        TextView tvFollowerCount;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            ButterKnife.bind(this, itemView);
            presenter = new UserSearchResultRecyclerViewAdapterViewHolderPresenter(this);
            itemView.setOnClickListener((view) -> FRSCFragmentUtil.intentToFragmentAddedToBackStack(UserInformationFragment.newInstance(user)));
        }

        public void initView(User user) {
            this.user = user;
            Drawable userProfile = FRSCImagePatternChangeUtil.getDrawableFromBase64(user.getProfile());
            ivProfile.setImageDrawable(userProfile);
            tvUserName.setText(user.getUserName());
            presenter.loadBlogCount(user.getUserId());
            tvArticleCount.setText("博客总数 " + 1000);
        }

        @OnClick({R.id.ivProfile, R.id.btnFollow})
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ivProfile:
                    break;
                case R.id.btnFollow:
                    break;
            }
        }

        public void showArticleCount(String articleCount) {
            tvArticleCount.setText(articleCount);
        }

        public void showFollowerCount(String followerCount) {
            tvFollowerCount.setText("博客总数 " + followerCount);
        }
    }
}
