CREATE TABLE IF NOT EXISTS Track (
    `ID` INT auto_increment primary key,
    `Name` VARCHAR(50) CHARACTER SET utf8,
    `City` VARCHAR(50) CHARACTER SET utf8,
    `State` VARCHAR(2) CHARACTER SET utf8,
    `Length` NUMERIC(3, 2),
    `Turns` INT
);
INSERT INTO Track VALUES
    (1,'Summit Point Motorsports Park','Summit Point','WV',2.0,10),
    (2,'Watkins Glenn International','Watkins Glen','NY',3.45,11),
    (3,'Road Atlanta','Braselton','GA',2.54,12),
    (4,'Lime Rock Park','Lakeville','CT',1.53,7),
    (5,'Brainerd International Raceway','Braselton','MN',2.50,13);
