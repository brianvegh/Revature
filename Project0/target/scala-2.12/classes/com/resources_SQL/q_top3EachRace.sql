use racing;

SELECT r.RaceNumber as "Race Number" ,d.Name as "Driver", r.Position as "Position", t.Name as "Track Name"
From Driver as d
join Result as r on d.ID=r.DriverID
join Schedule as s on r.RaceNumber=s.RaceNumber
join Track as t on s.TrackID=t.ID
Where r.Position=1 or r.position=2 or r.position=3
order by r.RaceNumber, r.position;