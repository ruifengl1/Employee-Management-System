CREATE DATABASE IF NOT EXISTS authentication;
USE authentication;


DROP TABLE IF EXISTS RegistrationToken;
DROP TABLE IF EXISTS UserRole;
DROP TABLE IF EXISTS Role;
DROP TABLE IF EXISTS User;

CREATE TABLE User (
    userId INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    createDate DATETIME NOT NULL default NOW(),
    lastModificationDate DATETIME NOT NULL default NOW(),
    activeFlag BOOLEAN NOT NULL default true
);

CREATE TABLE Role (
    roleId INT PRIMARY KEY AUTO_INCREMENT,
    roleName VARCHAR(50) NOT NULL,
    roleDescription VARCHAR(255) NOT NULL,
    createDate DATETIME NOT NULL,
    lastModificationDate DATETIME NOT NULL
);

CREATE TABLE UserRole (
    userRoleId INT PRIMARY KEY AUTO_INCREMENT,
    userId INT NOT NULL,
    roleId INT NOT NULL,
    activeFlag BOOLEAN NOT NULL,
    createDate DATETIME NOT NULL,
    lastModificationDate DATETIME NOT NULL,
    FOREIGN KEY (userId) REFERENCES User(userId),
    FOREIGN KEY (roleId) REFERENCES Role(roleId)
);

CREATE TABLE RegistrationToken (
    registrationTokenId INT PRIMARY KEY AUTO_INCREMENT,
    token VARCHAR(255) NOT NULL,
    email VARCHAR(50) NOT NULL,
    expirationDate DATETIME NOT NULL,
    createBy INT NOT NULL,
    consumed BOOLEAN NOT NULL default false,
	FOREIGN KEY (createBy) REFERENCES User(userId)
);


-- Insert user record
INSERT INTO User (username, email, password)
VALUES ('admin', 'admin@example.com', '$2a$10$BJf6vvQKxTXaSzvBMivzb.cfuZTYhdqfcvf/gGE5goE7d/yrGKQB6');

-- Insert admin role record
INSERT INTO Role (roleName, roleDescription, createDate, lastModificationDate)
VALUES ('admin', 'Administrator role', NOW(), NOW());

-- Get the id of the newly created user and admin role
SET @userId = LAST_INSERT_ID();
SET @roleId = LAST_INSERT_ID();

-- Insert user-role association record
INSERT INTO UserRole (userId, roleId, activeFlag, createDate, lastModificationDate)
VALUES (@userId, @roleId, 1, NOW(), NOW());

-- Insert user record
INSERT INTO User (username, email, password)
VALUES ('user', 'user@example.com', '$2a$10$BJf6vvQKxTXaSzvBMivzb.cfuZTYhdqfcvf/gGE5goE7d/yrGKQB6');

-- Insert admin role record
INSERT INTO Role (roleName, roleDescription, createDate, lastModificationDate)
VALUES ('user', 'Normal user role', NOW(), NOW());

-- Get the id of the newly created user and admin role
SET @userId = LAST_INSERT_ID();
SET @roleId = LAST_INSERT_ID();

-- Insert user-role association record
INSERT INTO UserRole (userId, roleId, activeFlag, createDate, lastModificationDate)
VALUES (@userId, @roleId, 1, NOW(), NOW());

INSERT INTO RegistrationToken (token, email, expirationDate, createBy)
VALUES ('1681065484215-c164e267-8a39-4267-b8ae-177b83077632', 'newUser@example.com', DATE_ADD(NOW(), INTERVAL 3 HOUR), 1);