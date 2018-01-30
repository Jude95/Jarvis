package com.jude.jarvis_core.expansion.list;

import android.databinding.BindingAdapter;
import android.databinding.ViewDataBinding;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.jude.jarvis_core.R;

/**
 * Created by Jude on 2017/7/25.
 */

public class ListBinding {

    @BindingAdapter({"itemPresenter"})
    public static <D extends ViewDataBinding, M, P extends ListItemPresenter<D, M>> void itemPresenter(EasyRecyclerView easyRecyclerView, String presenterClass) {
        try {
            itemPresenter(easyRecyclerView, (Class<P>) Class.forName(presenterClass));
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("itemPresenter class " + presenterClass + "doesn't found");
        }
    }


    @BindingAdapter({"itemPresenter"})
    public static <D extends ViewDataBinding, M, P extends ListItemPresenter<D, M>> void itemPresenter(EasyRecyclerView easyRecyclerView, final Class<P> presenterClass) {
        if (easyRecyclerView.getAdapter() instanceof MultiPresenterRecyclerAdapter) {
            ((MultiPresenterRecyclerAdapter<M>) easyRecyclerView.getAdapter()).singleItemPresenter(presenterClass);
        }
    }

    @BindingAdapter(value = {"dividerColor", "dividerHeight"}, requireAll = false)
    public static void divider(EasyRecyclerView easyRecyclerView, int color, float height) {
        if (color == 0) {
            color = easyRecyclerView.getContext().getResources().getColor(R.color.divider_default);
        }
        if (height == 0) {
            height = easyRecyclerView.getContext().getResources().getDimension(R.dimen.divider_default);
        }
        easyRecyclerView.addItemDecoration(new DividerDecoration(color, (int) height));
    }
}
