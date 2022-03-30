CREATE database if not exists craigslist;
use craigslist;

  #	For an existing table, use the ALTER TABLE statement:
	#	ALTER TABLE your_table
	#	ALTER COLUMN date_column SET DEFAULT CURRENT_TIMESTAMP
    
CREATE TABLE IF NOT EXISTS User (
    `Username` VARCHAR(255),
    `FirstName` VARCHAR(40) ,
    `LastName` VARCHAR(40), 
    `DateCreated` DATETIME DEFAULT CURRENT_TIMESTAMP,  
    `PassHash` varchar(1000),
    `isAdmin` tinyint(1) default 0,
    Primary Key (`Username`)
);
ALTER TABLE User
ADD CONSTRAINT CK_Username_Length CHECK (LENGTH(Username) >= 5);
		insert into user (username,firstname,lastname,passhash,isAdmin)
		Values ("Admin","Admin","Admin","b109f3bbbc244eb82441917ed06d618b9008dd09b3befd1b5e07394c706a8bb980b1d7785e5976ec049b46df5f1326af5a2ea6d103fd07c95385ffab0cacbc86",1);
        #for testing       
		insert into user (username,firstname,lastname,passhash)
		Values			
			("user1","Billy","Bob","b109f3bbbc244eb82441917ed06d618b9008dd09b3befd1b5e07394c706a8bb980b1d7785e5976ec049b46df5f1326af5a2ea6d103fd07c95385ffab0cacbc86"),
			("user2","Teddy","Tornado","b109f3bbbc244eb82441917ed06d618b9008dd09b3befd1b5e07394c706a8bb980b1d7785e5976ec049b46df5f1326af5a2ea6d103fd07c95385ffab0cacbc86"),
			("user3","Kasper","Khan","b109f3bbbc244eb82441917ed06d618b9008dd09b3befd1b5e07394c706a8bb980b1d7785e5976ec049b46df5f1326af5a2ea6d103fd07c95385ffab0cacbc86")
		;        

CREATE TABLE IF NOT EXISTS UserSession(   
    `Username` VARCHAR(255),
     `UserSessionID` INT auto_increment primary key,
     `confirmationcode` Int not null,
    `DateLogged` DATETIME DEFAULT CURRENT_TIMESTAMP,    
    foreign key(`Username`) REFERENCES `User`(`Username`)    
);
ALTER TABLE usersession 
AUTO_INCREMENT=10001;
ALTER TABLE usersession 
ADD CONSTRAINT CK_confirmationcode CHECK (confirmationcode >= 100 and confirmationcode <=999);


CREATE TABLE IF NOT EXISTS AdminUserLogs (
    `Unique_ID` INT auto_increment primary key,
    `Username` VARCHAR(255) not null,
    `UserSessionID` Int not null,
    `DateLogged` DATETIME DEFAULT CURRENT_TIMESTAMP,    
    `LogText` VARCHAR(255) not null,
    FOREIGN KEY (`Username`) REFERENCES `User`(Username),
    FOREIGN KEY (`UserSessionID`) REFERENCES `UserSession`(`UserSessionID`)
);


CREATE TABLE IF NOT EXISTS AvailableQueries (
    `QueryID` INT auto_increment primary key,
    `Description` VARCHAR(1000),
    `DateCreated` VARCHAR(40)
);

CREATE TABLE IF NOT EXISTS UserQueryHistory (
    `Unique_ID` INT auto_increment primary key,
    `Username` VARCHAR(255),
    `DateLogged` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `UserSessionID` Int,
    `QueryID` Int not null,
    FOREIGN KEY (`Username`) REFERENCES `User`(Username),
    FOREIGN KEY (`UserSessionID`) REFERENCES `UserSession`(`UserSessionID`),
    FOREIGN KEY (`QueryID`) REFERENCES `AvailableQueries`(`QueryID`)
);