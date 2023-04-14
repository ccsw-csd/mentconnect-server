CREATE TABLE diary (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  description VARCHAR(4000) NOT NULL,
  create_date date NOT NULL,
  patient_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT patient_id FOREIGN KEY (patient_id) REFERENCES patient(id)
);