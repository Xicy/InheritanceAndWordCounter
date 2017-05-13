package com.xicy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Umut Akkaya on 6.05.2017.
 */
public class PetCollection {
    Map<String, Pet> set;

    public PetCollection() {
        set = new HashMap<>();
    }

    public void add(Pet pet) {
        set.putIfAbsent(pet.getName(), pet);
    }

    public void remove(String name) {
        set.remove(name);
    }

    public Map<String, Pet> listAll() {
        return set;
    }

    private Map<String, Pet> list(Class c) {
        Map<String, Pet> ret = new HashMap<>();
        for (Map.Entry<String, Pet> entry : set.entrySet())
            if (entry.getValue().getClass() == c)
                ret.put(entry.getKey(), entry.getValue());
        return ret;
    }

    public Map<String, Pet> listDogs() {
        return list(Dog.class);
    }

    public Map<String, Pet> listCats() {
        return list(Cat.class);
    }

    public String toString() {
        return "PetCollection{" +
                "set=" + set +
                '}';
    }
}
