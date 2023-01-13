package ru.tiunov.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import ru.tiunov.exception.NotFoundSizeException;

public enum Size {
    SizeA(36.5f), SizeB(37), SizeC(37.5f);


    private final float name;

    Size(float name) {
        this.name = name;
    }

    @JsonValue
    public float getName() {
        return name;
    }


    @JsonCreator
    public static Size forValues(String size) {
        try {
            for (Size el : Size.values()) {
                if (Float.parseFloat(size)== el.name) {
                    return el;
                }
            }
        }catch (NumberFormatException e){
            throw new NotFoundSizeException();
        }
        throw new NotFoundSizeException();
    }

}
