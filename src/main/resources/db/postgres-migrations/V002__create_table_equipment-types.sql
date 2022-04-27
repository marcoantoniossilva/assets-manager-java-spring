CREATE SEQUENCE equipment_types_seq;

CREATE TABLE equipment_types (
  id smallint check (id > 0) NOT NULL DEFAULT NEXTVAL ('equipment_types_seq'),
  description varchar(120) DEFAULT NULL,
  depreciation smallint check (depreciation > 0) DEFAULT NULL,
  depreciation_term smallint check (depreciation_term > 0) DEFAULT NULL,
  PRIMARY KEY (id)
);