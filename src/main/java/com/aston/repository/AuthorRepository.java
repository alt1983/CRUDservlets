package com.aston.repository;

import com.aston.entity.Author;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author AuthorRepository stores Author objects from DB for usage in application services
 */
public class AuthorRepository {
    private List<Author> authors;
    Connection c;
    Statement stmt;
    final String DRIVER = "org.postgresql.Driver";
    final String URL = "jdbc:postgresql://localhost:5432/postgres";
    final String USER = "postgres";
    final String PASSWORD = "pepper";
    final String SELECT_QUERY = "SELECT id, name,active FROM crud.authors;";

    public AuthorRepository(List<Author> authors) {
        this.authors = authors;
    }

    public AuthorRepository() {
        authors = Collections.synchronizedList(new ArrayList());
        authors.add(new Author(1000l,"name",true));

        try {
            Class.forName(DRIVER);
            c = DriverManager
                    .getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String qr = SELECT_QUERY;
            ResultSet rs = stmt.executeQuery(qr);
            while (rs.next()) {
                authors.add(new Author(rs.getLong("id"), rs.getString("name"), rs.getBoolean("active")));
            }
            rs.close();
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<Author> getAuthors() {
        List<Author> activeAuthors = Collections.synchronizedList(new ArrayList());
        for (Author a : authors) {
            if (a.getActive() == true) activeAuthors.add(a);
        }
        return activeAuthors;
    }

}
