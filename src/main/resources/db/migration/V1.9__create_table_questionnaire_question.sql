CREATE TABLE questionnaire_question (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  questionnaire_id bigint(20) NOT NULL,
  question_id bigint(20) NOT NULL,
  timeslot char(1),
  PRIMARY KEY (id),
  CONSTRAINT questionnaire_question_questionnaire_fk FOREIGN KEY (questionnaire_id) REFERENCES questionnaire(id),
  CONSTRAINT questionnaire_question_question_fk FOREIGN KEY (question_id) REFERENCES question(id)
);