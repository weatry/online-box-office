DROP TABLE IF EXISTS obo_ticket;
DROP TABLE IF EXISTS obo_schedule;
DROP TABLE IF EXISTS undo_log;

CREATE TABLE obo_schedule (
  id varchar(255) not null,
  cinema_id integer,
  hall_id integer,
  movie_id varchar(255),
  start_time datetime(6),
  end_time datetime(6),
  status integer,
  primary key (id)
);

CREATE TABLE obo_ticket (
  id varchar(255) not null,
  schedule_id varchar(255),
  movie_id varchar(255),
  cinema_id integer,
  hall_id integer,
  hall_floor integer,
  seat_col integer,
  seat_row integer,
  price double,
  status integer,
  code varchar(255),
  get_time datetime(6),
  pay_time datetime(6),
  primary key (id)
);

CREATE TABLE `undo_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) NOT NULL,
  `context` varchar(128) NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

