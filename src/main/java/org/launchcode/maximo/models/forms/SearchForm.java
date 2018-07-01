package org.launchcode.maximo.models.forms;

import org.launchcode.maximo.models.WorkRequestFieldType;

public class SearchForm {

    private WorkRequestFieldType[] fields = WorkRequestFieldType.values();

    private WorkRequestFieldType searchField = WorkRequestFieldType.ALL;

    private String keyword;

    public WorkRequestFieldType[] getFields() {
        return fields;
    }

    public WorkRequestFieldType getSearchField() {
        return searchField;
    }

    public void setSearchField(WorkRequestFieldType searchField) {
        this.searchField = searchField;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
