INSERT INTO DEPARTAMENT (name) VALUES ('Financial');
INSERT INTO DEPARTAMENT (name) VALUES ('Marketing');

INSERT INTO EMPLOYEE (name, salary, date_created, id_departament) VALUES ('Lucas Fernandes', 9000.00, CURRENT_TIMESTAMP(), SELECT id FROM DEPARTAMENT WHERE name = 'Financial');
INSERT INTO EMPLOYEE (name, salary, date_created, id_departament) VALUES ('Bianca Santana', 5000.00, CURRENT_TIMESTAMP(), SELECT id FROM DEPARTAMENT WHERE name = 'Marketing');
INSERT INTO EMPLOYEE (name, salary, date_created, id_departament) VALUES ('Luna Fernandes', 3000.00, CURRENT_TIMESTAMP(), SELECT id FROM DEPARTAMENT WHERE name = 'Financial');