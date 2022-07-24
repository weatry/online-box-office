DROP TABLE IF EXISTS obo_cinema CASCADE;
DROP TABLE IF EXISTS obo_hall CASCADE;
DROP TABLE IF EXISTS OBO_SEAT CASCADE;

CREATE TABLE obo_cinema (
  id integer generated by default as identity,
  city varchar(255),
  province varchar(255),
  street varchar(255),
  latitude double,
  longitude double,
  name varchar(255),
  telephone varchar(255),
  primary key (id)
);

CREATE TABLE obo_hall (
  id integer generated by default as identity,
  name varchar(255),
  status varchar(255),
  type varchar(255),
  cinema_id integer,
  primary key (id)
);

CREATE TABLE obo_seat (
  id integer generated by default as identity,
  available boolean,
  hall_floor integer,
  seat_col integer,
  seat_row integer,
  coordinatex integer,
  coordinatey integer,
  type varchar(255),
  cinema_id integer,
  hall_id integer,
  primary key (id)
);

ALTER TABLE obo_hall ADD CONSTRAINT FK_hall_cinema FOREIGN KEY (cinema_id) REFERENCES obo_cinema;
ALTER TABLE obo_seat ADD CONSTRAINT FK_seat_cinema FOREIGN KEY (cinema_id) REFERENCES obo_cinema;
ALTER TABLE obo_seat ADD CONSTRAINT FK_seat_hall   FOREIGN KEY (hall_id)   REFERENCES obo_hall;