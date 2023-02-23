CREATE TABLE user_patient (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    user_id bigint(20) NOT NULL,
    patient_id bigint(20) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT user_patient_user_fk FOREIGN KEY (user_id) REFERENCES user(id),
    CONSTRAINT user_patient_patient_fk FOREIGN KEY (patient_id) REFERENCES patient(id)
    
);

