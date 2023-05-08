CREATE TABLE questionnaire_question (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  questionnaire_id bigint(20) NOT NULL,
  question_id bigint(20) NOT NULL,
  timeslot char(15) NOT NULL,
  alert_config_answer_type_id bigint(20),
  alert_config_consecutive_answers INT,
  PRIMARY KEY (id),
  CONSTRAINT questionnaire_question_questionnaire_fk FOREIGN KEY (questionnaire_id) REFERENCES questionnaire(id),
  CONSTRAINT questionnaire_question_question_fk FOREIGN KEY (question_id) REFERENCES question(id),
  CONSTRAINT alert_config_answer_type_value_id_fk FOREIGN KEY (alert_config_answer_type_id) REFERENCES answer_type_value(id)
);