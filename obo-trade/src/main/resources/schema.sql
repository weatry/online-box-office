DROP TABLE IF EXISTS obo_order_item;
DROP TABLE IF EXISTS obo_order;
DROP TABLE IF EXISTS undo_log;

CREATE TABLE IF not EXISTS `obo_order` (
  id varchar(255) not null,
  cinema_id integer,
  phone varchar(20),
  total_price bigint,
  payment_id varchar(255),
  pay_time datetime,
  status integer,
  finished_time datetime,
  pickup_code varchar(16),
  pickup_time datetime,
  create_time datetime,
  primary key (id)
) engine=InnoDB;

CREATE TABLE IF not EXISTS `obo_order_item` (
  id varchar(255) not null,
  cinema_id integer,
  price integer,
  ticket_id varchar(255),
  seat_row integer,
  seat_col integer,
  seat_floor integer,
  order_id varchar(255),
  primary key (id)
);

--ALTER TABLE obo_order_item ADD CONSTRAINT FK_order_id FOREIGN KEY (order_id) REFERENCES obo_order (id);

CREATE TABLE IF not EXISTS `undo_log` (
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
