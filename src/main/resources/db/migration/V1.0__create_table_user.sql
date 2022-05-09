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

INSERT INTO user (username, password, name, surnames, email) VALUES ('admin', SHA2('pass', 256), 'Admin', 'MentConnect', 'admin@mentconnect.com');
INSERT INTO user (username, password, name, surnames, email) VALUES ('staff', SHA2('pass', 256), 'Staff', 'MentConnect', 'stuff@mentconnect.com');