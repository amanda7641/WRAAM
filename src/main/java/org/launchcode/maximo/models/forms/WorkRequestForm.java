package org.launchcode.maximo.models.forms;

import org.launchcode.maximo.models.Building;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class WorkRequestForm {
    @NotNull
    @Size(min=1, max=75)
    private String description;

    @NotNull
    @Size(min=1, max=30)
    private String reportedBy;

    @NotNull
    @Size(min=1, max=20)
    private String contactNumber;

    @NotNull(message = "Please select a building.")
    private int buildingId;

    public WorkRequestForm(){}

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

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }
}
