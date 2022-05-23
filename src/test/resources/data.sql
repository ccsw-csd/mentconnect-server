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

CREATE TABLE questionnaire (
  id BIGINT NOT NULL AUTO_INCREMENT,
  description VARCHAR(45) NOT NULL,
  questions INT NOT NULL DEFAULT 0,
  patients INT NOT NULL DEFAULT 0,
  user_id BIGINT NOT NULL,
  create_date DATE NOT NULL,
  last_edit_date DATE NULL,
  PRIMARY KEY (id),
  CONSTRAINT questionary_user_fk FOREIGN KEY (user_id) REFERENCES user(id)
  );
  
 INSERT INTO questionnaire (description, user_id ,create_date, last_edit_date) VALUES ('Prueba de descripcion admin ', '1' ,'2022-05-23', '2022-05-23');
 INSERT INTO questionnaire (description, user_id ,create_date, last_edit_date) VALUES ('Prueba de descripcion staff ', '1' ,'2022-05-23', '2022-05-23'); 
  