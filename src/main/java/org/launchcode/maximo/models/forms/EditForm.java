package org.launchcode.maximo.models.forms;

import org.launchcode.maximo.models.StatusType;
import org.launchcode.maximo.models.WorkRequest;

public class EditForm {

    private StatusType[] fields = StatusType.values();

    private StatusType statusField;

    private WorkRequest workRequest;

    public EditForm(){

    }

    public EditForm(WorkRequest workRequest){
        this.workRequest = workRequest;
        this.statusField = workRequest.getStatus();
    }

    public WorkRequest getWorkRequest() {
        return workRequest;
    }

    public void setWorkRequest(WorkRequest workRequest) {
        this.workRequest = workRequest;
    }

    public StatusType[] getFields() {
        return fields;
    }

    public StatusType getStatusField() {
        return statusField;
    }

    public void setStatusField(StatusType statusField) {
        this.statusField = statusField;
    }
}
