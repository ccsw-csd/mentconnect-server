DELETE
FROM questionnaire_question;

DELETE
FROM answer_type_value;

DELETE
FROM question;

DELETE
FROM answer_type;

INSERT INTO answer_type (id, description)
VALUES (1, 'answer.dichotomic.description');
INSERT INTO answer_type (id, description)
VALUES (2, 'answer.polytomousqualitative.description');
INSERT INTO answer_type (id, description)
VALUES (3, 'answer.polytomousquantitative.description');
INSERT INTO answer_type (id, description)
VALUES (4, 'answer.numeric.description');


INSERT INTO question (id, question, answer_type_id)
VALUES (1, 'question_1.description', 1);
INSERT INTO question (id, question, answer_type_id)
VALUES (2, 'question_2.description', 1);
INSERT INTO question (id, question, answer_type_id)
VALUES (3, 'question_3.description', 1);
INSERT INTO question (id, question, answer_type_id)
VALUES (4, 'question_4.description', 1);
INSERT INTO question (id, question, answer_type_id)
VALUES (5, 'question_5.description', 1);
INSERT INTO question (id, question, answer_type_id)
VALUES (6, 'question_6.description', 1);
INSERT INTO question (id, question, answer_type_id)
VALUES (7, 'question_7.description', 1);
INSERT INTO question (id, question, answer_type_id)
VALUES (8, 'question_8.description', 1);
INSERT INTO question (id, question, answer_type_id)
VALUES (9, 'question_9.description', 1);
INSERT INTO question (id, question, answer_type_id)
VALUES (10, 'question_10.description', 1);
INSERT INTO question (id, question, answer_type_id)
VALUES (11, 'question_11.description', 1);
INSERT INTO question (id, question, answer_type_id)
VALUES (12, 'question_12.description', 1);

INSERT INTO question (id, question, answer_type_id)
VALUES (13, 'question_13.description', 2);
INSERT INTO question (id, question, answer_type_id)
VALUES (14, 'question_14.description', 2);

INSERT INTO question (id, question, answer_type_id)
VALUES (15, 'question_15.description', 3);
INSERT INTO question (id, question, answer_type_id)
VALUES (16, 'question_16.description', 3);
INSERT INTO question (id, question, answer_type_id)
VALUES (17, 'question_17.description', 3);
INSERT INTO question (id, question, answer_type_id)
VALUES (18, 'question_18.description', 3);
INSERT INTO question (id, question, answer_type_id)
VALUES (19, 'question_19.description', 3);
INSERT INTO question (id, question, answer_type_id)
VALUES (20, 'question_20.description', 3);
INSERT INTO question (id, question, answer_type_id)
VALUES (21, 'question_21.description', 3);

INSERT INTO question (id, question, answer_type_id)
VALUES (22, 'question_22.description', 4);
INSERT INTO question (id, question, answer_type_id)
VALUES (23, 'question_23.description', 4);
INSERT INTO question (id, question, answer_type_id)
VALUES (24, 'question_24.description', 4);
INSERT INTO question (id, question, answer_type_id)
VALUES (25, 'question_25.description', 4);


INSERT INTO answer_type_value (id, value, answer_type_id)
VALUES (1, 'answer.dichotomic.yes', 1);
INSERT INTO answer_type_value (id, value, answer_type_id)
VALUES (2, 'answer.dichotomic.no', 1);
INSERT INTO answer_type_value (id, value, answer_type_id)
VALUES (3, 'answer.polytomousqualitative.verybad', 2);
INSERT INTO answer_type_value (id, value, answer_type_id)
VALUES (4, 'answer.polytomousqualitative.bad', 2);
INSERT INTO answer_type_value (id, value, answer_type_id)
VALUES (5, 'answer.polytomousqualitative.normal', 2);
INSERT INTO answer_type_value (id, value, answer_type_id)
VALUES (6, 'answer.polytomousqualitative.good', 2);
INSERT INTO answer_type_value (id, value, answer_type_id)
VALUES (7, 'answer.polytomousqualitative.verygood', 2);
INSERT INTO answer_type_value (id, value, answer_type_id)
VALUES (8, 'answer.polytomousquantitative.nothing', 3);
INSERT INTO answer_type_value (id, value, answer_type_id)
VALUES (9, 'answer.polytomousquantitative.alittlebit', 3);
INSERT INTO answer_type_value (id, value, answer_type_id)
VALUES (10, 'answer.polytomousquantitative.moderate', 3);
INSERT INTO answer_type_value (id, value, answer_type_id)
VALUES (11, 'answer.polytomousquantitative.prettymuch', 3);
INSERT INTO answer_type_value (id, value, answer_type_id)
VALUES (12, 'answer.polytomousquantitative.alot', 3);