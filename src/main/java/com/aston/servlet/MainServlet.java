package com.aston.servlet;

import com.aston.dto.Dto;
import com.aston.service.PostService;
import com.aston.controller.PostController;
import jakarta.servlet.http.*;

//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

/**
 * @author
 * Servlet layer for CRUD functions
 */

public class MainServlet extends HttpServlet {

  private Dto dto;
  private PostService postService;
  private PostController controller;

  @Override
  public void init() {

    dto = new Dto();
    postService = new PostService(dto);
    controller = new PostController(postService);
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) {
    try {
      final var path = req.getRequestURI();
      final var method = req.getMethod();
      final var url = "/api/posts";
      if (method.equals("GET") && path.equals(url)) {
        controller.all(resp);
        return;
      }
      if (method.equals("GET") && path.matches("/api/posts/\\d+")) {
        final var id = Long.parseLong(path.substring((path.lastIndexOf("/")+1)));
        controller.getById(id, resp);
        return;
      }
      if (method.equals("POST") && path.equals(url)) {
        controller.save(req.getReader(), resp);
        return;
      }
      if (method.equals("DELETE") && path.matches("/api/posts/\\d+")) {
        final var id = Long.parseLong(path.substring((path.lastIndexOf("/")+1)));
        controller.removeById(id, resp);
        return;
      }
      if (method.equals("PUT") && path.equals(url)) {
        controller.put(req.getReader(), resp);
        return;
      }
      resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    } catch (Exception e) {
      e.printStackTrace();
      resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }
}

