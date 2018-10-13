package pt.ipleiria.ppb.model;

import java.util.ArrayList;

public class Task {

 private int id;
 private int order;
 private String title;
 private String description;
 private int value;
 private ArrayList<String> ValidRule = new ArrayList<>();


    public Task(int id, int order, String title, String description, int value, ArrayList<String> validRule) {
        this.id = id;
        this.order = order;
        this.title = title;
        this.description = description;
        this.value = value;
        ValidRule = validRule;
    }

    public int getId() {
        return id;
    }

    public int getOrder() {
        return order;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getValue() {
        return value;
    }

    public ArrayList<String> getValidRule() {
        return ValidRule;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setValidRule(ArrayList<String> validRule) {
        ValidRule = validRule;
    }
}
