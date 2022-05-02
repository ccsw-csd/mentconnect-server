CREATE TABLE role (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  code varchar(20) NOT NULL,
  type varchar(3) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT uc_code UNIQUE (code)
);

INSERT INTO role (code, type) VALUES ('ADMIN', 'INT');
INSERT INTO role (code, type) VALUES ('STAFF', 'INT');
INSERT INTO role (code, type) VALUES ('PAT_INFO', 'EXT');
INSERT INTO role (code, type) VALUES ('PAT_PHOTO', 'EXT');
INSERT INTO role (code, type) VALUES ('PAT_FORUM_REED', 'EXT');
INSERT INTO role (code, type) VALUES ('PAT_FORUM_WRITE', 'EXT');
INSERT INTO role (code, type) VALUES ('PAT_FORUM_ANSWER', 'EXT');
INSERT INTO role (code, type) VALUES ('PAT_DAILY', 'EXT');