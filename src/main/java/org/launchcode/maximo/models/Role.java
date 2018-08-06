package org.launchcode.maximo.models;

public enum Role {

    ADMIN ("Administrator"),
    USER ("User");

    private final String status;

    Role(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }


}
