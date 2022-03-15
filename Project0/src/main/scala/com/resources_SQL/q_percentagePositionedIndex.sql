use racing;
set @positionRange_high = 5;
set @positionRange_low =  9;
set @driverName = '*';   
 Select z.name as 'Driver Name', z.sum_Val as '# of races ending in position range ',  
		concat(round((z.sum_Val/Count(s.RaceNumber) * 100 ),2),'%') as '% of races ending in position range' 
	from(Select dd.name, Sum(Case
		 When (rr.Position>=@positionRange_high and rr.position<=@positionRange_low)
		 then 1 else 0
		 end
		 ) as 'sum_Val' from Result rr
		join Driver dd on dd.id=rr.DriverID
		join Schedule ss on ss.RaceNumber=rr.RaceNumber
		group by dd.Name) z
	join Driver d on d.name=z.name
    join Result r on d.ID=r.DriverID
    join Schedule s on s.RaceNumber=r.RaceNumber
    group by d.name
    order by z.sum_Val desc;
