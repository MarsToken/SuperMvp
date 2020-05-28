package com.ly.supermvp.delegate;


import com.google.android.material.tabs.TabLayout;
import com.ly.supermvp.R;
import com.ly.supermvp.mvp_frame.view.AppDelegate;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * <Pre>
 *     主页面视图代理
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/1/27 11:10
 * @see https://github.com/liuyanggithub/SuperMvp
 */
public class MainActivityDelegate extends AppDelegate{
    @BindView(R.id.container)
    ViewPager mViewpager;

    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initWidget() {
        super.initWidget();
    }

    public void setViewPagerAdapter(FragmentPagerAdapter adapter){
        mViewpager.setOffscreenPageLimit(3);//设置viewpager预加载页面数
        mViewpager.setAdapter(adapter);
    }

    public void setupWithViewPager(){
        mTabLayout.setupWithViewPager(mViewpager);
    }
}
