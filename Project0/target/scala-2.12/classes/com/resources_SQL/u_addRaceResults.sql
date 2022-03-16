use racing;

Set @Position= 20	;							#set position (Int)
Set @Finished= 	1	;							#set finished 1=true, 2=false
Set @RaceNumber= 5	;							#set RaceNumber (Int)
Set @DriverNameVar= 'Brian Vegh'	;			#set @DriverNameVar (VarChar)
Set @CarNumberVar=256	;						#set @CarNumberVar (Int)
INSERT INTO Result (RaceNumber,DriverID,Position, Finished)
Select @RaceNumber, d.id, @Position, @Finished
From Driver d
Where d.Name = @DriverNameVar
	and d.CarNumber = @CarNumberVar;


###return newly added rows, formatted
Set @newRowCount=1				;				#Set @newRowCount (Int)
Prepare stmt From 'Select r.raceNumber, d.Name, r.position, r.Finished 
	From Result r
	Inner Join Driver d on r.DriverID = d.ID
	Order By r.resultID_Key Desc limit ?';
Execute stmt using @newRowCount;

		
