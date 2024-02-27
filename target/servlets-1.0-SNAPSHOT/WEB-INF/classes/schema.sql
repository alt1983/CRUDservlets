CREATE SCHEMA crud;

CREATE TABLE crud.AUTHORS(
                              id           SERIAL PRIMARY KEY,
                              name         VARCHAR(30) NOT NULL,
                              active       BOOLEAN NOT NULL DEFAULT TRUE
);


CREATE TABLE crud.POSTS(
                               id           SERIAL PRIMARY KEY,
                               authorid       INTEGER NOT NULL ,
                               content        VARCHAR(500) NOT NULL,
                               active       BOOLEAN NOT NULL DEFAULT TRUE,
                               FOREIGN KEY (authorid) REFERENCES crud.AUTHORS(id)
);

CREATE TABLE crud.SUMMARIES(
                           id           SERIAL PRIMARY KEY,
                           postid       INTEGER NOT NULL,
                           summary      VARCHAR(200) NOT NULL,
                           active       BOOLEAN NOT NULL DEFAULT TRUE,
                           FOREIGN KEY (postid) REFERENCES crud.POSTS(id)
);
