package com.aston.service;

import com.aston.dto.Dto;
import com.aston.dto.PostDto;

import java.util.List;

/**
 * @author
 * Service layer provides services for Controller
 */

public class PostService {
  private final Dto dto;

  public PostService(Dto dto) {
    this.dto = dto;
  }

  public List<PostDto> all() {
    return dto.getPostsDto();
  }

  public PostDto getById(long id) {
    return dto.getById(id);
  }

  public PostDto save(PostDto postDto) {
    return dto.save(postDto);
  }

  public boolean put(PostDto postDto) {
    return dto.put(postDto);
  }

  public boolean removeById(long id) {
    return dto.removeById(id);
  }
}

