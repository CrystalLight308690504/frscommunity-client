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
import com.crystallightghot.frscommunityclient.presenter.MyBlogCategoryRecycleViewAdapterPresenter;
import com.crystallightghot.frscommunityclient.view.dialog.CategoryDialogFragment;
import com.crystallightghot.frscommunityclient.view.fragment.ArticlesFragment;
import com.crystallightghot.frscommunityclient.view.pojo.blog.BlogCategory;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.FRSCFragmentUtil;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.dialog.materialdialog.simplelist.MaterialSimpleListAdapter;
import com.xuexiang.xui.widget.dialog.materialdialog.simplelist.MaterialSimpleListItem;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2022/1/23
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class MyBlogCategoryRecycleViewAdapter extends RecyclerView.Adapter<MyBlogCategoryRecycleViewAdapter.ViewHolder> {
    List<BlogCategory> blogCategories;
    MyBlogCategoryRecycleViewAdapterPresenter presenter;

    public MyBlogCategoryRecycleViewAdapter(List<BlogCategory> blogCategories) {
        this.blogCategories = blogCategories;
        presenter = new MyBlogCategoryRecycleViewAdapterPresenter(this);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(FRSCApplicationContext.getActivity()).inflate(R.layout.recycle_item_my_category, parent, false);
        ButterKnife.bind(this, view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.init(blogCategories.get(position));
    }

    @Override
    public int getItemCount() {
        return blogCategories.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        @BindView(R.id.tvCategoryName)
        TextView tvPackageName;
        @BindView(R.id.tvTotal)
        TextView tvTotal;
        @BindView(R.id.tvPrivilege)
        TextView tvPrivilege;
        @BindView(R.id.ivArrow)
        ImageView ivArrow;
        @BindView(R.id.rvLists)
        RecyclerView rvLists;
        @BindView(R.id.tvCategoryDescription)
        TextView tvCategoryDescription;

        BlogCategory blogCategory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void init(BlogCategory blogCategory) {
            this.blogCategory = blogCategory;
            tvPackageName.setText(blogCategory.getCategoryName());
            tvCategoryDescription.setText(blogCategory.getDescription());
            itemView.setOnClickListener(view -> {
                FRSCFragmentUtil.intentToFragmentAddedToBackStack(ArticlesFragment.newInstance(blogCategory.getCategoryId()));
            });
            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View view) {

            List<MaterialSimpleListItem> list = new ArrayList<>();
            list.add(new MaterialSimpleListItem.Builder(FRSCApplicationContext.getActivity())
                    .content("编辑")
                    .icon(R.mipmap.ic_edit)
                    .build());
            list.add(new MaterialSimpleListItem.Builder(FRSCApplicationContext.getActivity())
                    .content("删除")
                    .icon(R.mipmap.icon_delete)
                    .build());
            final MaterialSimpleListAdapter adapter = new MaterialSimpleListAdapter(list)
                    .setOnItemClickListener((dialog, index, item) -> {
                        switch (index) {
                            case 0 :
                                CategoryDialogFragment dialogFragment = new CategoryDialogFragment(blogCategory);
                                dialogFragment.show(FRSCApplicationContext.getBaseFragmentActivity().getSupportFragmentManager(), "s");
                                break;
                            case 1 :
                                presenter.deleteBlogCategory(blogCategory);
                                break;

                        }

                    });
            new MaterialDialog.Builder(FRSCApplicationContext.getActivity()).adapter(adapter, null).show();

            return false;
        }
    }
}
