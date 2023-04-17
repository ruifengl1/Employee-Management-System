-- HousingService Schema
CREATE DATABASE IF NOT EXISTS HousingService;
USE HousingService;


DROP TABLE IF EXISTS facilityReportDetail;
DROP TABLE IF EXISTS facilityReport;
DROP TABLE IF EXISTS facility;
DROP TABLE IF EXISTS house;
DROP TABLE IF EXISTS landlord;

CREATE TABLE HousingService.landlord(
                                        landlordId int NOT NULL AUTO_INCREMENT,
                                        firstName varchar(255),
                                        lastName varchar(255),
                                        email varchar(255),
                                        cellphone varchar(255),
                                        PRIMARY KEY (landlordId)
);
CREATE TABLE HousingService.house(
                                     houseId int NOT NULL AUTO_INCREMENT,
                                     landlordId int,
                                     address varchar(500),
                                     maxOccupant int,
                                     PRIMARY KEY (houseId),
                                     FOREIGN KEY (landlordId) REFERENCES landlord(landlordId)
);
CREATE TABLE HousingService.facility(
                                        facilityId int NOT NULL AUTO_INCREMENT,
                                        houseId int,
                                        facilityType varchar(255),
                                        description varchar(1000),
                                        quantity int,
                                        PRIMARY KEY (facilityId),
                                        FOREIGN KEY (houseId) REFERENCES house(houseId)
);
CREATE TABLE HousingService.facilityReport(
                                              facilityReportId int NOT NULL AUTO_INCREMENT,
                                              facilityId int,
                                              employeeId int,
                                              title varchar(255),
                                              description varchar(1000),
                                              createDate DATETIME,
                                              lastModifyDate DATETIME,
                                              status varchar(30), -- Open, In Progress, Closed
                                              PRIMARY KEY (facilityReportId),
                                              FOREIGN KEY (facilityId) REFERENCES facility(facilityId)
);
CREATE TABLE HousingService.facilityReportDetail(
                                                    facilityReportDetailId int NOT NULL AUTO_INCREMENT,
                                                    facilityReportId int,
                                                    userId int, -- both admin and employee are able to leave a comment
                                                    comment varchar(1000),
                                                    createDate DATETIME,
                                                    PRIMARY KEY (facilityReportDetailId),
                                                    FOREIGN KEY (facilityReportId) REFERENCES facilityReport(facilityReportId)
);
----------------------------------------------------------------------------
insert into landlord
values(1,"Tom", "Lee","tomlee@example.com","1234567890"),
      (2,"John", "Kim", "johnkim@example.com", "1112223333"),
      (3, "Jessica", "Yen", "jessicayen@example.com","1212121212")
;

insert into house
values(1,1,"466 Gonzales Court Miami Gardens", 4)
     ,(2,2,"19 2nd St. Dayton", 10)
     ,(3,2,"1603 Rivendell Drive", 6)
     ,(4,3,"1770 Reeves Street", 8)
;

insert into facility
values(1,1,"AC","air conditioner", 1),
      (2,1,"system", "bathroom plumbing", 1),
      --
      (3,2,"furniture", "bed frame",4),
      (4,2,"appliances", "conditioner",1),
      (5,2,"system", "bathroom plumbing",1),
      (6,2,"furniture", "chair",4),
      --
      (7,3,"furniture","bed frame",6),
      (8,3,"furniture","chair",6),
      (9,3,"appliances","microwave",1),
      --
      (10,3,"furniture","bed frame",6),
      (11,3,"appliances","fridge",1)
;

insert into facilityReport
values(1, 1, 2, "clogged", "clogged plumbing pipe",SUBTIME(now(), '10:30:14'),SUBTIME(now(), '8:30:14'), "Open"),
      (2, 2, 2, "lack", "need one more bed frame", NOW(),SUBTIME(now(), '7:30:14'), "In Progress"),
      --
      (3, 3, 3, "lack", "need one more bed frame",SUBTIME(now(), '6:30:14'),SUBTIME(now(), '4:30:14'), "In Progress"),
      (4, 6, 4, "lack", "need one more chair",SUBTIME(now(), '3:30:14'),SUBTIME(now(), '3:30:14'), "In Progress"),
      (5, 5, 3, "clogged", "clogged plumbing pip",SUBTIME(now(), '2:30:14'),SUBTIME(now(), '1:30:14'), "Closed")
;

insert into facilityReportDetail
values(1,1,2,"emergency",SUBTIME(now(), '10:30:14') ),
      (2,2,1,"will send a new bed to you soon",SUBTIME(now(), '9:30:14')),
      (3,1,1,"Got it.",SUBTIME(now(), '8:30:14')),
      (4,2,2,"I need a twin size bed frame.",SUBTIME(now(), '7:30:14')),
      (5,3,3,"How long will it take?",SUBTIME(now(), '6:30:14')),
      (6,3,1,"Your bed frame is delivering.",SUBTIME(now(), '5:30:14')),
      (7,3,1,"You should have received.",SUBTIME(now(), '4:30:14')),
      (8,4,4,"Please give me a office chair.",SUBTIME(now(), '3:30:14')),
      (9,5,3,"emergency",SUBTIME(now(), '2:30:14')),
      (10,5,1,"finished",SUBTIME(now(), '1:30:14'))
;