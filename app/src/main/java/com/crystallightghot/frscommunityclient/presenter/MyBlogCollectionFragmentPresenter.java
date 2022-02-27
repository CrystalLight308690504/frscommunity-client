package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.model.BlogModel;
import com.crystallightghot.frscommunityclient.view.fragment.MyBlogCollectionFragment;
import com.crystallightghot.frscommunityclient.view.message.RequestMessage;
import com.crystallightghot.frscommunityclient.view.pojo.blog.BlogCollection;
import com.crystallightghot.frscommunityclient.view.pojo.system.UserFollowerEntity;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.FRSCEventBusUtil;
import com.crystallightghot.frscommunityclient.view.util.FRSCObjectTransferUtil;
import com.crystallightghot.frscommunityclient.view.util.XToastUtils;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.Map;

/**
 * @Date 2022/2/27
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class MyBlogCollectionFragmentPresenter {

    private MyBlogCollectionFragment view;
    BlogModel blogModel;

    public MyBlogCollectionFragmentPresenter(MyBlogCollectionFragment view) {
        blogModel = new BlogModel();
        this.view = view;
        FRSCEventBusUtil.register(this);
    }

    public void loadBlogCollections(int pagerIndex) {
        Long userId = FRSCApplicationContext.getUser().getUserId();
        blogModel.loadBlogCollections(userId,pagerIndex, RespondMessageKey.LOAD_BLOG_COLLECTIONS);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(RequestMessage message) {
        if (!(message.getMessageKey() instanceof RespondMessageKey)) {
            return;
        }
        switch ((RespondMessageKey)message.getMessageKey()){
            case LOAD_BLOG_COLLECTIONS:
                if (message.isSuccess()) {
                    Map mapResult = (Map) message.getData();
                    boolean hasNext = (boolean) mapResult.get("hasNext");
                    List data = (List) mapResult.get("data");
                    List<BlogCollection> blogCollections = FRSCObjectTransferUtil.listMapToListObject(data, BlogCollection.class);
                    view.loadMoreData(blogCollections, hasNext);
                }else {
                    XToastUtils.error(message.getMessage());
                }
                break;

        }
    }
    public enum RespondMessageKey {
        LOAD_BLOG_COLLECTIONS,

    }
}
