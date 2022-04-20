CREATE TABLE companies (
  id smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  corporate_name varchar(120) COLLATE utf8_unicode_ci DEFAULT NULL,
  fantasy_name varchar(120) COLLATE utf8_unicode_ci NOT NULL,
  cnpj varchar(18) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;