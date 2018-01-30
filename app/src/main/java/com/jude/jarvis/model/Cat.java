package com.jude.jarvis.model;

/**
 * Created by Jude on 2018/1/30.
 */

public class Cat extends Animal {
    public Cat(String name, int image) {
        super(name, image);
    }

    @Override
    public String bark() {
        return "miao";
    }
}
