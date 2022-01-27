package com.crystallightghot.frscommunityclient.view.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.fragment.ArticleContentSpecifiedFragment;
import com.crystallightghot.frscommunityclient.view.util.FRSCFragmentManageUtil;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
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
public class HomeSearchBlogAndHelpResultRecyclerViewAdapter extends RecyclerView.Adapter<HomeSearchBlogAndHelpResultRecyclerViewAdapter.MyViewHolder> {

    Activity activity;
    List<HashMap<Object, Object>> datas;


    public HomeSearchBlogAndHelpResultRecyclerViewAdapter(Activity activity, List<HashMap<Object, Object>> datas) {
        this.activity = activity;
        this.datas = datas;
    }

    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.recycle_item_article_lists, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Map<Object, Object> data = datas.get(position);
        holder.setData(data);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView userNameTV;
        TextView putDateTV;
        TextView articleStyle;
        TextView articleTitleTV;
        TextView articleContentTV;
        @BindView(R.id.profile)
        RadiusImageView profile;
        @BindView(R.id.image1)
        RadiusImageView image1;
        View itemView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            profile = itemView.findViewById(R.id.profile);
            userNameTV = itemView.findViewById(R.id.user_name);
            putDateTV = itemView.findViewById(R.id.put_date);
            articleStyle = itemView.findViewById(R.id.articleType);
            articleTitleTV = itemView.findViewById(R.id.article_title);
            articleContentTV = itemView.findViewById(R.id.article_content);
            image1 = itemView.findViewById(R.id.image1);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FRSCFragmentManageUtil.intentToFragmentAddedToBackStack(ArticleContentSpecifiedFragment.newInstance(""));
                }
            });
        }

        public void setData(Map<Object, Object> data) {
            data.get("userProfile");
            data.get("imageView1");
            data.get("imageView2");
            userNameTV.setText(data.get("userName").toString());
            putDateTV.setText(data.get("putDate").toString());
            articleStyle.setText(data.get("articleStyle").toString());
            articleTitleTV.setText(data.get("articleTitle").toString());
            articleContentTV.setText(data.get("articleContent").toString());
        }
    }
}
