use racing;

set @positionRange_high = 1;
set @positionRange_low =  3;
set @driverName = '*';

Select d.name as 'Driver Name', Sum(Case
 When (r.Position>=@positionRange_high and r.position<=@positionRange_low)
 then 1 else 0
 end
 ) as '# of races ending in position range',
 
 r.placedPercentage as '% or races ending in position range'
 from Results r
 join Driver d on d.id=r.DriverID
 join 