use racing;
Set @name='bandit';

SELECT r.RaceNumber as 'Race Number' , t.Name as 'Track Name',r.Position as 'Position',d.Name as 'Driver'
From Driver as d
join Result as r on d.ID=r.DriverID
join Schedule as s on r.RaceNumber=s.RaceNumber
join Track as t on s.TrackID=t.ID
Where d.Name like Concat('%',@name,'%')
order by d.Name, r.RaceNumber, r.position;
