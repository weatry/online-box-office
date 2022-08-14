DROP TABLE IF EXISTS obo_order_item;
DROP TABLE IF EXISTS obo_order;
DROP TABLE IF EXISTS undo_log;

CREATE TABLE obo_order (
  id varchar(255) not null,
  phone varchar(255),
  total_price bigint,
  create_time datetime(6),
  payment_id varchar(255),
  pay_time datetime(6),
  primary key (id)
) engine=InnoDB;

CREATE TABLE obo_order_item (
  id varchar(255) not null,
  price integer,
  ticket_id varchar(255),
  seat_row integer,
  seat_col integer,
  seat_floor integer,
  order_id varchar(255),
  primary key (id)
);

ALTER TABLE obo_order_item ADD CONSTRAINT FK_order_id FOREIGN KEY (order_id) REFERENCES obo_order (id);

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
