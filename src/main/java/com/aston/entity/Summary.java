package com.aston.entity;

/**
 * @author
 * Summary object creates from row of database table
 */
public class Summary {
    private long id;

    private String summary;
    private long post;
    private boolean active;

    public Summary() {
    }

    public Summary(long id, String summary, long post, boolean active) {
        this.id = id;
        this.summary = summary;
        this.post = post;
        this.active = active;
    }

    @Override
    public String toString() {
        return "Summary{" +
                "id=" + id +
                ", summary='" + summary + '\'' +
                ", post=" + post +
                ", active=" + active +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPost() {
        return post;
    }

    public void setPost(long id) {
        this.post = id;
    }


    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
