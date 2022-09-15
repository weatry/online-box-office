DROP TABLE if EXISTS obo_global_id;

CREATE TABLE `obo_global_id` (
  `id` int NOT NULL,
  `bucket` varchar(255),
  `value` bigint,
  `step` int,
  `upper_limit` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;