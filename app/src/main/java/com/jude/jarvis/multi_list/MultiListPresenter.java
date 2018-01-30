package com.jude.jarvis.multi_list;

import com.jude.jarvis.R;
import com.jude.jarvis.databinding.ActivityListMultiBinding;
import com.jude.jarvis.model.Animal;
import com.jude.jarvis.model.Cat;
import com.jude.jarvis.model.Dog;
import com.jude.jarvis.widget.CatItemPresenter;
import com.jude.jarvis.widget.DogItemPresenter;
import com.jude.jarvis_core.expansion.list.ListItemPresenter;
import com.jude.jarvis_core.expansion.list.ListItemPresenterSwitch;
import com.jude.jarvis_core.expansion.list.ListPresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

/**
 * Created by Jude on 2018/1/30.
 */

public class MultiListPresenter extends ListPresenter<ActivityListMultiBinding,Animal> {

    @Override
    public void onCreated() {
        super.onCreated();
        getAdapter().multiItemPresenter(new ListItemPresenterSwitch<Animal>() {
            @Override
            public Class<? extends ListItemPresenter> getItemPresenterClass(int position, Animal data) {
                if (data instanceof Cat){
                    return CatItemPresenter.class;
                }else {
                    return DogItemPresenter.class;
                }
            }
        });

        List<Animal> list = new ArrayList<>();
        list.add(new Dog("狗蛋", R.mipmap.dog1));
        list.add(new Cat("二妞",R.mipmap.cat1));
        list.add(new Dog("狗剩",R.mipmap.dog3));
        Single.just(list)
                .subscribe(getRefreshConsumer());


    }
}
