CREATE TABLE answer_type (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  description varchar(300) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE question (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  question varchar(300) NOT NULL,
  answer_type_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT question_answer_type_id_fk FOREIGN KEY (answer_type_id) REFERENCES answer_type(id)
);

CREATE TABLE answer_type_value (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  value varchar(300) NOT NULL,
  answer_type_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT answer_type_value_answer_type_id_fk FOREIGN KEY (answer_type_id) REFERENCES answer_type(id)
);

CREATE TABLE questionnaire_patient (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  questionnaire_id BIGINT NOT NULL,
  patient_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT questionnaire_patient_questionnaire_fk FOREIGN KEY (questionnaire_id) REFERENCES questionnaire(id),
  CONSTRAINT questionnaire_patient_patient_fk FOREIGN KEY (patient_id) REFERENCES patient(id)
);

CREATE TABLE questionnaire_question (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  questionnaire_id BIGINT NOT NULL,
  question_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT questionnaire_question_questionnaire_fk FOREIGN KEY (questionnaire_id) REFERENCES questionnaire(id),
  CONSTRAINT questionnaire_question_question_fk FOREIGN KEY (question_id) REFERENCES question(id)
);


INSERT INTO answer_type (id, description) VALUES (1, 'Dicotómica');
INSERT INTO answer_type (id, description) VALUES (2, 'Cualitativa politómica');
INSERT INTO answer_type (id, description) VALUES (3, 'Cuantativa politómica');
INSERT INTO answer_type (id, description) VALUES (4, 'Numérica');


INSERT INTO question (id, question, answer_type_id) VALUES (1, '¿Te está generando ansiedad la comida?',1);
INSERT INTO question (id, question, answer_type_id) VALUES (2, '¿Has tenido variación del apetito?',1);
INSERT INTO question (id, question, answer_type_id) VALUES (3, '¿Has tenido alteraciones en el movimiento o rigidez?',1);
INSERT INTO question (id, question, answer_type_id) VALUES (4, '¿Has tenido problemas en el trabajo o estudios?',1);
INSERT INTO question (id, question, answer_type_id) VALUES (5, '¿Te encuentras más irritable o nervioso?',1);
INSERT INTO question (id, question, answer_type_id) VALUES (6, '¿Has tenido variación en la rutina del sueño?',1);
INSERT INTO question (id, question, answer_type_id) VALUES (7, '¿Tienes dificultad para conciliar o mantener el sueño?',1);
INSERT INTO question (id, question, answer_type_id) VALUES (8, '¿Te cuesta concentrarte en alguna tarea o conversación',1);
INSERT INTO question (id, question, answer_type_id) VALUES (9, '¿Se te olvidan cosas o datos con frecuencia?',1);
INSERT INTO question (id, question, answer_type_id) VALUES (10, '¿Has tenido ideas de causarte algún tipo de dolor?',1);
INSERT INTO question (id, question, answer_type_id) VALUES (11, '¿Has tenido ideas de causar a los demás algún tipo de dolor?',1);
INSERT INTO question (id, question, answer_type_id) VALUES (12, '¿Te has alejado de familiares o amigos?',1);

INSERT INTO question (id, question, answer_type_id) VALUES (13, '¿Cómo has dormido hoy?',2);
INSERT INTO question (id, question, answer_type_id) VALUES (14, '¿Cómo te encuentras hoy anímicamente?',2);

INSERT INTO question (id, question, answer_type_id) VALUES (15, '¿Has tomado alcohol?',3);
INSERT INTO question (id, question, answer_type_id) VALUES (16, '¿Has tomado algún tipo de drogas?',3);
INSERT INTO question (id, question, answer_type_id) VALUES (17, '¿Tienes ganas de salir de casa?',3);
INSERT INTO question (id, question, answer_type_id) VALUES (18, '¿Has realizado algún tipo de actividad física?',3);
INSERT INTO question (id, question, answer_type_id) VALUES (19, '¿Sigues una rutina diaria?',3);
INSERT INTO question (id, question, answer_type_id) VALUES (20, '¿Has realizado actividades al aire libre?',3);
INSERT INTO question (id, question, answer_type_id) VALUES (21, '¿Has pasado tiempo con otras personas?',3);

INSERT INTO question (id, question, answer_type_id) VALUES (22, 'Si te has pesado recientemente, ¿cuál es tu peso?',4);
INSERT INTO question (id, question, answer_type_id) VALUES (23, 'Si te has tomado la tensión arterial, ¿cuál es tu TA?',4);
INSERT INTO question (id, question, answer_type_id) VALUES (24, 'Si te has tomado la frecuencia cardíaca, ¿cuál es tu FC?',4);
INSERT INTO question (id, question, answer_type_id) VALUES (25, 'Si te has tomado la glucemia, ¿cuál es tu glucemia?',4);


INSERT INTO answer_type_value (id, value, answer_type_id) VALUES (1, 'Si', 1);
INSERT INTO answer_type_value (id, value, answer_type_id) VALUES (2, 'No', 1);
INSERT INTO answer_type_value (id, value, answer_type_id) VALUES (3, 'Muy mal', 2);
INSERT INTO answer_type_value (id, value, answer_type_id) VALUES (4, 'Mal', 2);
INSERT INTO answer_type_value (id, value, answer_type_id) VALUES (5, 'Normal', 2);
INSERT INTO answer_type_value (id, value, answer_type_id) VALUES (6, 'Bien', 2);
INSERT INTO answer_type_value (id, value, answer_type_id) VALUES (7, 'Muy bien', 2);
INSERT INTO answer_type_value (id, value, answer_type_id) VALUES (8, 'Nada', 3);
INSERT INTO answer_type_value (id, value, answer_type_id) VALUES (9, 'Poco', 3);
INSERT INTO answer_type_value (id, value, answer_type_id) VALUES (10, 'Moderado', 3);
INSERT INTO answer_type_value (id, value, answer_type_id) VALUES (11, 'Bastante', 3);
INSERT INTO answer_type_value (id, value, answer_type_id) VALUES (12, 'Mucho', 3);