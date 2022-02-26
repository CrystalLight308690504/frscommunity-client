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
import com.crystallightghot.frscommunityclient.presenter.ArticlesAdapterPresenter;
import com.crystallightghot.frscommunityclient.view.fragment.ArticleContentSpecifiedFragment;
import com.crystallightghot.frscommunityclient.view.pojo.blog.Blog;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.FRSCFragmentUtil;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.dialog.LoadingDialog;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.dialog.materialdialog.simplelist.MaterialSimpleListAdapter;
import com.xuexiang.xui.widget.dialog.materialdialog.simplelist.MaterialSimpleListItem;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2022/1/23
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {
    private final LoadingDialog loadingDialog;
    ArrayList<Blog> blogs;
    ArticlesAdapterPresenter presenter;


    public ArticlesAdapter(ArrayList<Blog> blogs) {
        this.blogs = blogs;
        presenter = new ArticlesAdapterPresenter(this);
        loadingDialog = WidgetUtils.getLoadingDialog(FRSCApplicationContext.getActivity())
                .setIconScale(0.4F)
                .setLoadingSpeed(8);
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(FRSCApplicationContext.getActivity()).inflate(R.layout.recycle_item_articles, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.initView(blogs.get(position));
    }


    @Override
    public int getItemCount() {
        return blogs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

        @BindView(R.id.contentTitle)
        TextView contentTitle;
        @BindView(R.id.timeCreated)
        TextView timeCreated;
        @BindView(R.id.article_content)
        TextView articleContent;
        public View itemView;
        @Getter
        Blog blog;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            ButterKnife.bind(this, itemView);
        }

        public void initView(Blog blog) {
            this.blog = blog;
            contentTitle.setText(blog.getBlogTitle());
            articleContent.setText(blog.getContent());
            timeCreated.setText(blog.getCreatedTime().toString());
            itemView.setOnClickListener(view -> {
                FRSCFragmentUtil.intentToFragmentAddedToBackStack(ArticleContentSpecifiedFragment.newInstance(blog));
            });
            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View view) {
            List<MaterialSimpleListItem> list = new ArrayList<>();
            list.add(new MaterialSimpleListItem.Builder(FRSCApplicationContext.getActivity())
                    .content("删除")
                    .icon(R.mipmap.icon_delete)
                    .build());
            final MaterialSimpleListAdapter adapter = new MaterialSimpleListAdapter(list)
                    .setOnItemClickListener((dialog, index, item) -> presenter.deleteBlog(this.blog));
            new MaterialDialog.Builder(FRSCApplicationContext.getActivity()).adapter(adapter, null).show();
            return false;
        }
    }

    public void showLoadingDialog() {
        loadingDialog.show();
    }

    public void hideLoadingDialog() {
        loadingDialog.dismiss();
    }
}