--https://ugeo.urbistat.com/AdminStat/en/se/demografia/eta/stockholms-lan/1/3
--https://experience.arcgis.com/experience/a6d20c1544f34d33b60026f45b786230
--https://www.folkhalsomyndigheten.se/smittskydd-beredskap/utbrott/aktuella-utbrott/covid-19/statistik-och-analyser/bekraftade-fall-i-sverige/
--https://regionblekinge.se/halsa-och-vard/for-vardgivare/smittskyddsenheten/information-om-coronaviruset/information-about-the-coronavirus.html
--https://news.google.com/covid19/map?hl=es-419&mid=%2Fm%2F0syfj&gl=US&ceid=US%3Aes-419
--https://platz.se/coronavirus/?lan=stockholm&lang=en



INSERT INTO location ( location_id, name, population, average_age) VALUES ('1','Stockholm','974073','39,04');
INSERT INTO location ( location_id, name, population, average_age) VALUES ('2','Uppsala','225164','38,39');
INSERT INTO location ( location_id, name, population, average_age) VALUES ('3','Karlskrona', '66675', '41,61');



INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ( '1', date '2021-04-27', '89681', '89014', '1630', '62,17', '5', '8', '9', '17', '1');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ( '2', date '2021-04-26', '85999', '85269', '1625', '62,25', '5', '6', '9', '17', '1');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ( '3', date '2021-04-25', '85999', '85269', '1619', '62,66', '5', '6', '9', '17', '1');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ( '4', date '2021-04-24', '85999', '85269', '1614', '60,35', '5', '7', '9', '17', '1');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ( '5', date '2021-04-23', '85999', '85269', '1608', '60,85', '5', '4', '9', '17', '1');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ( '6', date '2021-04-22', '85999', '85269', '1601', '61,24', '5', '4', '9', '17', '1');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ( '7', date '2021-04-21', '85999', '85269', '1596', '61,99', '5', '4', '9', '17', '1');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ( '8', date '2021-04-20', '85999', '85269', '1590', '62,17', '5', '7', '9', '17', '1');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ( '9', date '2021-04-19', '81972', '81364', '1586', '62,63', '5', '8', '9', '17', '1');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ( '10', date '2021-04-18', '81972', '81364', '1582', '62,05', '5', '7', '9', '17', '1');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ( '11', date '2021-04-17', '81972', '81364', '1577', '62,17', '5', '7', '9', '17', '1');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ( '12', date '2021-04-16', '81972', '81364', '1570', '62,64', '5', '9', '9', '17', '1');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ( '13', date '2021-04-15', '81972', '81364', '1563', '62,23', '5', '9', '9', '17', '1');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ( '14', date '2021-04-14', '81972', '81364', '1567', '61,74', '5', '5', '9', '17', '1');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ( '15', date '2021-04-13', '81972', '81364', '1561', '62,17', '5', '6', '9', '17', '1');

INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ('16', date '2021-04-27','35778','35675','517','62,8','4','4','3','3','2');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ('17', date '2021-04-26','35655','35576','513','63','4','4','4','3','2');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ('18', date '2021-04-25','35214','35175','513','63,8','4','6','6','3','2');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ('19', date '2021-04-24','35214','35175','513','64','4','7','6','3','2');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ('20', date '2021-04-23','35214','35175','513','63,9','4','9','8','3','2');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ('21', date '2021-04-22','34964','34887','513','62,5','4','10','9','3','2');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ('22', date '2021-04-21','34566','34452','511','61,9','4','7','6','3','2');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ('23', date '2021-04-20','34164','34024','510','62','4','6','5','3','2');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ('24', date '2021-04-19','33979','33759','506','62,1','4','2','3','3','2');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ('25', date '2021-04-18','33507','33305','506','62,8','4','7','8','3','2');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ('26', date '2021-04-17','33507','33305','506','63,1','4','6','7','3','2');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ('27', date '2021-04-16','33507','33305','506','62,7','4','8','7','3','2');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ('28', date '2021-04-15','33164','32956','506','62,4','4','4','5','3','2');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ('29', date '2021-04-14','32820','32534','503','62,2','4','8','7','3','2');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ('30', date '2021-04-13','32528','32375','503','62,5','4','5','6','3','2');

INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ( '31', date '2021-04-27', '4271', '4026', '48', '39,66', '2', '7', '8', '5', '3');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ( '32', date '2021-04-26', '4268', '4021', '48', '39,66', '2', '8', '8', '5', '3');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ( '33', date '2021-04-25', '4199', '4015', '48', '39,66', '2', '8', '8', '5', '3');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ( '34', date '2021-04-24', '4157', '4014', '48', '39,66', '2', '5', '8', '5', '3');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ( '35', date '2021-04-23', '4120', '4012', '46', '39,66', '2', '6', '8', '5', '3');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ( '36', date '2021-04-22', '4072', '3992', '46', '39,66', '2', '6', '8', '5', '3');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ( '37', date '2021-04-21', '4068', '3986', '46', '39,66', '2', '8', '8', '5', '3');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ( '38', date '2021-04-20', '4028', '3979', '46', '39,66', '2', '8', '8', '5', '3');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ( '39', date '2021-04-19', '4002', '3963', '46', '39,66', '2', '4', '7', '5', '3');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ( '40', date '2021-04-18', '3982', '3955', '43', '39,66', '2', '7', '8', '5', '3');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ( '41', date '2021-04-17', '3933', '3948', '43', '39,66', '2', '7', '8', '5', '3');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ( '42', date '2021-04-16', '3933', '3938', '43', '39,66', '2', '7', '8', '5', '3');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ( '43', date '2021-04-15', '3883', '3742', '43', '39,66', '2', '8', '8', '5', '3');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ( '44', date '2021-04-14', '3858', '3726', '42', '39,66', '2', '6', '8', '5', '3');
INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id)
VALUES ( '45', date '2021-04-13', '3822', '3714', '42', '39,66', '2', '6', '8', '5', '3');



INSERT INTO hospital ( hospital_id, name, size_shift, icu_size, hosp_size, equipment_limit, location_id)
VALUES ( '1', 'Danderyds Hospital', '500', '40', '1300', '2000', '1');
INSERT INTO hospital ( hospital_id, name, size_shift, icu_size, hosp_size, equipment_limit, location_id)
VALUES ( '2', 'Karolinska University Hospital', '600', '55', '1500', '3000', '1');
INSERT INTO hospital ( hospital_id, name, size_shift, icu_size, hosp_size, equipment_limit, location_id)
VALUES ( '3', 'Norrtälje Hospital', '300', '25', '850', '1500', '1');

INSERT INTO hospital ( hospital_id, name, size_shift, icu_size, hosp_size, equipment_limit, location_id)
VALUES ( '4', 'Uppsala University Hospital', '350', '27', '1000', '2000', '2');
INSERT INTO hospital ( hospital_id, name, size_shift, icu_size, hosp_size, equipment_limit, location_id)
VALUES ( '5', 'Elisabeth Hospital', '250', '18', '850', '1500', '2');

INSERT INTO hospital ( hospital_id, name, size_shift, icu_size, hosp_size, equipment_limit, location_id)
VALUES ( '6', 'Karlskrona Hospital', '200', '13', '700', '1000', '3');



INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '1', date '2021-04-27', '16', '21', '81', '753', '1687', '1');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '2', date '2021-04-26', '17', '21', '83', '764', '1912', '1');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '3', date '2021-04-25', '15', '19', '85', '783', '486', '1');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '4', date '2021-04-24', '15', '19', '85', '783', '747', '1');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '5', date '2021-04-23', '15', '19', '85', '783', '997', '1');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '6', date '2021-04-22', '15', '19', '87', '791', '1153', '1');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '7', date '2021-04-21', '14', '18', '89', '815', '1396', '1');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '8', date '2021-04-20', '14', '17', '92', '822', '1583', '1');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '9', date '2021-04-19', '16', '20', '99', '853', '1823', '1');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '10', date '2021-04-18', '17', '22', '94', '839', '521', '1');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '11', date '2021-04-17', '17', '22', '94', '839', '793', '1');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '12', date '2021-04-16', '17', '22', '94', '839', '1034', '1');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '13', date '2021-04-15', '17', '22', '92', '842', '1275', '1');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '14', date '2021-04-14', '17', '22', '94', '856', '1493', '1');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '15', date '2021-04-13', '19', '25', '100', '861', '1745', '1');

INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '16', date '2021-04-27', '18', '30', '85', '964', '2397', '2');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '17', date '2021-04-26', '19', '31', '87', '953', '2784', '2');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '18', date '2021-04-25', '17', '30', '91', '975', '203', '2');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '19', date '2021-04-24', '17', '29', '91', '983', '526', '2');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '20', date '2021-04-23', '17', '30', '91', '972', '984', '2');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '21', date '2021-04-22', '18', '31', '94', '992', '1368', '2');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '22', date '2021-04-21', '17', '29', '95', '987', '1721', '2');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '23', date '2021-04-20', '17', '29', '99', '991', '2098', '2');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '24', date '2021-04-19', '18', '30', '107', '1004', '2476', '2');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '25', date '2021-04-18', '20', '32', '102', '1012', '2838', '2');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '26', date '2021-04-17', '20', '32', '102', '1013', '295', '2');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '27', date '2021-04-16', '20', '31', '102', '1013', '628', '2');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '28', date '2021-04-15', '21', '33', '98', '1006', '979', '2');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '29', date '2021-04-14', '21', '34', '101', '1021', '1347', '2');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '30', date '2021-04-13', '22', '34', '108', '1028', '1739', '2');

INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '31', date '2021-04-27', '13', '17', '68', '531', '815', '3');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '32', date '2021-04-26', '13', '16', '67', '527', '1094', '3');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '33', date '2021-04-25', '12', '16', '68', '536', '1275', '3');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '34', date '2021-04-24', '12', '15', '68', '536', '1452', '3');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '35', date '2021-04-23', '12', '15', '68', '532', '285', '3');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '36', date '2021-04-22', '12', '14', '69', '541', '497', '3');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '37', date '2021-04-21', '12', '16', '71', '543', '629', '3');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '38', date '2021-04-20', '12', '16', '73', '549', '875', '3');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '39', date '2021-04-19', '13', '17', '77', '558', '1028', '3');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '40', date '2021-04-18', '14', '17', '75', '553', '1251', '3');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '41', date '2021-04-17', '14', '18', '75', '556', '1427', '3');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '42', date '2021-04-16', '14', '18', '75', '551', '183', '3');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '43', date '2021-04-15', '14', '17', '73', '552', '348', '3');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '44', date '2021-04-14', '14', '18', '74', '558', '578', '3');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '45', date '2021-04-13', '14', '18', '78', '563', '762', '3');

INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '46', date '2021-04-27', '14', '17', '73', '772', '1100', '4');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '47', date '2021-04-26', '15', '19', '76', '781', '1268', '4');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '48', date '2021-04-25', '13', '17', '74', '774', '1420', '4');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '49', date '2021-04-24', '13', '16', '72', '769', '1568', '4');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '50', date '2021-04-23', '16', '20', '71', '767', '1687', '4');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '51', date '2021-04-22', '16', '20', '80', '776', '1835', '4');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '52', date '2021-04-21', '14', '18', '77', '764', '1983', '4');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '53', date '2021-04-20', '16', '21', '76', '762', '890', '4');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '54', date '2021-04-19', '15', '20', '75', '763', '1056', '4');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '55', date '2021-04-18', '16', '21', '85', '793', '1178', '4');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '56', date '2021-04-17', '14', '18', '73', '784', '1346', '4');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '57', date '2021-04-16', '14', '19', '62', '773', '1587', '4');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '58', date '2021-04-15', '17', '22', '64', '771', '1762', '4');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '59', date '2021-04-14', '15', '20', '63',  '765','1967', '4');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '60', date '2021-04-13', '12', '17', '65', '770', '979', '4');

INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '61', date '2021-04-27', '11', '12', '65', '528', '905', '5');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '62', date '2021-04-26', '12', '12', '63', '528', '1034', '5');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '63', date '2021-04-25', '10', '11', '66', '538', '1153', '5');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '64', date '2021-04-24', '13', '14', '62', '578', '1283', '5');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '65', date '2021-04-23', '14', '16', '68', '592', '1371', '5');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '66', date '2021-04-22', '13', '14', '61', '572', '1453', '5');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '67', date '2021-04-21', '13', '14', '65', '583', '1597', '5');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '68', date '2021-04-20', '12', '13', '61', '572', '975', '5');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '69', date '2021-04-19', '14', '17', '70', '598', '1101', '5');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '70', date '2021-04-18', '12', '15', '63', '582', '1260', '5');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '71', date '2021-04-17', '14', '16', '70', '596', '1345', '5');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '72', date '2021-04-16', '13', '15', '62', '573', '1423', '5');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '73', date '2021-04-15', '11', '13', '63', '568', '1500', '5');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '74', date '2021-04-14', '13', '14', '64', '575', '1578', '5');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '75', date '2021-04-13', '12', '14', '62', '568', '492', '5');

INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '76', date '2021-04-27', '3', '4', '12', '321', '578', '6');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '77', date '2021-04-26', '2', '3', '9', '318', '621', '6');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '78', date '2021-04-25', '3', '3', '11', '323', '746', '6');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '79', date '2021-04-24', '3', '3', '11', '323', '789', '6');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '80', date '2021-04-23', '3', '3', '11', '322', '836', '6');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '81', date '2021-04-22', '3', '3', '10', '320', '913', '6');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '82', date '2021-04-21', '3', '3', '10', '321','1000', '6');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '83', date '2021-04-20', '2', '2', '10', '321', '448', '6');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '84', date '2021-04-19', '1', '2', '10', '323', '537', '6');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '85', date '2021-04-18', '1', '2', '8', '318', '611', '6');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '86', date '2021-04-17', '1', '2', '8', '318', '746', '6');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '87', date '2021-04-16', '1', '1', '8', '320', '812', '6');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '88', date '2021-04-15', '2', '2', '9', '324', '870', '6');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '89', date '2021-04-14', '3', '4', '6', '316', '984', '6');
INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)
VALUES ( '90', date '2021-04-13', '3', '4', '7', '318','492', '6');
