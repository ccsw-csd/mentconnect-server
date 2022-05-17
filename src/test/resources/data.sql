CREATE TABLE user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  username varchar(20) NOT NULL,
  password varchar(64) NOT NULL,
  name varchar(200) NOT NULL,
  surnames varchar(200) NOT NULL,
  email varchar(200) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT uc_username UNIQUE (username)
);

INSERT INTO user (username, password, name, surnames, email) VALUES ('admin', 'pass', 'Admin', 'MentConnect', 'admin@mentconnect.com');
INSERT INTO user (username, password, name, surnames, email) VALUES ('staff', 'pass', 'Staff', 'MentConnect', 'stuff@mentconnect.com');