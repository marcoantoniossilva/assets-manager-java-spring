CREATE SEQUENCE recuperation_tokens_seq;

CREATE TABLE recuperation_tokens (
  id smallint check (id > 0) NOT NULL DEFAULT NEXTVAL ('recuperation_tokens_seq'),
  user_id smallint check (user_id > 0) DEFAULT NULL,
  token varchar(120) DEFAULT NULL,
  expiration_time TIMESTAMP(0) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT recuperation_tokens_ibfk_1 FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE INDEX recuperation_tokens_user_id ON recuperation_tokens (user_id);