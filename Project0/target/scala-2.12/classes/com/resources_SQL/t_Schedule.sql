CREATE TABLE IF NOT EXISTS `Schedule` (
    `RaceNumber` INT auto_increment primary key,
    `TrackID` INT,
    `Date` DATETIME,
    FOREIGN KEY (`TrackID`) REFERENCES `Track`(ID)    
);
INSERT INTO Schedule VALUES
    (1,1,'2021-04-02 00:00:00'),
    (2,2,'2021-04-16 00:00:00'),
    (3,3,'2021-04-30 00:00:00'),
    (4,4,'2021-05-07 00:00:00'),
    (5,5,'2021-05-21 00:00:00');
