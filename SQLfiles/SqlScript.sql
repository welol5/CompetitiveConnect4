create schema project2;

drop table project2.game_history;
drop table project2.game_state;
drop table project2.person;

create table project2.person(
	id serial primary key,
	username varchar(64) not null,
	pass varchar(64) not null,
	skill_ranking integer not null,
	profile_pic_path varchar(128)
);

create table project2.game_state(
	id serial primary key,
	moveslist varchar(42) not null,
	player_one_id integer references project2.person,
	player_two_id integer references project2.person
);

create table project2.game_history(
	id serial primary key,
	date_played date not null,
	winning_player_id integer references person,
	game_state_id integer references game_state
);