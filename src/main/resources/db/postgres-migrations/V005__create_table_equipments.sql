CREATE SEQUENCE equipments_seq;

CREATE TABLE equipments (
  id smallint check (id > 0) NOT NULL DEFAULT NEXTVAL ('equipments_seq'),
  company_id smallint check (company_id > 0) DEFAULT NULL,
  sector_id smallint check (sector_id > 0) DEFAULT NULL,
  type_id smallint check (type_id > 0) DEFAULT NULL,
  user_id smallint check (user_id > 0) DEFAULT NULL,
  acquisition_value decimal(9,2) DEFAULT NULL,
  description varchar(120) DEFAULT NULL,
  acquisition_date date DEFAULT NULL,
  nfe_path varchar(120) DEFAULT NULL,
  status enum(ACTIVE,RESERVE,INACTIVE) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY company_id (company_id),
  KEY sector_id (sector_id),
  KEY type_id (type_id),
  KEY user_id (user_id),
  CONSTRAINT equipments_ibfk_1 FOREIGN KEY (type_id) REFERENCES types (id) ON DELETE CASCADE,
  CONSTRAINT equipments_ibfk_2 FOREIGN KEY (company_id) REFERENCES companies (id) ON DELETE CASCADE,
  CONSTRAINT equipments_ibfk_3 FOREIGN KEY (sector_id) REFERENCES sectors (id) ON DELETE CASCADE,
  CONSTRAINT equipments_ibfk_4 FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
) CHARSET=utf8 COLLATE=utf8_unicode_ci;