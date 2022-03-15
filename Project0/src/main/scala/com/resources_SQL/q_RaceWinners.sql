use racing;

SELECT r.RaceNumber as "Race Number" ,d.Name as "Winner", t.Name as "Track Name"
From Driver as d
join Result as r on d.ID=r.DriverID
join Schedule as s on r.RaceNumber=s.RaceNumber
join Track as t on s.TrackID=t.ID
Where r.Position=1;




