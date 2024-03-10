INSERT INTO crud.AUTHORS(name,active)
VALUES ('Author1',true);

INSERT INTO crud.AUTHORS(name,active)
VALUES ('Author2',true);

SELECT id, summary, postid, active FROM crud.summaries;

INSERT INTO crud.authors (name, active) VALUES ('Author3',true) RETURNING id;

INSERT INTO crud.POSTS(authorid,content,active)
VALUES (1,'Text11',true);

INSERT INTO crud.POSTS(authorid,content, active) VALUES (1,'Text12',true);

INSERT INTO crud.SUMMARIES(postid,summary, active) VALUES (1,'Summary1',true);

INSERT INTO crud.POSTS(authorid,content,active)
VALUES (2,'Text21',true);



INSERT INTO crud.SUMMARIES(postid,summary, active)
VALUES (2,'Summary2',true);

INSERT INTO crud.SUMMARIES(postid,summary, active)
VALUES (3,'Summary3',true);

SELECT * FROM crud.AUTHORS;

SELECT * FROM crud.POSTS;

SELECT * FROM crud.SUMMARIES;

drop table crud.posts;

select * from crud.authors where active = true;

SELECT * FROM crud.posts WHERE active = true;


SELECT id, authorid, content FROM crud.posts WHERE active = 'true';