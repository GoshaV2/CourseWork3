package ru.tiunov.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Sock {
    private Color color;
    private Size size;
    private int cottonPart;
    private boolean hasInStock;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sock)) return false;

        Sock sock = (Sock) o;

        if (cottonPart != sock.cottonPart) return false;
        if (color != sock.color) return false;
        return size == sock.size;
    }

    @Override
    public int hashCode() {
        int result = color.hashCode();
        result = 31 * result + size.hashCode();
        result = 31 * result + cottonPart;
        return result;
    }
}
