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