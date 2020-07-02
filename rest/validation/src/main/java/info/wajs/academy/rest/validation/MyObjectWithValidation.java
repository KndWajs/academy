package info.wajs.academy.rest.validation;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MyObjectWithValidation {
    private String name;

    @NotNull
    @Min(2)
    private int value;

    public MyObjectWithValidation(String name, int value) {
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
