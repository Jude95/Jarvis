package com.jude.jarvis.model;

/**
 * Created by Jude on 2018/1/30.
 */

public abstract class Animal {
    String name;
    int image;

    public Animal(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public abstract String bark();
}
