CREATE TABLE cowboy(
	id serial primary key,
	kills integer,
	sheriff boolean,
	dollars money
);

insert into cowboy(kills,sheriff,dollars) values(10, true, 100.0);
update cowboy set dollars = 200.5;
delete from cowboy;
select * from cowboy;