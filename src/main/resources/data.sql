INSERT INTO DEPARTAMENT (name) VALUES ('Financial');
INSERT INTO DEPARTAMENT (name) VALUES ('Marketing');

INSERT INTO EMPLOYEE (name, salary, date_created, id_departament) VALUES ('Rhaegar Targeryan', 9000.00, CURRENT_TIMESTAMP(), SELECT id FROM DEPARTAMENT WHERE name = 'Financial');
INSERT INTO EMPLOYEE (name, salary, date_created, id_departament) VALUES ('Aegon Targeryan', 5000.00, CURRENT_TIMESTAMP(), SELECT id FROM DEPARTAMENT WHERE name = 'Marketing');
INSERT INTO EMPLOYEE (name, salary, date_created, id_departament) VALUES ('Tywin Lannister', 3000.00, CURRENT_TIMESTAMP(), SELECT id FROM DEPARTAMENT WHERE name = 'Financial');
INSERT INTO EMPLOYEE (name, salary, date_created, id_departament) VALUES ('Cersei Lannister', 3000.00, CURRENT_TIMESTAMP(), SELECT id FROM DEPARTAMENT WHERE name = 'Financial');
INSERT INTO EMPLOYEE (name, salary, date_created, id_departament) VALUES ('Jaime Lannister', 3000.00, CURRENT_TIMESTAMP(), SELECT id FROM DEPARTAMENT WHERE name = 'Financial');
INSERT INTO EMPLOYEE (name, salary, date_created, id_departament) VALUES ('Tyrion Lannister', 3000.00, CURRENT_TIMESTAMP(), SELECT id FROM DEPARTAMENT WHERE name = 'Financial');
INSERT INTO EMPLOYEE (name, salary, date_created, id_departament) VALUES ('Jon Snow', 3000.00, CURRENT_TIMESTAMP(), SELECT id FROM DEPARTAMENT WHERE name = 'Financial');
INSERT INTO EMPLOYEE (name, salary, date_created, id_departament) VALUES ('Rob Stark', 3000.00, CURRENT_TIMESTAMP(), SELECT id FROM DEPARTAMENT WHERE name = 'Financial');
INSERT INTO EMPLOYEE (name, salary, date_created, id_departament) VALUES ('Brandon Stark', 3000.00, CURRENT_TIMESTAMP(), SELECT id FROM DEPARTAMENT WHERE name = 'Financial');
INSERT INTO EMPLOYEE (name, salary, date_created, id_departament) VALUES ('Khal Drogo', 3000.00, CURRENT_TIMESTAMP(), SELECT id FROM DEPARTAMENT WHERE name = 'Financial');