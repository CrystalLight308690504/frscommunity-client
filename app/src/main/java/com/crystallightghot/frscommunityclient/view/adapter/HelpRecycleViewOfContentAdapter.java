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
import com.crystallightghot.frscommunityclient.utils.FRSCApplicationContext;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView2;
import org.jetbrains.annotations.NotNull;

/**
 * @Date 2022/1/25
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class HelpRecycleViewOfContentAdapter extends RecyclerView.Adapter<HelpRecycleViewOfContentAdapter.ViewHolder> {




    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(FRSCApplicationContext.getActivity()).inflate(R.layout.fragment_help_recycle_view_question_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.profile)
        QMUIRadiusImageView2 profile;
        @BindView(R.id.userName)
        TextView userName;
        @BindView(R.id.dateCreated)
        TextView dateCreated;
        @BindView(R.id.type)
        TextView type;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.content)
        TextView content;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
