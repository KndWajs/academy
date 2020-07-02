package info.wajs.academy.rest.test;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MyObject {
    private String name;

    private int value;

    public MyObject(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "name= " + name + ", value= " + value ;
    }
}
