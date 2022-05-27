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

CREATE TABLE user_role (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  user_id bigint(20) NOT NULL,
  role_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT user_role_user_fk FOREIGN KEY (user_id) REFERENCES user(id),
  CONSTRAINT user_role_role_fk FOREIGN KEY (role_id) REFERENCES role(id)
);

INSERT INTO user_role (user_id, role_id) VALUES ((SELECT id FROM user WHERE username = 'admin'), (SELECT id FROM role WHERE code = 'ADMIN'));
INSERT INTO user_role (user_id, role_id) VALUES ((SELECT id FROM user WHERE username = 'staff'), (SELECT id FROM role WHERE code = 'STAFF'));

CREATE TABLE questionnaire (
  id BIGINT NOT NULL AUTO_INCREMENT,
  description VARCHAR(45) NOT NULL,
  questions INT NOT NULL DEFAULT 0,
  patients INT NOT NULL DEFAULT 0,
  user_id BIGINT NOT NULL,
  create_date DATE NOT NULL,
  last_edit_date DATE NULL,
  PRIMARY KEY (id),
  CONSTRAINT questionnaire_user_fk FOREIGN KEY (user_id) REFERENCES user(id)
  );
  
 INSERT INTO questionnaire (description, user_id ,create_date, last_edit_date) VALUES ('Prueba de descripcion admin ', '1' ,'2022-05-23', '2022-05-23');
 INSERT INTO questionnaire (description, user_id ,create_date, last_edit_date) VALUES ('Prueba de descripcion staff ', '1' ,'2022-05-23', '2022-05-23'); 
  
