CREATE SEQUENCE sectors_seq;

CREATE TABLE sectors (
  id smallint check (id > 0) NOT NULL DEFAULT NEXTVAL ('sectors_seq'),
  description varchar(120) DEFAULT NULL,
  PRIMARY KEY (id)
)  ;