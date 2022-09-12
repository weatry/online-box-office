DROP TABLE IF EXISTS obo_seat;
DROP TABLE IF EXISTS obo_hall;
DROP TABLE IF EXISTS obo_cinema;

CREATE TABLE obo_cinema (
  id integer not null auto_increment,
  city varchar(255),
  province varchar(255),
  street varchar(255),
  latitude double,
  longitude double,
  name varchar(255),
  telephone varchar(255),
  primary key (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE obo_hall (
  id integer not null auto_increment,
  name varchar(255),
  status varchar(255),
  type varchar(255),
  cinema_id integer,
  primary key (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE obo_seat (
  id bigint not null,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--ALTER TABLE obo_hall ADD CONSTRAINT FK_hall_cinema FOREIGN KEY (cinema_id) REFERENCES obo_cinema (id);
--ALTER TABLE obo_seat ADD CONSTRAINT FK_seat_cinema FOREIGN KEY (cinema_id) REFERENCES obo_cinema (id);
--ALTER TABLE obo_seat ADD CONSTRAINT FK_seat_hall   FOREIGN KEY (hall_id)   REFERENCES obo_hall (id);