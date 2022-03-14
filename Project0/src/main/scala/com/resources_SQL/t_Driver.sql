CREATE TABLE IF NOT EXISTS Driver (
    `ID` INT auto_increment primary key,
    `Name` VARCHAR(255) CHARACTER SET utf8,
    `CarNumber` INT,
    `CarMake` VARCHAR(40) CHARACTER SET utf8,
    `CarModel` VARCHAR(40) CHARACTER SET utf8,
    `CarYear` INT,
    `CarCountry` VARCHAR(40) CHARACTER SET utf8
    Constraint Chk_CarYear Check ('CarYear' >=1920 And 'CarYear' <=YEAR(curdate()))
);
INSERT INTO Driver VALUES
    (1,'Brian Vegh',256,'BMW','M3',1999,'Germany'),
    (2,'Mark Vegh',23,'Oldsmobile','Cutlass',1983,'America'),
    (3,'Crazy Kenny Thomas',51,'Pontiac','Gran Prix',1982,'America'),
    (4,'Dale Earnheart',3,'Chevrolet','Monte Carlo',1980,'America'),
    (5,'Ricky Bobby',26,'Chevrolet','Malibu',1969,'America'),
    (6,'Jerry Seinfeld',9,'Porsche','959',1983,'Germany'),
    (7,'The Bandit',00,'Pontiac','Friebird Trans Am',1977,'America'),
    (8,'Ken Block',43,'Subaru','WRX STI',2008,'Japan'),
    (9,'Paul Newman',82,'Nissan','280ZX',1979,'Japan');
