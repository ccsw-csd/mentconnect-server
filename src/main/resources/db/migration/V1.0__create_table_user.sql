CREATE TABLE user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  username varchar(20) NOT NULL,
  password varchar(50) NOT NULL,
  name varchar(200) NOT NULL,
  surnames varchar(200) NOT NULL,
  email varchar(200) NOT NULL,
  user_type varchar(3) NOT NULL,
  is_admin BOOLEAN NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT uc_username UNIQUE (username)
);

INSERT INTO user (username, password, name, surnames, email, user_type, is_admin) VALUES ('admin', MD5('admin'), 'Admin', 'MentConnect', 'admin@mentconnect.com', 'INT', true);