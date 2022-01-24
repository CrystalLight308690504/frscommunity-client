package com.crystallightghot.frscommunityclient.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.utils.FRSCApplicationContext;
import org.jetbrains.annotations.NotNull;

/**
 * @Date 2022/1/23
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public  class MyClassificationRecycleViewAdapter extends RecyclerView.Adapter<MyClassificationRecycleViewAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(FRSCApplicationContext.getActivity()).inflate(R.layout.recycle_item_my_classfication, parent, false);
        ButterKnife.bind(this, view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvPackageName)
        TextView tvPackageName;
        @BindView(R.id.tvTotal)
        TextView tvTotal;
        @BindView(R.id.tvPrivilege)
        TextView tvPrivilege;
        @BindView(R.id.ivArrow)
        ImageView ivArrow;
        @BindView(R.id.rvLists)
        RecyclerView rvLists;
        boolean rvListsShowed = false;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            init();

        }

        private void init() {
            rvLists.setAdapter(new MyClassificationContentRecycleViewAdapter());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rvListsShowed = !rvListsShowed;
                    if (rvListsShowed) {
                        rvLists.setVisibility(View.VISIBLE);
                        ivArrow.setRotation(90);
                    } else {
                        rvLists.setVisibility(View.GONE);
                        ivArrow.setRotation(0);
                    }

                }
            });
        }
    }
}