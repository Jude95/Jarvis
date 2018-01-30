package com.jude.jarvis_core.framework.b_inject;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.jude.jarvis_core.util.ParametrizedTypeUtil;

/**
 * Created by zhuchenxi on 2017/1/16.
 */

public class InjectFragment<P extends InjectPresenter> extends Fragment {
    P presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter = createPresenter();
        presenter.mContext = getContext();
    }

    public <E extends P> E getPresenter() {
        return (E) presenter;
    }

    private P createPresenter(){
        Class<P> clazz = ParametrizedTypeUtil.getTypeArgumentClass(getClass(),0);
        try {
            return clazz.newInstance();
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        }catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
        throw new IllegalAccessError("the presenter shouldn't change the constructor");
    }
}
