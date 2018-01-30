package com.jude.jarvis_core;

import com.jude.jarvis_core.expansion.list.DefaultListViewAdapter;
import com.jude.jarvis_core.expansion.list.ListViewAdapter;
import com.jude.jarvis_core.framework.c_decoration.DefaultWindowAction;
import com.jude.jarvis_core.framework.c_decoration.WindowAction;

/**
 * Created by zhuchenxi on 2017/1/16.
 */

public class JarvisSetting {
    public static WindowAction.Factory sViewDecoratorFactory;
    public static ListViewAdapter.Factory sListViewAdapterFactory;

    static {
        sViewDecoratorFactory = new DefaultWindowAction.Factory();
        sListViewAdapterFactory = new DefaultListViewAdapter.Factory();
    }

    public static void setViewDecoratorFactory(WindowAction.Factory factory){
        JarvisSetting.sViewDecoratorFactory = factory;
    }

    public static void setListViewAdapterFactory(ListViewAdapter.Factory factory){
        JarvisSetting.sListViewAdapterFactory = factory;
    }


}
