package com.aston.dto;

public class PostDto {
    private Long id;
    private Long authorid;
    private String content;

    public PostDto() {
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "id=" + id +
                ", authorid=" + authorid +
                ", content='" + content + '\'' +
                '}';
    }

    public PostDto(Long id, Long authorid, String content) {
        this.id = id;
        this.authorid = authorid;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthorid() {
        return authorid;
    }

    public void setAuthorid(Long authorid) {
        this.authorid = authorid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
