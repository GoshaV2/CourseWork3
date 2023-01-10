package ru.tiunov.model;

import com.fasterxml.jackson.annotation.JsonValue;

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


    public static Size forValues(String size) {
        for (Size el : Size.values()) {
            if (
                    String.valueOf(el.getName()).equals(size)) {
                return el;
            }
        }

        return null;
    }

}
