CREATE TABLE questionnairequestion_weekdays (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  questionnaire_question_id bigint(20) NOT NULL,
  week_day INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_questionnairequestion_weekdays_questionnairequestion FOREIGN KEY (questionnaire_question_id) REFERENCES questionnaire_question(id)
);
