package com.aston.controller;

import com.aston.dto.PostDto;
import com.aston.service.PostService;
import com.google.gson.Gson;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;

/**
 * @author
 * Controller layer for getting data from request and creating response
 */

public class PostController {
  public static final String APPLICATION_JSON = "application/json";
  private final PostService service;

  public PostController(PostService service) {
    this.service = service;
  }

  public void all(HttpServletResponse response) throws IOException {
    final var data = service.all();
    if(data != null) {
      response.setContentType(APPLICATION_JSON);
      final var gson = new Gson();
      response.getWriter().print(gson.toJson(data));
    }else{
      response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
  }

  public void getById(long id, HttpServletResponse response) throws IOException {
    final var data = service.getById(id);
    if(data != null) {
      response.setContentType(APPLICATION_JSON);
      final var gson = new Gson();
      response.getWriter().print(gson.toJson(data));
    }else {
      response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

  }

  public void save(Reader body, HttpServletResponse response) throws IOException {
    response.setContentType(APPLICATION_JSON);
    final var gson = new Gson();
    final var postDto = gson.fromJson(body, PostDto.class);
    final var data = service.save(postDto);
    if(data != null) {
      response.getWriter().print(gson.toJson(data));
    }else {
      response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
  }

  public void removeById(long id, HttpServletResponse response) throws IOException {
    if(service.removeById(id)) {
      response.setStatus(HttpServletResponse.SC_OK);
    } else response.setStatus(HttpServletResponse.SC_NO_CONTENT);
  }

  public void put(Reader body, HttpServletResponse response) throws IOException {
    final var gson = new Gson();
    final var postDto = gson.fromJson(body, PostDto.class);
    if(service.put(postDto)) {
      response.setStatus(HttpServletResponse.SC_OK);
    } else response.setStatus(HttpServletResponse.SC_NO_CONTENT);
  }

}
