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