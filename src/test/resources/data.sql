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

INSERT INTO user (id, username, password, name, surnames, email) VALUES (1, 'admin', 'pass', 'Admin', 'MentConnect', 'admin@mentconnect.com');
INSERT INTO user (id, username, password, name, surnames, email) VALUES (2, 'staff', 'pass', 'Staff', 'MentConnect', 'stuff@mentconnect.com');
INSERT INTO user (id, username, password, name, surnames, email) VALUES (3, 'patient', 'pass', 'Patient', 'MentConnect', 'patient@mentconnect.com');

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

INSERT INTO user_role (user_id, role_id) VALUES (1, (SELECT id FROM role WHERE code = 'ADMIN'));
INSERT INTO user_role (user_id, role_id) VALUES (1, (SELECT id FROM role WHERE code = 'PAT_DAILY'));
INSERT INTO user_role (user_id, role_id) VALUES (2, (SELECT id FROM role WHERE code = 'STAFF'));
INSERT INTO user_role (user_id, role_id) VALUES (3, (SELECT id FROM role WHERE code = 'PAT_DAILY'));


CREATE TABLE questionnaire (
  id BIGINT NOT NULL AUTO_INCREMENT,
  description VARCHAR(45) NOT NULL,
  user_id BIGINT NOT NULL,
  create_date DATE NOT NULL,
  last_edit_date DATE NULL,
  PRIMARY KEY (id),
  CONSTRAINT questionnaire_user_fk FOREIGN KEY (user_id) REFERENCES user(id)
  );
  
 INSERT INTO questionnaire (id, description, user_id ,create_date, last_edit_date) VALUES (1, 'Prueba de descripcion admin ', 1 ,'2022-05-23', '2022-05-23');
 INSERT INTO questionnaire (id, description, user_id ,create_date, last_edit_date) VALUES (2, 'Prueba de descripcion staff ', 2 ,'2022-05-23', '2022-05-23');
 
 CREATE TABLE patient (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  user_id bigint(20) NOT NULL,
  nif varchar(20) NOT NULL,
  gender char(1),
  date_birth date,
  phone varchar(20) NOT NULL,
  sip varchar(30),
  medical_history varchar(50),
  PRIMARY KEY (id),
  CONSTRAINT uc_nif UNIQUE (nif),
  CONSTRAINT user_patient_fk FOREIGN KEY (user_id) REFERENCES user(id)
);

INSERT INTO patient (id, user_id, nif, gender, date_birth, phone, sip, medical_history) VALUES (1, (SELECT id FROM user WHERE username = 'admin'), '12345678Y', 'H', '2022-06-02', '666666666', 'P159753P', 'P159753P');
INSERT INTO patient (id, user_id, nif, gender, date_birth, phone, sip, medical_history) VALUES (2, (SELECT id FROM user WHERE username = 'staff'), '12345678X', 'H', '2022-06-02', '666666666', 'P159753P', 'P159753P');
INSERT INTO patient (id, user_id, nif, gender, date_birth, phone, sip, medical_history) VALUES (3, (SELECT id FROM user WHERE username = 'patient'), '12345678Z', 'H', '2022-06-02', '666666666', 'P159753P', 'P159753P');

CREATE TABLE answer_type (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  description varchar(300) NOT NULL,
  PRIMARY KEY (id)
);
INSERT INTO answer_type (id, description) VALUES (1, 'Dicotomica');
INSERT INTO answer_type (id, description) VALUES (2, 'Poli');



CREATE TABLE question (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  question varchar(300) NOT NULL,
  answer_type_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT question_answer_type_id_fk FOREIGN KEY (answer_type_id) REFERENCES answer_type(id)
);
INSERT INTO question (id, question, answer_type_id) VALUES (1, 'Pregunta 1', 1);
INSERT INTO question (id, question, answer_type_id) VALUES (2, 'Pregunta 2', 1);
INSERT INTO question (id, question, answer_type_id) VALUES (3, 'Pregunta 3', 2);


CREATE TABLE answer_type_value (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  value varchar(300) NOT NULL,
  answer_type_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT answer_type_value_answer_type_id_fk FOREIGN KEY (answer_type_id) REFERENCES answer_type(id)
);
INSERT INTO answer_type_value (id, value, answer_type_id) VALUES (1, 'Si', 1);
INSERT INTO answer_type_value (id, value, answer_type_id) VALUES (2, 'No', 1);


CREATE TABLE questionnaire_patient (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  questionnaire_id bigint(20) NOT NULL,
  patient_id bigint(20) NOT NULL,
  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT questionnaire_id_questionnaire_patient_fk FOREIGN KEY (questionnaire_id) REFERENCES questionnaire(id),
  CONSTRAINT patient_id_questionnaire_patient_fk FOREIGN KEY (patient_id) REFERENCES patient(id)
);
INSERT INTO questionnaire_patient (id, questionnaire_id, patient_id, start_date, end_date) VALUES (1, 1, 1, '2023-03-02', '2023-03-05');
INSERT INTO questionnaire_patient (id, questionnaire_id, patient_id, start_date, end_date) VALUES (2, 2, 2, '2023-03-14', '2023-03-16');
INSERT INTO questionnaire_patient (id, questionnaire_id, patient_id, start_date, end_date) VALUES (3, 1, 1, '2023-03-17', '2023-03-31');


CREATE TABLE questionnaire_question (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  questionnaire_id bigint(20) NOT NULL,
  question_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT questionnaire_id_questionnaire_question_fk FOREIGN KEY (questionnaire_id) REFERENCES questionnaire(id),
  CONSTRAINT question_id_questionnaire_question_fk FOREIGN KEY (question_id) REFERENCES question(id)
);
INSERT INTO questionnaire_question (id, questionnaire_id, question_id) VALUES (1, 1, 1);
INSERT INTO questionnaire_question (id, questionnaire_id, question_id) VALUES (2, 1, 2);
INSERT INTO questionnaire_question (id, questionnaire_id, question_id) VALUES (3, 2, 3);

CREATE TABLE user_patient (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    user_id bigint(20) NOT NULL,
    patient_id bigint(20) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT user_patient_user_fk FOREIGN KEY (user_id) REFERENCES user(id),
    CONSTRAINT user_patient_patient_fk FOREIGN KEY (patient_id) REFERENCES patient(id)
    
);


CREATE TABLE diary (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  description VARCHAR(4000) NOT NULL,
  create_date date NOT NULL,
  patient_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT patient_id FOREIGN KEY (patient_id) REFERENCES patient(id)
);

INSERT INTO diary (id, description, create_date, patient_id) VALUES (1, 'primera descripcion', '2023-03-10', 1);
INSERT INTO diary (id, description, create_date, patient_id) VALUES (2, 'segunda descripcion', '2024-04-11', 1);
INSERT INTO diary (id, description, create_date, patient_id) VALUES (3, 'tercera descripcion', '2025-05-12', 1);