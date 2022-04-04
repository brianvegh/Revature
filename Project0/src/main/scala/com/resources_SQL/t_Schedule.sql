CREATE TABLE IF NOT EXISTS `Schedule` (
    `RaceNumber` INT auto_increment primary key,
    `TrackID` INT,
    `Date` DATE,
    FOREIGN KEY (`TrackID`) REFERENCES `Track`(ID)    
);

ALTER TABLE `Schedule` CHANGE `yourfield` `yourfield` DATE;

ALTER TABLE `Schedule` MODIFY `Date` DATE;


INSERT INTO Schedule VALUES	
    (1,1,'2021-04-02'),
    (2,2,'2021-04-16'),
    (3,3,'2021-04-30'),
    (4,4,'2021-05-07'),
    (5,5,'2021-05-21');
   
INSERT INTO Schedule VALUES   
    (6,1,'2021-06-04');
    
    INSERT INTO Schedule (TrackID,`Date`) Values ((Select t.id From Track t  Where t.Name like '%$trackName%'),'$sqlDate');
    
    
    Select t.id From Track t  Where t.Name like '$trackName';
     Select t.id From Track t  Where t.Name like '%motor%';
