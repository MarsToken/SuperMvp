package com.ly.supermvp.view.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.ly.supermvp.R;
import com.ly.supermvp.adapter.SectionsPagerAdapter;
import com.ly.supermvp.delegate.MainActivityDelegate;
import com.ly.supermvp.mvp_frame.presenter.ActivityPresenter;
import com.ly.supermvp.utils.InputUtil;
import com.ly.supermvp.utils.ToastUtils;

import androidx.appcompat.widget.Toolbar;

/**
 * <Pre>
 *      主Activity
 * </Pre>
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/1/27 10:47
 * @see https://github.com/liuyanggithub/SuperMvp
 */
public class MainActivity extends ActivityPresenter<MainActivityDelegate> implements View.OnClickListener {
    private long mExitTime = 0;

    @Override
    protected Class<MainActivityDelegate> getDelegateClass() {
        return MainActivityDelegate.class;
    }

    private SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarFullTransparent();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        InputUtil.fixInputMethodManagerLeak(this);
    }

    @Override
    protected void initData() {
        super.initData();
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), this);
    }

    @Override
    protected void initView() {
        super.initView();

        setSwipeBackEnable(false);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewDelegate.setViewPagerAdapter(mSectionsPagerAdapter);
        viewDelegate.setupWithViewPager();
        viewDelegate.setOnClickListener(this, R.id.fab);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            ToastUtils.showShort("如果觉得有帮助，记得star支持哦~");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - mExitTime < 2000) {
            super.onBackPressed();
        }else {
            ToastUtils.showShort("再次按下退出");
            mExitTime = System.currentTimeMillis();
        }

    }
}
