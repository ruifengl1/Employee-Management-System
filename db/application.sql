-- ApplicationService Schema
CREATE DATABASE IF NOT EXISTS application;
USE application;

DROP TABLE IF EXISTS ApplicationWorkFlow;
DROP TABLE IF EXISTS DigitalDocument;


CREATE TABLE IF NOT EXISTS ApplicationWorkFlow(
    applicationWorkFlowId int auto_increment,
	employeeId int,
    createDate DATETIME,
    lastModificationDate DATETIME,
    `status` varchar(255),
    comment text,
	PRIMARY KEY (applicationWorkFlowId)
);

CREATE TABLE IF NOT EXISTS DigitalDocument(
    digitalDocumentId int auto_increment,
	`type` varchar(255),
    isRequired boolean,
    `path` varchar(255),
    `description` text,
    title varchar(255),
	PRIMARY KEY (digitalDocumentId)
);

-- Insert defult application
INSERT INTO ApplicationWorkFlow (employeeId, status)
VALUES (2 , 'INITIAL');


