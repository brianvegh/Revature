use craigslist;

set @username='user1';
insert into usersession (username) values (@username);

select * from usersession