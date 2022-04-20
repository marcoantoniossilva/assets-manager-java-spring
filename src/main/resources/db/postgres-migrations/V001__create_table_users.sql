CREATE SEQUENCE users_seq;

CREATE TABLE users (
  id smallint check (id > 0) NOT NULL DEFAULT NEXTVAL ('users_seq'),
  password varchar(60) DEFAULT NULL,
  name varchar(120) DEFAULT NULL,
  login varchar(32) DEFAULT NULL,
  email varchar(120) NOT NULL,
  last_access timestamp(0) DEFAULT NULL,
  register_in timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)   ;

ALTER SEQUENCE users_seq RESTART WITH 12;