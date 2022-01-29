package com.crystallightghot.frscommunityclient.view.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.fragment.UserInformationFragment;
import com.crystallightghot.frscommunityclient.view.util.FRSCFragmentManageUtil;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author crystallightghost
 * @date 2022/1/5
 * @Version: 1.0
 * description：
 */
public class HomeSearchUserResultRecyclerViewAdapter extends RecyclerView.Adapter<HomeSearchUserResultRecyclerViewAdapter.MyViewHolder> {

    Activity activity;
    List<HashMap<Object, Object>> dates;


    public HomeSearchUserResultRecyclerViewAdapter(List<HashMap<Object, Object>> dates) {
        this.activity = FRSCApplicationContext.getActivity();
        this.dates = dates;
    }

    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.recycle_item_search_user, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FRSCFragmentManageUtil.intentToFragmentAddedToBackStack(UserInformationFragment.newInstance("UserInformationFragment"));
                }
            });
        }

        public void setData(Map<Object, Object> data) {

        }
    }
}
