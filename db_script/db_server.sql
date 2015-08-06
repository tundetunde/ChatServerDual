DROP SCHEMA IF EXISTS chatServer
CREATE SCHEMA chatServer

DROP TABLE IF EXISTS chatServer.RegTable
CREATE TABLE chatServer.RegTable(Mobile VARCHAR(15) NOT NULL, RegId VARCHAR(40) NOT NULL, PRIMARY KEY(Mobile));

INSERT INTO chatServer.RegTable(Mobile, RegId) VALUES ('0812229044', '123ertfyguhijokpknbtyh123');