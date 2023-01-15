package ru.tiunov.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import ru.tiunov.exception.NotFoundSizeException;

public enum Size {
    SizeA(36.5f), SizeB(37), SizeC(37.5f), SizeD(38f), SizeE(38.5f), SizeF(39f),
    SizeT(39.5f), SizeG(40f), SizeH(40.5f), SizeS(41f), SizeV(41.5f), SizeN(42f);


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
