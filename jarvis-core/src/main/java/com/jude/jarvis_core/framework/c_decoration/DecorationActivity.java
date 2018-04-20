package com.jude.jarvis_core.framework.c_decoration;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.jude.jarvis_core.JarvisSetting;
import com.jude.jarvis_core.framework.b_inject.InjectActivity;

/**
 * Created by zhuchenxi on 2017/1/16.
 * Activity视图拓展层
 */
public class DecorationActivity<P extends DecorationPresenter> extends InjectActivity<P>{
    /**
     *      视图结构
     *                    DecorView
     *                        |
     *                    LinearLayout
     *                    /         \
     *                   /           \
     *           FrameLayout       FrameLayout
     *            |             (mContentParent)
     *            |                   /      \
     *         TextView     FrameLayout    各种附加View
     *                      (mContent)
     *                  (在一开始就装入View树)
     *                          |
     *                      setContent()
     *                  (什么时候装入View不确定)
     *
     */
    FrameLayout mContent;
    FrameLayout mContentParent;
    WindowAction mWindowAction;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewTree();
        mWindowAction = JarvisSetting.sViewDecoratorFactory.create(this);
        mWindowAction.container = mContentParent;
        getPresenter().mWindowAction = mWindowAction;
    }


    private void initViewTree(){
        mContentParent = (FrameLayout) findViewById(android.R.id.content);
        mContent = new FrameLayout(this);
        super.setContentView(mContent);
    }

    @Override
    public void setContentView(int layoutResID) {
        this.setContentView(getLayoutInflater().inflate(layoutResID, mContent, false));
    }

    @Override
    public void setContentView(View view) {
        this.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        mContent.addView(view, params);
    }

    /**
     * 获取ContentView，比如你想将View A加进Activity View树，这个View就是View A的父View。
     * @return
     */
    public ViewGroup getContentView(){
        return mContent;
    }

    /**
     * 这个需求应该没有例外吧?
     * 有，DrawerLayout的指示器
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
