package com.ly.supermvp.view.fragment;

import android.view.View;

import com.ly.supermvp.R;
import com.ly.supermvp.adapter.PictureGridAdapter;
import com.ly.supermvp.delegate.PicturesFragmentDelegate;
import com.ly.supermvp.delegate.SwipeRefreshAndLoadMoreCallBack;
import com.ly.supermvp.model.entity.OpenApiPicture;
import com.ly.supermvp.model.entity.OpenApiResponse;
import com.ly.supermvp.model.pictures.PicturesModel;
import com.ly.supermvp.model.pictures.PicturesModelImpl;
import com.ly.supermvp.mvp_frame.presenter.FragmentPresenter;
import com.ly.supermvp.server.NetTransformer;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;


/**
 * <Pre>
 * 美图大全fragment
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/3/21 14:39
 * @see https://github.com/liuyanggithub/SuperMvp
 */
public class PicturesFragment extends FragmentPresenter<PicturesFragmentDelegate> implements SwipeRefreshAndLoadMoreCallBack, PicturesFragmentDelegate.FloatingActionButtonListener {
    private PicturesModel mPicturesModel;
    private PictureGridAdapter mPictureGridAdapter;

    private int mPageNum = 1;
    private String mPictureId = "";

    private List<OpenApiPicture> mList = new ArrayList<>();

    public static PicturesFragment newInstance() {
        PicturesFragment fragment = new PicturesFragment();
        return fragment;
    }

    @Override
    protected Class<PicturesFragmentDelegate> getDelegateClass() {
        return PicturesFragmentDelegate.class;
    }

    @Override
    protected void initData() {
        super.initData();
        mPicturesModel = new PicturesModelImpl();
        mPictureGridAdapter = new PictureGridAdapter(mList, getActivity());
        mPictureGridAdapter.setOnImageClickListener(new PictureGridAdapter.OnImageClickListener() {
            @Override
            public void onImageClick(View view, int position) {
                viewDelegate.showDialog(mList.get(position).getImg());
            }
        });

        viewDelegate.setListAdapter(mPictureGridAdapter);

        //注册下拉刷新
        viewDelegate.registerSwipeRefreshCallBack(this);
        //注册加载更多
        viewDelegate.registerLoadMoreCallBack(this, mPictureGridAdapter);

        netLoadPictures(mPictureId, true);
    }

    @Override
    protected void initView() {
        super.initView();
        viewDelegate.setFloatingActionButtonListener(this);
    }

    /**
     * 网络获取图片
     */
    private void netLoadPictures(String id, final boolean isRefresh) {
        if (isRefresh) {
            mPageNum = 1;
        } else {
            mPageNum++;
        }

        mPicturesModel.netLoadPicturesByOpenApi(mPageNum, 20).compose(
                new NetTransformer<OpenApiResponse<List<OpenApiPicture>>, List<OpenApiPicture>>(this))
                .subscribe(new Observer<List<OpenApiPicture>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        viewDelegate.showRefreshLayout();
                    }

                    @Override
                    public void onNext(List<OpenApiPicture> openApiPictures) {
                        viewDelegate.showContent();
                        if (isRefresh) {
                            if (!mList.isEmpty()) {
                                mList.clear();
                            }
                        }
                        mList.addAll(openApiPictures);
                        mPictureGridAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        viewDelegate.showError(R.string.load_error, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                netLoadPictures(mPictureId, true);
                            }
                        });
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        /*mPicturesModel.netLoadPicturesByOpenApi(mPageNum, 20, new OnNetRequestListener<List<OpenApiPicture>>() {
            @Override
            public void onStart() {
                viewDelegate.showRefreshLayout();
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(List<OpenApiPicture> data) {
                viewDelegate.showContent();
                if (isRefresh) {
                    if (!mList.isEmpty()) {
                        mList.clear();
                    }
                }
                mList.addAll(data);
                mPictureGridAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Throwable t) {
                viewDelegate.showError(R.string.load_error, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        netLoadPictures(mPictureId, true);
                    }
                });
            }
        });*/
    }

    @Override
    public void refresh() {
        netLoadPictures(mPictureId, true);
    }

    @Override
    public void loadMore() {
        netLoadPictures(mPictureId, false);
    }

    @Override
    public void onClick(String id) {
        mPictureId = id;
        refresh();
    }
}
