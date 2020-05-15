package model;

import java.io.Serializable;

public class ToDo implements Serializable {

    private static long idcount = 0;

    private long id = ++idcount;

    private String name;

    private String description;

    private boolean checked;

    public ToDo() {

    }

    public ToDo(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
