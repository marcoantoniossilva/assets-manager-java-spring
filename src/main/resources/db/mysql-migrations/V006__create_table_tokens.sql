CREATE TABLE tokens (
  id smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  user_id smallint(5) unsigned DEFAULT NULL,
  token varchar(120) COLLATE utf8_unicode_ci DEFAULT NULL,
  expiration_time DATETIME DEFAULT NULL,
  PRIMARY KEY (id),
  KEY user_id (user_id),
  CONSTRAINT tokens_ibfk_1 FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;