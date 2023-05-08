CREATE TABLE weekday (
  id int(1) NOT NULL,
  code char(3) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT uc_VALUE UNIQUE (code)
);


INSERT INTO weekday (id, code) VALUES (1, 'MON');
INSERT INTO weekday (id, code) VALUES (2, 'TUE');
INSERT INTO weekday (id, code) VALUES (3, 'WEN');
INSERT INTO weekday (id, code) VALUES (4, 'THU');
INSERT INTO weekday (id, code) VALUES (5, 'FRI');
INSERT INTO weekday (id, code) VALUES (6, 'SAT');
INSERT INTO weekday (id, code) VALUES (7, 'SUN');