-- Generado por Oracle SQL Developer Data Modeler 20.4.0.374.0801
--   en:        2021-04-27 12:20:19 CEST
--   sitio:      Oracle Database 11g
--   tipo:      Oracle Database 11g



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE


DROP TABLE hospital_situation;
DROP TABLE location_situation;
DROP TABLE prediction;
DROP TABLE hospital;
DROP TABLE location;

CREATE TABLE hospital (
    hospital_id      INTEGER NOT NULL,
    name             VARCHAR2(100),
    size_shift       INTEGER,
    icu_size         INTEGER,
    hosp_size        INTEGER,
    equipment_limit  INTEGER,
    location_id      INTEGER NOT NULL
);
ALTER TABLE hospital ADD CONSTRAINT hospital_pk PRIMARY KEY ( hospital_id );

CREATE TABLE hospital_situation (
    hosp_sit_id         INTEGER NOT NULL,
    date_hosp_situation DATE NOT NULL,
    icu_covid_patients  INTEGER,
    icu_total_patients  INTEGER,
    hosp_covid_patients INTEGER,
    hosp_total_patients INTEGER,
    actual_equipment    INTEGER,
    hospital_id         INTEGER NOT NULL
);
ALTER TABLE hospital_situation ADD CONSTRAINT hospital_situation_pk PRIMARY KEY (hosp_sit_id, date_hosp_situation );

CREATE TABLE location (
    location_id  INTEGER NOT NULL,
    name         VARCHAR2(50),
    population   INTEGER,
    average_age  NUMBER(5,2)
);
ALTER TABLE location ADD CONSTRAINT location_pk PRIMARY KEY ( location_id );

CREATE TABLE location_situation (
    loc_sit_id          INTEGER NOT NULL,
    date_loc_situation  DATE NOT NULL,
    sick_patients       INTEGER,
    recovered_patients  INTEGER,
    deaths              INTEGER,
    average_sick_age    NUMBER(5,2),
    lockdown_level      INTEGER,
    weather             INTEGER,
    transport_level     INTEGER,
    vital_companies     INTEGER,
    location_id         INTEGER NOT NULL
);
ALTER TABLE location_situation ADD CONSTRAINT location_situation_pk PRIMARY KEY ( loc_sit_id, date_loc_situation );

CREATE TABLE prediction (
    prediction_id     INTEGER NOT NULL,
    date_prediction   DATE NOT NULL,
    e_icu_total       INTEGER,
    e_icu_covid       INTEGER,
    e_hosp_total      INTEGER,
    e_hosp_covid      INTEGER,
    e_new_sick        INTEGER,
    e_recovered       INTEGER,
    e_deaths          INTEGER,
    personnel_needed  INTEGER,
    equipment_needed  INTEGER,
    location_id       INTEGER NOT NULL
);
ALTER TABLE prediction ADD CONSTRAINT prediction_pk PRIMARY KEY ( prediction_id, date_prediction );


ALTER TABLE hospital
    ADD CONSTRAINT hospital_location_fk FOREIGN KEY ( location_id )
        REFERENCES location ( location_id );

ALTER TABLE hospital_situation
    ADD CONSTRAINT hospital_situation_hospital_fk FOREIGN KEY ( hospital_id )
        REFERENCES hospital ( hospital_id );

ALTER TABLE location_situation
    ADD CONSTRAINT location_situation_location_fk FOREIGN KEY ( location_id )
        REFERENCES location ( location_id );

ALTER TABLE prediction
    ADD CONSTRAINT prediction_location_fk FOREIGN KEY ( location_id )
        REFERENCES location ( location_id );



-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                             5
-- CREATE INDEX                             0
-- ALTER TABLE                              9
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
