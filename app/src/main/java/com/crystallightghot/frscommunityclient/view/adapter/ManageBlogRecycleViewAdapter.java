package com.crystallightghot.frscommunityclient.view.adapter;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

/**
 * @Date 2022/3/9
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class ManageBlogRecycleViewAdapter extends RecyclerView.Adapter<ManageBlogRecycleViewAdapter.ViewHolder> {

    @Override
    public ManageBlogRecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ManageBlogRecycleViewAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }
}
