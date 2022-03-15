use racing;


Insert Into Result (RaceNumber, DriverID,Position,Finished)
	Select t.Name, 


INSERT INTO [dbo].[InsertDemo] (STD_Guid, StName ,StBirthDate,StParentsPhone)
SELECT TOP (10) P.[rowguid], P.[FirstName] + P.[MiddleName] + P.[LastName]  AS StName, P.[ModifiedDate], PP.PhoneNumber
FROM [Person].[Person] P
JOIN [Person].[PersonPhone] PP
ON P.BusinessEntityID=PP.BusinessEntityID
WHERE P.[FirstName] + P.[MiddleName] + P.[LastName] IS NOT NULL and PP.PhoneNumber IS NOT NULL


INSERT INTO first_table_name [(column1, column2, ... columnN)] 
   SELECT column1, column2, ...columnN 
   FROM second_table_name
   [WHERE condition];