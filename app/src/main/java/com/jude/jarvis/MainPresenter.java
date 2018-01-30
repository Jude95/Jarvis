package com.jude.jarvis;

import android.content.Intent;
import android.view.View;

import com.jude.jarvis.databinding.ActivityMainBinding;
import com.jude.jarvis.list.SimpleListActivity;
import com.jude.jarvis.multi_list.MultiListActivity;
import com.jude.jarvis_core.framework.z_base.JarvisPresenter;

/**
 * Created by Jude on 2018/1/30.
 */

public class MainPresenter extends JarvisPresenter<ActivityMainBinding> {

    public void listPage(View view){
        getContext().startActivity(new Intent(getContext(),SimpleListActivity.class));
    }

    public void multiListPage(View view){
        getContext().startActivity(new Intent(getContext(),MultiListActivity.class));
    }
}

