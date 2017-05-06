package com.xicy;

/**
 * Created by Umut Akkaya on 6.05.2017.
 */
public class Cat extends Pet {
    private String coatColor;

    public Cat(String name, String coatColor) {
        super(name);
        this.coatColor = coatColor;
    }

    public String getCoatColor() {
        return coatColor;
    }

    public void setCoatColor(String coatColor) {
        this.coatColor = coatColor;
    }

    public String toString() {
        return "Cat{" +
                "name='" + getName() + "', " +
                "coatColor='" + coatColor + '\'' +
                '}';
    }
}
