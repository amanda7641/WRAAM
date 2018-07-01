package org.launchcode.maximo.models;

public enum WorkRequestFieldType {

    ALL ("All"),
    NUMBER ("Number"),
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
