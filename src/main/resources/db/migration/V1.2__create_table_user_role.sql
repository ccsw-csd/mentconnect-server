CREATE TABLE user_role (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  user_id bigint(20) NOT NULL,
  role_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT user_role_user_fk FOREIGN KEY (user_id) REFERENCES user(id),
  CONSTRAINT user_role_role_fk FOREIGN KEY (role_id) REFERENCES role(id)
);

INSERT INTO user_role (user_id, role_id) VALUES ((SELECT id FROM user WHERE username = 'admin'), (SELECT id FROM role WHERE code = 'ADMIN'));
INSERT INTO user_role (user_id, role_id) VALUES ((SELECT id FROM user WHERE username = 'staff'), (SELECT id FROM role WHERE code = 'STAFF'));