package com.ly.supermvp.model.pictures;

import android.text.TextUtils;

import com.ly.supermvp.common.BizInterface;
import com.ly.supermvp.model.OnNetRequestListener;
import com.ly.supermvp.model.entity.OpenApiPicture;
import com.ly.supermvp.model.entity.OpenApiResponse;
import com.ly.supermvp.model.entity.PictureBody;
import com.ly.supermvp.model.entity.ShowApiPictures;
import com.ly.supermvp.model.entity.ShowApiResponse;
import com.ly.supermvp.server.RetrofitService;
import com.ly.supermvp.utils.ToastUtils;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;


/**
 * <Pre>
 *     图片大全数据实现类
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/3/21 16:04
 */
public class PicturesModelImpl implements PicturesModel{
    @Override
    public void netLoadPictures(String type, int page, OnNetRequestListener<List<PictureBody>> listener) {
        Observable<ShowApiResponse<ShowApiPictures>> observable = RetrofitService.getInstance().
                createAPI().getShowApiPictures(RetrofitService.getCacheControl(), BizInterface.SHOW_API_APPID,
                BizInterface.SHOW_API_KEY, type, page);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        listener.onStart();
                    }
                })
                .subscribe(new Observer<ShowApiResponse<ShowApiPictures>>() {
                    @Override
                    public void onError(Throwable e) {
                        listener.onFailure(e);
                        listener.onFinish();
                    }

                    @Override
                    public void onComplete() {
                        listener.onFinish();
                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ShowApiResponse<ShowApiPictures> showApiPicturesShowApiResponse) {
                        if (showApiPicturesShowApiResponse.getShowapi_res_body() != null
                                && TextUtils.equals("0", showApiPicturesShowApiResponse.getShowapi_res_code())) {
                            listener.onSuccess(showApiPicturesShowApiResponse.getShowapi_res_body().getPagebean().getContentlist());
                        } else {
                            ToastUtils.showShort(showApiPicturesShowApiResponse.getShowapi_res_error());
                            listener.onFailure(new Exception());
                        }
                    }
                });
    }

    @Override
    public Observable<OpenApiResponse<List<OpenApiPicture>>> netLoadPicturesByOpenApi(int page, int count) {
        //临时切换baseurl
        RetrofitUrlManager.getInstance().putDomain(BizInterface.DOMAIN_OPEN_API, BizInterface.OPEN_API);
        Observable<OpenApiResponse<List<OpenApiPicture>>> observable = RetrofitService.getInstance().
                createAPI().getPictures(RetrofitService.getCacheControl(), page, count);

        return observable;

        /*observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(mLifecycleTransformer)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        listener.onStart();
                    }
                })
                .subscribe(new Observer<OpenApiResponse<List<OpenApiPicture>>>() {
                    @Override
                    public void onError(Throwable e) {
                        listener.onFailure(e);
                        listener.onFinish();
                    }

                    @Override
                    public void onComplete() {
                        listener.onFinish();
                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(OpenApiResponse<List<OpenApiPicture>> openApiResponse) {
                        if (OpenApiResponse.SUCCESS == openApiResponse.code) {
                            listener.onSuccess(openApiResponse.result);
                        } else {
                            listener.onFailure(new Exception());
                        }
                    }
                });*/
    }
}
