CREATE TYPE status_enum AS ENUM ('ACTIVE','RESERVE','INACTIVE');

CREATE SEQUENCE equipments_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 56;

CREATE TABLE equipments (
  id smallint check (id > 0) NOT NULL DEFAULT NEXTVAL ('equipments_seq'),
  company_id smallint check (company_id > 0) DEFAULT NULL,
  sector_id smallint check (sector_id > 0) DEFAULT NULL,
  equipment_type_id smallint check (equipment_type_id > 0) DEFAULT NULL,
  user_id smallint check (user_id > 0) DEFAULT NULL,
  acquisition_value decimal(9,2) DEFAULT NULL,
  description varchar(120) DEFAULT NULL,
  acquisition_date date DEFAULT NULL,
  status status_enum,
  nfe_nfe_file_name VARCHAR(120),
  nfe_nfe_file_type VARCHAR(120),
  nfe_nfe_file_content bytea,
  PRIMARY KEY (id),
  CONSTRAINT equipments_ibfk_1 FOREIGN KEY (equipment_type_id) REFERENCES equipment_types (id) ON DELETE CASCADE,
  CONSTRAINT equipments_ibfk_2 FOREIGN KEY (company_id) REFERENCES companies (id) ON DELETE CASCADE,
  CONSTRAINT equipments_ibfk_3 FOREIGN KEY (sector_id) REFERENCES sectors (id) ON DELETE CASCADE,
  CONSTRAINT equipments_ibfk_4 FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);