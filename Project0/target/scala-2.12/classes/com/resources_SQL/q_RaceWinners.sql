Create database racing;
use racing;
show databases;

SELECT Cars.CarNumber, Cars.CarYear, Drivers.DriverName
FROM Cars
Inner Join Drivers ON Drivers.DriverCarNumber = Cars.CarNumber;

SELECT Cars.CarMake, Cars.CarModel, Cars.CarYear, Drivers.DriverName
FROM Drivers
Left Join Cars On Drivers.DriverCarNumber = Cars.CarNumber
and Cars.CarCountry='America';

SELECT rs.RaceNumber as "Race Number" ,d.DriverName as "Winner", rs.TrackName as "Track Name"
From Drivers as d
join RaceResults as rr on d.DriverID=rr.DriverID
join RaceSeason as rs on rr.RaceNumber=rs.RaceNumber
Where rr.DriverPosition=1;




