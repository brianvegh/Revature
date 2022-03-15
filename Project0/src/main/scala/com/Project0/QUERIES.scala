package com.Project0

protected object QUERIES_SQL {
  //1
  val roster_Q="Select  Name, CarNumber as 'Number', CarMake as 'Vehicle Make', CarModel as 'Vehicle Model' ,CarYear as 'Car Year'from Driver\norder by CarYear;"
  //2
  val raceWinners_Q: String ="SELECT r.RaceNumber as \"Race Number\" ,d.Name as \"Winner\", t.Name as \"Track Name\"\nFrom Driver as d\njoin Result as r on d.ID=r.DriverID" +
  "\njoin Schedule as s on r.RaceNumber=s.RaceNumber\njoin Track as t on s.TrackID=t.ID\nWhere r.Position=1;"
  //3
  val seasonRank_Q: String ="set @r=0;\t\nSELECT @r := @r+1 as 'Season Rank', \nz.* \nfrom(\nselect d.Name, Sum(p.pointValue) as 'Total Points'\nfrom Driver d " +
  "\ninner join Result r\ninner join Points p\non d.ID=r.DriverID    \nand r.Position = p.Position\ngroup by d.name\norder by Sum(p.pointValue) desc\n)z,\n(Select @r:=0)y;"
  //4
  val top3EachRace_Q: String ="SELECT r.RaceNumber as \"Race Number\" ,d.Name as \"Driver\", r.Position as \"Position\", t.Name as " +
  "\"Track Name\"\nFrom Driver as d\njoin Result as r on d.ID=r.DriverID\njoin Schedule as s on r.RaceNumber=s.RaceNumber" +
  "\njoin Track as t on s.TrackID=t.ID\nWhere r.Position=1 or r.position=2 or r.position=3\norder by r.RaceNumber, r.position;"
  //5
  val percentagePositionedIndex_Q: (Int,Int) => String = (high:Int,low:Int)=> {
      " Select z.name as 'Driver Name', z.sum_Val as '# of races ending in position range ', " +
        " \n\t\tconcat(round((z.sum_Val/Count(s.RaceNumber) * 100 ),2),'%') as '% of races ending in position range' " +
        s"\n\tfrom(Select dd.name, Sum(Case\n\t\t When (rr.Position>=$high and rr.position<=$low)" +
        "\n\t\t then 1 else 0\n\t\t end\n\t\t ) as 'sum_Val' from Result rr\n\t\tjoin Driver dd on dd.id=rr.DriverID" +
        "\n\t\tjoin Schedule ss on ss.RaceNumber=rr.RaceNumber\n\t\tgroup by dd.Name) z\n\tjoin Driver d on d.name=z.name\n" +
        "join Result r on d.ID=r.DriverID\n    join Schedule s on s.RaceNumber=r.RaceNumber\n    group by d.name\n    order by z.sum_Val desc;"
  }
  //6
  val finishedRaces_Q: String ="Select d.Name, SUM(CASE\n\t\tWhen  r.Finished=1 THEN 1 \n\t\tElse 0\n\t\tEnd) AS 'Finished Races',\n" +
    "SUM(CASE\n\t\tWhen  r.Finished=0 THEN 1 \n\t\tElse 0\n\t\tEnd) AS 'Unfinished Races'\nFrom Result r\nInner Join Driver d on r.DriverID = d.ID\nGroup by d.Name "
  //7
  val allDriverRacePositions_Q: String ="SELECT r.RaceNumber as \"Race Number\",t.Name as \"Track Name\",r.Position as \"Position\",d.Name as \"Driver\"\nFrom Driver as d" +
    "\njoin Result as r on d.ID=r.DriverID\njoin Schedule as s on r.RaceNumber=s.RaceNumber\njoin Track as t on s.TrackID=t.ID\norder by r.RaceNumber, r.position;"
  //8
  val inputedDriverRacePosition_Q:(String)=>String=(s:String)=>{
    s"SELECT r.RaceNumber as 'Race Number' , t.Name as 'Track Name',r.Position as 'Position',d.Name as 'Driver'" +
      s"\nFrom Driver as d\njoin Result as r on d.ID=r.DriverID\njoin Schedule as s on r.RaceNumber=s.RaceNumber" +
      s"\njoin Track as t on s.TrackID=t.ID\nWhere d.Name like Concat('%','$s','%')\norder by d.Name, r.RaceNumber, r.position;"
  }
  //Select All from Table to Print Raw Table
  val rawTable:(String)=>String =(s:String)=>{
    s"Select * from $s"
  }


}



