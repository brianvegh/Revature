use racing;


Select d.Name, SUM(CASE
		When  r.Finished=1 THEN 1 
		Else 0
		End) AS FinishedRaces,
        SUM(CASE
		When  r.Finished=0 THEN 1 
		Else 0
		End) AS UnfinishedRaces
From Result r
Inner Join Driver d on r.DriverID = d.ID
Group by d.Name 
    
