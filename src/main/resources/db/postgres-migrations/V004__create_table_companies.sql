CREATE SEQUENCE companies_seq;

CREATE TABLE companies (
  id smallint check (id > 0) NOT NULL DEFAULT NEXTVAL ('companies_seq'),
  corporate_name varchar(120) DEFAULT NULL,
  fantasy_name varchar(120) NOT NULL,
  cnpj varchar(18) DEFAULT NULL,
  PRIMARY KEY (id)
)  ;