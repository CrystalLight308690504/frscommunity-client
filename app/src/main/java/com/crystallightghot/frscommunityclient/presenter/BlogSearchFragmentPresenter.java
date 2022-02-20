package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.view.fragment.BlogSearchFragment;
import com.crystallightghot.frscommunityclient.view.pojo.blog.SearchHistory;
import com.crystallightghot.frscommunityclient.view.pojo.blog.SearchHistoryDao;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.FRSCDataBaseUtil;
import com.crystallightghot.frscommunityclient.view.util.FRSCThreadPoolUtil;

import java.util.Date;
import java.util.List;

/**
 * @Date 2022/2/20
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class BlogSearchFragmentPresenter {
    BlogSearchFragment view;

    public BlogSearchFragmentPresenter(BlogSearchFragment view) {
        this.view = view;
    }

    public void saveSearchHistory(String searchText) {
        SearchHistory searchHistory = new SearchHistory();
        searchHistory.setHistoryType(0);
        searchHistory.setSearchText(searchText);
        searchHistory.setCreatedTime(new Date(System.currentTimeMillis()));
        searchHistory.setUserId(FRSCApplicationContext.getUser().getUserId());
        view.getSearchHistoryRecycleViewAdapter().getSearchHistories().add(searchHistory);
        view.searchHistoryNotify();
        // 异步存储本例历史记录改变
        FRSCThreadPoolUtil.executeThread(() -> {
            SearchHistoryDao blogSearchHistoryDao = FRSCDataBaseUtil.getWriteDaoSession().getSearchHistoryDao();
            blogSearchHistoryDao.insert(searchHistory);
        });
    }

    public void loadSearchHistory() {
        SearchHistoryDao searchHistoryDao = FRSCDataBaseUtil.getWriteDaoSession().getSearchHistoryDao();
        List<SearchHistory> searchHistories = searchHistoryDao.queryBuilder()
                .where(SearchHistoryDao.Properties.HistoryType.eq(0)
                        , SearchHistoryDao.Properties.UserId.eq(FRSCApplicationContext.getUser().getUserId()))
                .build().list();
        view.loadingSearchHistory(searchHistories);
    }
}
