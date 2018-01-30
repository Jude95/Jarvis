package com.jude.jarvis_core.util;

import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Jude on 2017/7/25.
 */

public class ViewBindingHelper {

    public static void bindPresenter(ViewDataBinding dataBinding,Object object){
        try {
            Method method = dataBinding.getClass().getMethod("setPresenter",object.getClass());
            method.invoke(dataBinding,object);
            return;
        } catch (NoSuchMethodException e) {
        } catch (InvocationTargetException e) {
        } catch (IllegalAccessException e) {
        }
        throw new IllegalAccessError("check the xml '"+dataBinding.getClass()+"' whether the variable name is 'presenter' ant type is '"+object.getClass()+"'");
    }

    public static <D extends ViewDataBinding> D createDataBinding(Class<D> clazz,LayoutInflater inflater, ViewGroup container, boolean attach){
        try {
            Method inflateMethod = clazz.getMethod("inflate", LayoutInflater.class, ViewGroup.class,boolean.class);
            return (D) inflateMethod.invoke(clazz,inflater,container,attach);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        throw new IllegalAccessError("wtf..what happened?");
    }

}
