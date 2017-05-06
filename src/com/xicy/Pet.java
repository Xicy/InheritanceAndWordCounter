package com.xicy;

/**
 * Created by Umut Akkaya on 6.05.2017.
 */
public class Pet {
    private String name;

    protected Pet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                '}';
    }
}
