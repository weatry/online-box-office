DROP TABLE IF exists obo_schedule CASCADE;
DROP TABLE IF EXISTS obo_ticket CASCADE;

CREATE TABLE obo_schedule (
  id varchar(255) not null,
  cinema_id integer,
  hall_id integer,
  movie_id varchar(255),
  start_time timestamp,
  end_time timestamp,
  status integer,
  primary key (id)
);

CREATE TABLE obo_ticket (
  id varchar(255) not null,
  schedule_id varchar(255),
  hall_floor integer,
  seat_col integer,
  seat_row integer,
  price double,
  code varchar(255),
  get_time timestamp,
  pay_time timestamp,
  primary key (id)
);

ALTER TABLE obo_ticket ADD CONSTRAINT FK_schedule_id FOREIGN KEY (schedule_id) REFERENCES obo_schedule;
