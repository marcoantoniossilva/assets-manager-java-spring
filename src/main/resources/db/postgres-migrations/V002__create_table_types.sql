CREATE SEQUENCE types_seq;

CREATE TABLE types (
  id smallint check (id > 0) NOT NULL DEFAULT NEXTVAL ('types_seq'),
  description varchar(120) DEFAULT NULL,
  depreciation smallint check (depreciation > 0) DEFAULT NULL,
  depreciation_term smallint check (depreciation_term > 0) DEFAULT NULL,
  PRIMARY KEY (id)
)  ;