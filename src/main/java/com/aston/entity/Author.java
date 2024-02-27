package com.aston.entity;

/**
 * @author
 * Author object creates from row of database table
 */
public class Author {

    private long id;
    private String name;

    private boolean active;

    public Author() {

    }

    public Author(long id, String name, boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
