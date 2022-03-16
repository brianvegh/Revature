use racing;

Set @Position= 	;							#set position (Int)
Set @Finished= 	;							#set finished 1=true, 2=false
Set @RaceNumber= 	;							#set RaceNumber (Int)
Set @DriverNameVar= 	;			#set @DriverNameVar (VarChar)
Set @CarNumberVar=	;						#set @CarNumberVar (Int)
INSERT INTO Result (RaceNumber,DriverID,Position, Finished)
Select @RaceNumber, d.id, @Position, @Finished
From Driver d
Where d.Name = @DriverNameVar
	and d.CarNumber = @CarNumberVar;


###return newly added rows, formatted
Set @newRowCount=				;				#Set @newRowCount (Int)
Prepare stmt From 
"
Select r.resultID_Key as 'Unique ID',r.raceNumber as 'Race Number', d.Name as 'Driver Name', r.position as 'Position', IF(r.Finished=1,'Yes','No') as 'Finished Race'
	From Result r
	Inner Join Driver d on r.DriverID = d.ID
	Order By r.resultID_Key Desc limit ?
";
Execute stmt using @newRowCount;

select * from result








