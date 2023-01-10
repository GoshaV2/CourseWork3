package ru.tiunov.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Size {
    SizeA(36.5f), SizeB(37), SizeC(37.5f);

    private float name;

    Size(float name) {
        this.name = name;
    }

    @JsonValue
    public float getName() {
        return name;
    }

    @JsonValue
    public void setName(float name) {
        this.name = name;
    }
}
