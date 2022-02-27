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
import com.crystallightghot.frscommunityclient.presenter.MyFanRecycleViewAdapterViewHolderPresenter;
import com.crystallightghot.frscommunityclient.presenter.UserFollowedRecycleViewAdapterViewHolderPresenter;
import com.crystallightghot.frscommunityclient.view.fragment.UserInformationFragment;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.crystallightghot.frscommunityclient.view.pojo.system.UserFollowerEntity;
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
public class MyFanRecycleViewAdapter extends RecyclerView.Adapter<MyFanRecycleViewAdapter.ViewHolder> {

    @Getter
    final List<UserFollowerEntity> usersFollowers = new ArrayList<>();


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(FRSCApplicationContext.getActivity()).inflate(R.layout.recycle_item_search_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.initView(usersFollowers.get(position));
    }

    @Override
    public int getItemCount() {
        return usersFollowers.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private UserFollowerEntity userFollower;

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

        User user;
        MyFanRecycleViewAdapterViewHolderPresenter presenter;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void initView(UserFollowerEntity userFollower) {
            this.userFollower = userFollower;
            user = userFollower.getUser();

            presenter = new MyFanRecycleViewAdapterViewHolderPresenter(this);
            Drawable userProfile = FRSCImagePatternChangeUtil.getDrawableFromBase64(user.getProfile());
            ivProfile.setImageDrawable(userProfile);
            ivProfile.setOnClickListener((view) -> FRSCFragmentUtil.intentToFragmentAddedToBackStack(UserInformationFragment.newInstance(user)));
            tvUserName.setText(user.getUserName());
            btnFollow.setSelected(true);
            btnFollow.setText("已关注");
            presenter.loadBlogCount(user.getUserId());
            presenter.loadFollowerCount(user.getUserId());
        }


        public void showArticleCount(long articleCount) {
            tvArticleCount.setText(""+ articleCount);
        }
        public void showFollowerCount(long followerCount) {
            tvFollowerCount.setText(""+followerCount);
        }

        @OnClick({R.id.ivProfile, R.id.btnFollow})
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ivProfile:
                    FRSCFragmentUtil.intentToFragmentAddedToBackStack(UserInformationFragment.newInstance(user));
                    break;
                case R.id.btnFollow:
                    btnFollow.setSelected(!btnFollow.isSelected());
                    if (btnFollow.isSelected()) { // 取消关注
                        btnFollow.setText("已关注");
                        presenter.followUser(user.getUserId());
                    } else { // 关注
                        btnFollow.setText("关注");
                        presenter.cancelFollower(FRSCApplicationContext.getUser().getUserId(), user.getUserId());
                    }
                    break;
            }
        }

    }


}
