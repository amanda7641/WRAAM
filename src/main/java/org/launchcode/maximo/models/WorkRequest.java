package org.launchcode.maximo.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class WorkRequest {

    // TODO - add work order number field, dependent on id
    // and add more fields when prepared
    // should be able to make as complete - need boolean value

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=1, max=30)
    private String description;

    @ManyToOne
    private Building building;

    public WorkRequest(){

    }

    public WorkRequest(String description){
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
