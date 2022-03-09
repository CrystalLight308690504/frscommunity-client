package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.model.BlogModel;
import com.crystallightghot.frscommunityclient.view.fragment.ManageBlogFragment;
import com.crystallightghot.frscommunityclient.view.message.RequestMessage;
import com.crystallightghot.frscommunityclient.view.pojo.blog.Blog;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.crystallightghot.frscommunityclient.view.util.FRSCEventBusUtil;
import com.crystallightghot.frscommunityclient.view.util.FRSCGsonUtil;
import com.crystallightghot.frscommunityclient.view.util.XToastUtils;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.Map;

/**
 * @Date 2022/3/9
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class ManageBlogFragmentPresenter {
    private ManageBlogFragment view;
    BlogModel blogModel = new BlogModel();
    public ManageBlogFragmentPresenter(ManageBlogFragment view) {
        this.view = view;
        FRSCEventBusUtil.register(this);
    }

    public void loadBlogs(int pagerIndex) {
        blogModel.loadBlogs(pagerIndex, RespondMessageKey.LOAD_BLOGS);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(RequestMessage message) {
        if (!(message.getMessageKey() instanceof RespondMessageKey)) {
            return;
        }
        switch ((RespondMessageKey) message.getMessageKey()) {
            case LOAD_BLOGS:
                if (message.isSuccess()) {
                    Map data = (Map) message.getData();
                    boolean hasNext = (boolean) data.get("hasNext");
                    List<Map> lists = (List) data.get("data");
                    List<Blog> blogs = FRSCGsonUtil.listMapToListObject(lists, Blog.class);
                    view.showMoreBlogs(blogs, hasNext);
                }else {
                    XToastUtils.error(message.getMessage());
                }
                break;
        }
    }

    enum RespondMessageKey {
        LOAD_BLOGS,
    }
}
