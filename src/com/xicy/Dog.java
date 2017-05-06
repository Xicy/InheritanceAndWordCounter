package com.xicy;

/**
 * Created by Umut Akkaya on 6.05.2017.
 */
public class Dog extends Pet {
    private int weight;

    public Dog(String name, int weight) {
        super(name);
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String toString() {
        return "Dog{" +
                "name='" + getName() + "', " +
                "weight=" + weight +
                '}';
    }
}
