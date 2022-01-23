package com.crystallightghot.frscommunityclient.view.adapter;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.crystallightghot.frscommunityclient.R;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView2;
import com.scwang.smartrefresh.layout.adapter.SmartRecyclerAdapter;
import com.scwang.smartrefresh.layout.adapter.SmartViewHolder;

import java.util.Collection;
import java.util.Map;

/**
 * 基于simple_list_item_2简单的适配器
 *
 * @author XUE
 * @since 2019/4/1 11:04
 */
public class SimpleRecyclerAdapter extends SmartRecyclerAdapter<String> {

    public SimpleRecyclerAdapter() {
        super(android.R.layout.simple_list_item_2);
    }

    public SimpleRecyclerAdapter(Collection<String> data) {
        super(data, android.R.layout.simple_list_item_2);
    }

    /**
     * 绑定布局控件
     *
     * @param holder
     * @param model
     * @param position
     */
    @Override
    protected void onBindViewHolder(SmartViewHolder holder, String model, int position) {

    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        QMUIRadiusImageView2 userProfile;
        TextView userNameTV;
        TextView putDateTV;
        TextView articleStyle;
        TextView articleTitleTV;
        TextView articleContentTV;
        QMUIRadiusImageView2 imageView1IV;
        View itemView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            userProfile = itemView.findViewById(R.id.profile);
            userNameTV = itemView.findViewById(R.id.user_name);
            putDateTV = itemView.findViewById(R.id.put_date);
            articleStyle = itemView.findViewById(R.id.articleType);
            articleTitleTV = itemView.findViewById(R.id.article_title);
            articleContentTV = itemView.findViewById(R.id.article_content);
            imageView1IV = itemView.findViewById(R.id.image1);

        }

        public void setData(Map<Object,Object> data) {
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
