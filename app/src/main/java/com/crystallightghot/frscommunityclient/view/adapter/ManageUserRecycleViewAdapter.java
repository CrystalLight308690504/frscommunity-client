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
import com.crystallightghot.frscommunityclient.presenter.ManageUserRecycleViewAdapterViewHolderPresenter;
import com.crystallightghot.frscommunityclient.view.fragment.ManageBlogFragment;
import com.crystallightghot.frscommunityclient.view.fragment.ManageUserFragment;
import com.crystallightghot.frscommunityclient.view.fragment.UserInformationFragment;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.FRSCFragmentUtil;
import com.crystallightghot.frscommunityclient.view.util.FRSCImagePatternChangeUtil;
import com.crystallightghot.frscommunityclient.view.util.FRSCIntentUtil;
import com.xuexiang.xui.widget.dialog.bottomsheet.BottomSheet;
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
public class ManageUserRecycleViewAdapter extends RecyclerView.Adapter<ManageUserRecycleViewAdapter.ViewHolder> {

    @Getter
    List<User> users = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(FRSCApplicationContext.getActivity()).inflate(R.layout.recycle_view_manage_user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.initView(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements BottomSheet.BottomListSheetBuilder.OnSheetItemClickListener {
        private User user;
        @BindView(R.id.ivProfile)
        RadiusImageView ivProfile;
        @BindView(R.id.tvUserName)
        TextView tvUserName;
        @BindView(R.id.btnManage)
        Button btnManage;
        @BindView(R.id.tvUserRoleName)
        TextView tvUserRoleName;
        ManageUserRecycleViewAdapterViewHolderPresenter presenter;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            presenter = new ManageUserRecycleViewAdapterViewHolderPresenter(this);
            ButterKnife.bind(this, itemView);
        }

        public void initView(User user) {
            this.user = user;
            Drawable userProfile = FRSCImagePatternChangeUtil.getDrawableFromBase64(user.getProfile());
            ivProfile.setImageDrawable(userProfile);
            tvUserName.setText(user.getUserName());
            tvUserRoleName.setText(user.getRole().getRoleName());
        }

        @OnClick({R.id.ivProfile, R.id.btnManage})
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ivProfile:
                    FRSCFragmentUtil.intentToFragmentAddedToBackStack(UserInformationFragment.newInstance(user));
                    break;
                case R.id.btnManage:
                    new BottomSheet.BottomListSheetBuilder(FRSCApplicationContext.getActivity())
                            .addItem("普通用户", "普通用户")
                            .addItem("用户管理员", "用户管理员")
                            .addItem("博客管理员", "博客管理员")
                            .addItem("超级管理员", "超级管理员")
                            .setIsCenter(true)
                            .setOnSheetItemClickListener(this)
                            .build()
                            .show();
                    break;
            }
        }

        @Override
        public void onClick(BottomSheet dialog, View itemView, int position, String tag) {
            dialog.dismiss();
            switch (tag) {
                case "普通用户":
                    presenter.changeRole(user.getUserId(),1L);
                    break;
                case "博客管理员":
                    presenter.changeRole(user.getUserId(),2);
                    break;
                case "用户管理员":
                    presenter.changeRole(user.getUserId(),3);
                    break;
                case "超级管理员":
                    presenter.changeRole(user.getUserId(),4);
                    break;
                default:
                    break;
            }
        }
    }

}
