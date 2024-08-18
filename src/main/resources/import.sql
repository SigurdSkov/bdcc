-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

--CREATE TABLE CounterEntity (
--    id BIGINT AUTO_INCREMENT PRIMARY KEY,
--    count BIGINT,
--    name VARCHAR(255)
--);

--INSERT INTO CounterEntity(id, count, name) VALUES (1, 42, 'Sigurd');
--INSERT INTO CounterEntity(id, count, name) VALUES (2, 69, 'Ronja');
--
--ALTER SEQUENCE CounterEntity_SEQ RESTART WITH 51;


INSERT INTO BankAccountEntity(id, accountNumber, balance, fName, lName) VALUES(1, '1000000001', 431, 'Sigurd', 'Jensen');
INSERT INTO BankAccountEntity(id, accountNumber, balance, fName, lName) VALUES(2, '1000000002', 8413, 'Ronja', 'Skov');
INSERT INTO BankAccountEntity(id, accountNumber, balance, fName, lName) VALUES(3, '1000000003', 54301, 'Søren', 'Jensen');

ALTER SEQUENCE BankAccountEntity_SEQ RESTART WITH 51;