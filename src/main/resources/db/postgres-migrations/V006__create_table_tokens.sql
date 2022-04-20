CREATE SEQUENCE tokens_seq;

CREATE TABLE tokens (
  id smallint check (id > 0) NOT NULL DEFAULT NEXTVAL ('tokens_seq'),
  user_id smallint check (user_id > 0) DEFAULT NULL,
  token varchar(120) DEFAULT NULL,
  expiration_time TIMESTAMP(0) DEFAULT NULL,
  PRIMARY KEY (id)
 ,
  CONSTRAINT tokens_ibfk_1 FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
)  ;

CREATE INDEX user_id ON tokens (user_id);