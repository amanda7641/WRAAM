package org.launchcode.maximo.models;

public enum WorkRequestFieldType {

    ALL ("All"),
    DESCRIPTION ("Description"),
    BUILDING ("Building"),
    DATEREQUESTED ("Date Requested");


    private final String name;

    WorkRequestFieldType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
