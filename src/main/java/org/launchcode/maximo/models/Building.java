package org.launchcode.maximo.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

import java.util.List;

@Entity
public class Building {

    @Id
    @GeneratedValue
    private int id;


    @NotNull
    @Size(min=1, max=15)
    private String name;

    private int yearBuilt;
//    private List<Department> departments;

    @OneToMany
    @JoinColumn(name="building_id")
    private List<WorkRequest> workRequests = new ArrayList<>();

    public Building(){

    }

    public Building(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearBuilt() {
        return yearBuilt;
    }

    public void setYearBuilt(int yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    public List<WorkRequest> getWorkRequests() {
        return workRequests;
    }
}
