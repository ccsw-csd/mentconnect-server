CREATE TABLE role (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  code varchar(20) NOT NULL,
  name varchar(50) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT uc_code UNIQUE (code)
);

INSERT INTO role (code, name) VALUES ('ADMIN', 'Administrator');
INSERT INTO role (code, name) VALUES ('STAFF', 'Staff member');
INSERT INTO role (code, name) VALUES ('USER', 'Patient user');