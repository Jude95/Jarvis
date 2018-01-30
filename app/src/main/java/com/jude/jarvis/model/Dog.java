package com.jude.jarvis.model;

/**
 * Created by Jude on 2018/1/29.
 */

public class Dog extends Animal{

    public Dog(String name, int image) {
        super(name, image);
    }

    @Override
    public String bark() {
        return "wang";
    }

}
