CREATE TABLE weekday (
  id int(1) NOT NULL AUTO_INCREMENT,
  value int(1) not null,
  PRIMARY KEY (id),
  CONSTRAINT uc_VALUE UNIQUE (value)
);

INSERT INTO weekday (value) VALUES (1);
INSERT INTO weekday (value) VALUES (2);
INSERT INTO weekday (value) VALUES (3);
INSERT INTO weekday (value) VALUES (4);
INSERT INTO weekday (value) VALUES (5);
INSERT INTO weekday (value) VALUES (6);
INSERT INTO weekday (value) VALUES (0);
