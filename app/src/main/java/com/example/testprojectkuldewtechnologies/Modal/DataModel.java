package com.example.testprojectkuldewtechnologies.Modal;

import java.util.List;

public class DataModel {

    private List<String> nestedList;
    private String itemText;
    private boolean isExpandable;
    boolean isChecked;

    public DataModel(List<String> itemList, String itemText) {
        this.nestedList = itemList;
        this.itemText = itemText;
        isExpandable = false;
        isChecked = false;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }

    public List<String> getNestedList() {
        return nestedList;
    }

    public String getItemText() {
        return itemText;
    }

    public boolean isExpandable() {
        return isExpandable;
    }
    

    public void setNestedItemChecked(int nestedIndex, boolean isChecked) {
    }

    public boolean isNestedItemChecked(int nestedIndex) {
        return false;
    }
}
