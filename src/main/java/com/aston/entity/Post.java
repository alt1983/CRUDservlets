package com.aston.entity;

/**
 * @author
 * Post object creates from row of database table
 */
public class Post {
  private long id;
  private String content;
  private long author;
  private boolean active;

  @Override
  public String toString() {
    return "Post{" +
            "id=" + id +
            ", content='" + content + '\'' +
            ", author=" + author +
            ", active=" + active +
            '}';
  }
  public Post() {
  }
  public Post(long id, long author, String content, boolean active) {
    this.id = id;
    this.content = content;
    this.author = author;
    this.active = active;
  }
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public boolean getActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public long getAuthor() {
    return author;
  }

  public void setAuthor(long authorId) {
    this.author = authorId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
