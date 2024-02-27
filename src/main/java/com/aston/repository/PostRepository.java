package com.aston.repository;

import com.aston.entity.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author PostRepository stores Post objects from DB for usage in application services
 */

public class PostRepository {
    private List<Post> posts;
    Connection c;
    Statement stmt;
    private final String DRIVER = "org.postgresql.Driver";
    private final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private final String USER = "postgres";
    private final String PASSWORD = "pepper";
    private final String SELECT_QUERY = "SELECT id, authorid, content, active FROM crud.posts;";
    private final String INSERT_QUERY = "INSERT INTO crud.posts (authorid,content,active) VALUES (?,?,true) RETURNING id;";

    public PostRepository(List<Post> posts) {
        this.posts = posts;
    }

    public PostRepository() {
        posts = Collections.synchronizedList(new ArrayList());
        try {
            Class.forName(DRIVER);
            c = DriverManager
                    .getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String qr = SELECT_QUERY;
            ResultSet rs = stmt.executeQuery(qr);
            while (rs.next()) {
                posts.add(new Post(rs.getLong("id"), rs.getLong("authorid"), rs.getString("content"), rs.getBoolean("active")));
            }
            rs.close();
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<Post> getPosts() {
        List<Post> activePosts = Collections.synchronizedList(new ArrayList());
        for (Post p : posts) {
            if (p.getActive() == true) activePosts.add(p);
        }

        return activePosts;
    }

    public Post getById(long id) {
        Post post = null;
        for (Post p : posts) {
            if ((p.getId() == id) && (p.getActive() == true)) post = p;
        }
        return post;
    }

    public Post save(Post post) {

        Post newPost = new Post(post.getId(), post.getAuthor(), post.getContent(), post.getActive());
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setLong(1, newPost.getAuthor());
            preparedStatement.setString(2, newPost.getContent());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                newPost.setId(rs.getLong("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        posts.add(newPost);
        return newPost;
    }

    public boolean put(Post post) {
        boolean res = false;
        for (Post p : posts) {
            if (p.getId() == post.getId()) {
                p.setContent(post.getContent());
                p.setAuthor(post.getAuthor());
                p.setActive(post.getActive());
                res = true;
            }
        }
        return res;
    }

    public boolean removeById(long id) {
        boolean res = false;
        for (Post p : posts) {
            if (p.getId() == id) {
                p.setActive(false);
                res = true;
            }
        }
        return res;
    }

}
