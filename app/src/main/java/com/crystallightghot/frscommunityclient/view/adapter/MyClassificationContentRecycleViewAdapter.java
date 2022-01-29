package com.crystallightghot.frscommunityclient.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import org.jetbrains.annotations.NotNull;

/**
 * @Date 2022/1/23
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class MyClassificationContentRecycleViewAdapter extends RecyclerView.Adapter<MyClassificationContentRecycleViewAdapter.ViewHolder> {

    @NonNull
    @NotNull
    @Override
    public MyClassificationContentRecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(FRSCApplicationContext.getActivity()).inflate(R.layout.recycle_item_my_classsification_content, parent, false);
        MyClassificationContentRecycleViewAdapter.ViewHolder viewHolder = new MyClassificationContentRecycleViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyClassificationContentRecycleViewAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }
}