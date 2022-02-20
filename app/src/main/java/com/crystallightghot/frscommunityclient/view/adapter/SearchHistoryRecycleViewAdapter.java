package com.crystallightghot.frscommunityclient.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.pojo.blog.SearchHistory;
import com.crystallightghot.frscommunityclient.view.pojo.blog.SearchHistoryDao;
import com.crystallightghot.frscommunityclient.view.pojo.system.LoginInformationDao;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.FRSCDataBaseUtil;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @Date 2022/2/20
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class SearchHistoryRecycleViewAdapter extends RecyclerView.Adapter<SearchHistoryRecycleViewAdapter.ViewHolder> {
    @Getter
    List<SearchHistory> searchHistories;


    public SearchHistoryRecycleViewAdapter(List<SearchHistory> searchHistories) {
        this.searchHistories = searchHistories;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(FRSCApplicationContext.getActivity()).inflate(R.layout.recycle_item_search_history, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.initView(searchHistories.get(position));
    }

    @Override
    public int getItemCount() {
        return searchHistories.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvSearchText)
        TextView tvSearchText;
        @BindView(R.id.btnDelete)
        AppCompatImageButton btnDelete;
        SearchHistory searchHistory;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void initView(SearchHistory searchHistory) {
            this.searchHistory = searchHistory;
            tvSearchText.setText(searchHistory.getSearchText());
        }

        @OnClick(R.id.btnDelete)
        public void onClick() {
            SearchHistoryDao searchHistoryDao = FRSCDataBaseUtil.getWriteDaoSession().getSearchHistoryDao();
            searchHistories.remove(searchHistory);
            searchHistoryDao.delete(searchHistory);
            notifyDataSetChanged();
        }
    }

}
