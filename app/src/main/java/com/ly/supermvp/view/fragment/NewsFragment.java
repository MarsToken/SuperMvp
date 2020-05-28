package com.ly.supermvp.view.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.ly.supermvp.R;
import com.ly.supermvp.adapter.NewsListAdapter;
import com.ly.supermvp.delegate.NewsFragmentDelegate;
import com.ly.supermvp.delegate.SwipeRefreshAndLoadMoreCallBack;
import com.ly.supermvp.model.entity.NewsBody;
import com.ly.supermvp.model.entity.ShowApiNews;
import com.ly.supermvp.model.entity.ShowApiResponse;
import com.ly.supermvp.model.news.NewsModel;
import com.ly.supermvp.model.news.NewsModelImpl;
import com.ly.supermvp.mvp_frame.presenter.FragmentPresenter;
import com.ly.supermvp.utils.ToastUtils;
import com.ly.supermvp.view.activity.NewsDetailActivity;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * <Pre>
 * 新闻fragment
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/1/27 11:04
 * @see https://github.com/liuyanggithub/SuperMvp
 */
public class NewsFragment extends FragmentPresenter<NewsFragmentDelegate> implements SwipeRefreshAndLoadMoreCallBack {
    private NewsModel mNewsModel;
    private int mPageNum = 1;
    private NewsListAdapter mAdapter;

    //新闻数据列表
    private List<NewsBody> mNews = new ArrayList<>();

//    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }

    @Override
    protected Class<NewsFragmentDelegate> getDelegateClass() {
        return NewsFragmentDelegate.class;
    }


    @Override
    protected void initData() {
        super.initData();
        mNewsModel = new NewsModelImpl();

        mAdapter = new NewsListAdapter(getActivity(), mNews);
        viewDelegate.setListAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new NewsListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                NewsBody item = mNews.get(position);
                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                if((item.getImageurls() != null && item.getImageurls().size() > 0)) {
                    intent.putExtra(NewsDetailActivity.ARG_NEWS_PIC, item.getImageurls().get(0).getUrl());
                }
                intent.putExtra(NewsDetailActivity.ARG_NEWS_URL, item.getLink());
                intent.putExtra(NewsDetailActivity.ARG_NEWS_TITLE, item.getTitle());
                startActivity(intent);
            }
        });

        //注册下拉刷新
        viewDelegate.registerSwipeRefreshCallBack(this);
        //注册加载更多
        viewDelegate.registerLoadMoreCallBack(this, mAdapter);

        netNewsList(true);
    }

    /**
     * 从网络加载数据列表
     * @param isRefresh 是否刷新
     */
    private void netNewsList(final boolean isRefresh) {
//        viewDelegate.showLoading();
        if(isRefresh){
            mPageNum = 1;
        }else {
            mPageNum++;
        }
        mNewsModel.netLoadNewsList(mPageNum, NewsModelImpl.CHANNEL_ID, NewsModelImpl.CHANNEL_NAME)
                .enqueue(new Callback<ShowApiResponse<ShowApiNews>>() {
            @Override
            public void onResponse(Call<ShowApiResponse<ShowApiNews>> call, Response<ShowApiResponse<ShowApiNews>> response) {
                Logger.d(response.message() + response.code() + response.body().getShowapi_res_code()
                        + response.body().getShowapi_res_error());

                if (response.body() != null && TextUtils.equals("0", response.body().getShowapi_res_code())) {
                    List<NewsBody> list = response.body().getShowapi_res_body().getPagebean().getContentlist();
                    viewDelegate.showContent();
                    if(isRefresh) {
                        if(!mNews.isEmpty()){
                            mNews.clear();
                        }
                    }
                    mNews.addAll(list);
                    mAdapter.notifyDataSetChanged();
                } else {
                    failure(response.body());
                }
            }

            @Override
            public void onFailure(Call<ShowApiResponse<ShowApiNews>> call, Throwable t) {
                failure(null);
            }
        });
    }

    private void failure(ShowApiResponse<ShowApiNews> response) {
        if (response != null) {
            ToastUtils.showShort(response.getShowapi_res_error());
        }
        viewDelegate.showError(R.string.load_error, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                netNewsList(true);
            }
        });
    }

    @Override
    protected void initView() {
        super.initView();
    }

    /**
     * 下拉刷新
     */
    @Override
    public void refresh() {
        netNewsList(true);
    }

    /**
     * 加载更多
     */
    @Override
    public void loadMore() {
        netNewsList(false);
    }
}
