CREATE TABLE equipment_types (
  id smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  description varchar(120) COLLATE utf8_unicode_ci DEFAULT NULL,
  depreciation tinyint(3) unsigned DEFAULT NULL,
  depreciation_term tinyint(3) unsigned DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;