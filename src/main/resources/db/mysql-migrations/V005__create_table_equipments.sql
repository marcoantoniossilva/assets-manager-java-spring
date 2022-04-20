CREATE TABLE equipments (
  id smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  company_id smallint(5) unsigned DEFAULT NULL,
  sector_id smallint(5) unsigned DEFAULT NULL,
  type_id smallint(5) unsigned DEFAULT NULL,
  user_id smallint(5) unsigned DEFAULT NULL,
  acquisition_value decimal(9,2) DEFAULT NULL,
  description varchar(120) COLLATE utf8_unicode_ci DEFAULT NULL,
  acquisition_date date DEFAULT NULL,
  nfe_path varchar(120) COLLATE utf8_unicode_ci DEFAULT NULL,
  status enum(ACTIVE,RESERVE,INACTIVE) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (id),
  KEY company_id (company_id),
  KEY sector_id (sector_id),
  KEY type_id (type_id),
  KEY user_id (user_id),
  CONSTRAINT equipments_ibfk_1 FOREIGN KEY (type_id) REFERENCES types (id) ON DELETE CASCADE,
  CONSTRAINT equipments_ibfk_2 FOREIGN KEY (company_id) REFERENCES companies (id) ON DELETE CASCADE,
  CONSTRAINT equipments_ibfk_3 FOREIGN KEY (sector_id) REFERENCES sectors (id) ON DELETE CASCADE,
  CONSTRAINT equipments_ibfk_4 FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;