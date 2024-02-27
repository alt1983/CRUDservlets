package com.aston.dto;

public class SummaryDto {
    private Long id;
    private Long postid;
    private String summary;
    public SummaryDto() {
    }
    public SummaryDto(Long id, Long postid, String summary) {
        this.id = id;
        this.postid = postid;
        this.summary = summary;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getPostid() {
        return postid;
    }
    public void setPostid(Long postid) {
        this.postid = postid;
    }
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
}
