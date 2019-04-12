package com.ly.supermvp.delegate;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ly.supermvp.R;
import com.ly.supermvp.adapter.WeatherAdapter;
import com.ly.supermvp.model.entity.ShowApiWeather;
import com.ly.supermvp.mvp_frame.view.AppDelegate;
import com.ly.supermvp.utils.GlideUtil;
import com.ly.supermvp.view.LoadingView;
import com.ly.supermvp.widget.ProgressLayout;
import com.rey.material.widget.EditText;

import butterknife.BindView;

/**
 * <Pre>
 * 天气预报界面视图代理
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/2/29 17:44
 * @see https://github.com/liuyanggithub/SuperMvp
 */
public class WeatherFragmentDelegate extends AppDelegate implements LoadingView{

    @BindView(R.id.progress_layout)
    ProgressLayout mProgressLayout;
    @BindView(R.id.et_location)
    EditText et_location;
    @BindView(R.id.rv_weather)
    RecyclerView rv_weather;

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_weather;
    }

    /**
     * 获取输入的地名
     * @return
     */
    public String getInputLocation(){
        return et_location.getText().toString();
    }

    /**
     * 初始化RecyclerView
     */
    public void initRecyclerView(WeatherAdapter adapter) {
        rv_weather.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_weather.setAdapter(adapter);
    }
    /**
     * 关闭软键盘
     */
    public void closeSoftInput(){
        InputMethodManager inputMethodManager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(et_location.getWindowToken(), 0);
    }

    @Override
    public void showLoading() {
        mProgressLayout.showLoading();
    }

    @Override
    public void showContent() {
        if (!mProgressLayout.isContent()) {
            mProgressLayout.showContent();
        }
    }

    @Override
    public void showError(int messageId, View.OnClickListener listener) {
        mProgressLayout.showError(messageId, listener);
    }
}
