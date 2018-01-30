package com.jude.jarvis.list;

import com.jude.jarvis.R;
import com.jude.jarvis.databinding.ActivityListSimpleBinding;
import com.jude.jarvis.model.Dog;
import com.jude.jarvis_core.expansion.list.ListPresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

/**
 * Created by Jude on 2018/1/27.
 */

public class SimpleListPresenter extends ListPresenter<ActivityListSimpleBinding,Dog> {

    @Override
    public void onCreated() {
        super.onCreated();

        List<Dog> list = new ArrayList<>();
        list.add(new Dog("狗蛋", R.mipmap.dog1));
        list.add(new Dog("二狗子",R.mipmap.dog2));
        list.add(new Dog("狗剩",R.mipmap.dog3));
        Single.just(list)
                .subscribe(getRefreshConsumer());
    }
}
