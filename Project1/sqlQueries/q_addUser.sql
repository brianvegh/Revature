use craigslist;

# return all current usernames
Select username from User u;

Select username, passhash from User u where u.username='user1';

#return passhash for specific username
Select passhash from User u where u.username=@username

#then, after checking username unique,

Set@username=	;
Set@firstname=	;
Set@lastname=	;
Set@passhashe=	;
insert into user (username,firstname,lastname,passhash)
Select @username,@firstname,@lastname,@passHash;

#add test users
insert into user (username,firstname,lastname,passhash)
Values
	("user1","Billy","Bob","b109f3bbbc244eb82441917ed06d618b9008dd09b3befd1b5e07394c706a8bb980b1d7785e5976ec049b46df5f1326af5a2ea6d103fd07c95385ffab0cacbc86"),
    ("user2","Teddy","Tornado","b109f3bbbc244eb82441917ed06d618b9008dd09b3befd1b5e07394c706a8bb980b1d7785e5976ec049b46df5f1326af5a2ea6d103fd07c95385ffab0cacbc86"),
    ("user3","Kasper","Khan","b109f3bbbc244eb82441917ed06d618b9008dd09b3befd1b5e07394c706a8bb980b1d7785e5976ec049b46df5f1326af5a2ea6d103fd07c95385ffab0cacbc86")
;
