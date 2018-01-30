package com.jude.jarvis_core.expansion.list;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.jarvis_core.framework.d_bind.ViewBinder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jude on 2018/1/30.
 */

public class MultiPresenterRecyclerAdapter<M> extends RecyclerArrayAdapter<M> {

    private Map<Class<ListItemPresenter>,Integer> presenterMap = new HashMap<>();

    private ListItemPresenterSwitch<M> presenterSwitch;

    public MultiPresenterRecyclerAdapter(Context context) {
        super(context);
    }

    public void singleItemPresenter(Class<? extends ListItemPresenter> clazz){
        multiItemPresenter(new SingleListItemPresenterSwitch<M>(clazz));
    }

    public void multiItemPresenter(ListItemPresenterSwitch<M> presenterSwitch){
        this.presenterSwitch = presenterSwitch;
    }

    @Override
    public BaseViewHolder<M> OnCreateViewHolder(ViewGroup parent, int viewType) {
        for (Map.Entry<Class<ListItemPresenter>, Integer> classIntegerEntry : presenterMap.entrySet()) {
            if (classIntegerEntry.getValue() == viewType){
                return new BindViewHolder<>(ViewBinder.createView(classIntegerEntry.getKey(),parent,false));
            }
        }
        throw new IllegalArgumentException("invalid viewType for MultiPresenterRecyclerAdapter :"+viewType);
    }

    @Override
    public int getViewType(int position) {
        if (presenterSwitch == null){
            throw new IllegalStateException("you must target at least one ListItemPresenter class by getAdapter().singleItemPresenter() or getAdapter().multiItemPresenter()");
        }
        Class clazz = presenterSwitch.getItemPresenterClass(position,getItem(position));
        if (presenterMap.containsKey(clazz)){
            return presenterMap.get(clazz);
        }else {
            int index = presenterMap.size();
            presenterMap.put(clazz,index);
            return index;
        }
    }

}
