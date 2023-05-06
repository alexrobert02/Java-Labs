drop table album_genre;
drop table albums;
drop table genres;
drop table artists;
/

drop sequence genre_seq;
drop sequence artist_seq;
drop sequence album_seq;
drop sequence album_genre_seq;
/

CREATE TABLE genres (
  id int primary key,
  name varchar2(100)
);
/

CREATE TABLE artists (
  id int primary key,
  name varchar2(100)
);
/

CREATE TABLE albums (
  id int not null primary key,
  release_year int,
  title varchar(100),
  artist_id int,
  genre_id int,
  CONSTRAINT FK_album_artist FOREIGN KEY (artist_id)
    REFERENCES artists(id),
  CONSTRAINT FK_album_genre FOREIGN KEY (genre_id)
    REFERENCES genres(id)
);
/

CREATE TABLE album_genre (
  album_id int,
  genre_id int,
  CONSTRAINT FK_album_genre_album FOREIGN KEY (album_id)
    REFERENCES albums(id),
  CONSTRAINT FK_album_genre_genre FOREIGN KEY (genre_id)
    REFERENCES genres(id)
);
/

CREATE SEQUENCE artist_seq START WITH 1;
CREATE SEQUENCE genre_seq START WITH 1;
CREATE SEQUENCE album_seq START WITH 1;
CREATE SEQUENCE album_genre_seq START WITH 1;
/

CREATE OR REPLACE TRIGGER artist_bir 
BEFORE INSERT ON artists 
FOR EACH ROW
BEGIN
  SELECT artist_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;
/

CREATE OR REPLACE TRIGGER genre_bir 
BEFORE INSERT ON genres
FOR EACH ROW
BEGIN
  SELECT genre_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;
/

CREATE OR REPLACE TRIGGER album_bir 
BEFORE INSERT ON albums 
FOR EACH ROW
BEGIN
  SELECT album_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;
/