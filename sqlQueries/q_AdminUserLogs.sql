CREATE TABLE IF NOT EXISTS AdminUserLogs (
    `Unique_ID` INT auto_increment primary key,
    `Username` VARCHAR(255) not null,
    `UserSessionID` Int not null,
    `DateLogged` DATETIME DEFAULT CURRENT_TIMESTAMP,    
    `LogText` VARCHAR(255) not null,
    FOREIGN KEY (`Username`) REFERENCES `User`(Username),
    FOREIGN KEY (`UserSessionID`) REFERENCES `UserSession`(`UserSessionID`)
);


set @username='user1';
set @userSessionId=10001;
set @logText="test logtext";
insert into adminuserlogs (username,usersessionID,logtext) values (@username,@userSessionID,@logText);


select * from AdminUserLogs