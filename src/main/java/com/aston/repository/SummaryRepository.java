package com.aston.repository;

import com.aston.entity.Summary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author
 * SummaryRepository stores Summary objects from DB for usage in application services
 */
public class SummaryRepository {
    private List<Summary> summaries;
    Connection c;
    Statement stmt;
    final String DRIVER = "org.postgresql.Driver";
    final String URL = "jdbc:postgresql://localhost:5432/postgres";
    final String USER = "postgres";
    final String PASSWORD = "pepper";
    final String SELECT_QUERY = "SELECT id, summary, postid, active FROM crud.summaries;";

    public SummaryRepository(List<Summary> summaries) {
        this.summaries = summaries;
    }

    public SummaryRepository() {
        summaries = Collections.synchronizedList(new ArrayList());
        try {
            Class.forName(DRIVER);
            c = DriverManager
                    .getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String qr = SELECT_QUERY;
            ResultSet rs = stmt.executeQuery(qr);
            while (rs.next()) {
                summaries.add(new Summary(rs.getLong("id"), rs.getString("summary"), rs.getLong("postid"), rs.getBoolean("active")));
            }
            rs.close();
            stmt.close();
            c.commit();
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Summary> getSummaries() {
        List<Summary> activeSummaries = Collections.synchronizedList(new ArrayList());
        for (Summary s : summaries) {
            if (s.getActive() == true) activeSummaries.add(s);
        }
        return activeSummaries;
    }

}
