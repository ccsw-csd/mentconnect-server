CREATE TABLE user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  username varchar(20) NOT NULL,
  password varchar(50) NOT NULL,
  name varchar(200) NOT NULL,
  surnames varchar(200) NOT NULL,
  email varchar(200) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT uc_username UNIQUE (username)
);

INSERT INTO user (username, password, name, surnames, email) VALUES ('admin', MD5('pass'), 'Admin', 'MentConnect', 'admin@mentconnect.com');
INSERT INTO user (username, password, name, surnames, email) VALUES ('staff', MD5('pass'), 'Staff', 'MentConnect', 'stuff@mentconnect.com');