package com.jude.jarvis_core.framework.b_inject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jude.jarvis_core.exception.IllegalClassParameterException;
import com.jude.jarvis_core.util.ParametrizedTypeUtil;

/**
 * Created by zhuchenxi on 2017/1/16.
 * 负责Presenter实例化及注入的一层。
 */

public class InjectActivity<P extends InjectPresenter> extends AppCompatActivity {
    P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        onCreatePresenter(presenter);
        presenter.mContext = this;
    }

    protected void onCreatePresenter(P presenter){

    }

    public <E extends P> E getPresenter() {
        return (E) presenter;
    }

    private P createPresenter(){
        Class<P> clazz = ParametrizedTypeUtil.getTypeArgumentClass(getClass(),0);
        if (clazz == null){
            throw new IllegalClassParameterException("Presenter not found in class "+getClass().getName());
        }
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        throw new IllegalAccessError("the presenter shouldn't change the constructor");
    }
}
