package org.launchcode.maximo.models;

public enum StatusType {

    INPRG ("In Progress"),
    COMP ("Complete"),
    CANCEL ("Cancel");


    private final String status;

    StatusType(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
