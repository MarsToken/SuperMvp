package com.ly.supermvp.model.pictures;

import com.ly.supermvp.common.BizInterface;
import com.ly.supermvp.model.entity.OpenApiPicture;
import com.ly.supermvp.model.entity.OpenApiResponse;
import com.ly.supermvp.server.RetrofitService;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
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
