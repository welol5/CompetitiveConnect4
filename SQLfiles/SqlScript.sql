--create schema cc4;

drop table if exists game_history;
drop table if exists game_state;
drop table if exists person;

create table person(
	id serial primary key,
	username varchar(64) unique not null,
	pass varchar(64) not null,
	skill_ranking integer not null,
	profile_pic_path varchar(128)
);

create table game_state(
	id serial primary key,
	moveslist varchar(42) not null,
	player_one_id integer references person,
	player_two_id integer references person
);

create table game_history(
	id serial primary key,
	date_played timestamp not null,
	winning_player_id integer references person,
	game_state_id integer references game_state
);