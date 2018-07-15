package org.launchcode.maximo.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class WorkRequest {

    // TODO - add work order number field, dependent on id
    // and add more fields when prepared
    // should be able to make as complete - need boolean value

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private Date dateRequested;

    @NotNull
    @Size(min=1, max=30)
    private String description;

    @NotNull
    @Size(min=1, max=30)
    private String reportedBy;

    @NotNull
    @Size(min=1, max=30)
    private String contactNumber;

    @ManyToOne
    private Building building;

    public WorkRequest(){
        this.dateRequested = new Date();
    }

    public WorkRequest(String description){
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public Date getDateRequested() {
        return dateRequested;
    }

    public void setDateRequested(Date dateRequested) {
        this.dateRequested = dateRequested;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
