use racing;
set @r=0;	
SELECT @r := @r+1 as 'Season Rank', 
z.* 
from(
select d.Name, Sum(p.pointValue) as 'Total Points'
from Driver d 
inner join Result r
inner join Points p
on d.ID=r.DriverID    
and r.Position = p.Position
group by d.name
order by Sum(p.pointValue) desc
)z,
(Select @r:=0)y;
    









