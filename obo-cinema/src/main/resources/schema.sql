DROP TABLE IF EXISTS obo_cinema CASCADE;
CREATE TABLE obo_cinema (
  id bigint NOT NULL auto_increment,
  name varchar(255) DEFAULT NULL,
  province varchar(255) DEFAULT NULL,
  city varchar(255) DEFAULT NULL,
  street varchar(255) DEFAULT NULL,
  telephone varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);