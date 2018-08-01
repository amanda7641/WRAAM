package org.launchcode.maximo.models;

public enum WorkRequestFieldType {

    DESCRIPTION ("Description"),
    BUILDING ("Building");


    private final String name;

    WorkRequestFieldType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
