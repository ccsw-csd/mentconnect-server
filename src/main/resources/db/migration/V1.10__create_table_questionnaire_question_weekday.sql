CREATE TABLE questionnaire_question_weekday (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  questionnaire_question_id bigint(20) NOT NULL,
  week_day INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_questionnaire_question_weekday_questionnaire_question FOREIGN KEY (questionnaire_question_id) REFERENCES questionnaire_question(id)
);