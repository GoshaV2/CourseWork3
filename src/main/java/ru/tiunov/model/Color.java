package ru.tiunov.model;

public enum Color {
    Black("Чёрный"), Red("Красный"), Yellow("Жёлтый"), Orange("Оранжевый");
    private final String name;

    Color(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
