CREATE TYPE role_type AS ENUM ('admin', 'user', 'worker');
CREATE TYPE item_type AS ENUM ('game', 'program', 'helicopter');

CREATE TABLE category (
	id serial primary key,
	category_name varchar(255)
);

CREATE TABLE state (
	id serial primary key,
	is_closed boolean
);

CREATE TABLE role (
	id serial primary key,
	user_role role_type
);

create table service_user (
	id serial primary key,
	name varchar(255),
	role_id int references role(id)
);

CREATE TABLE rules (
	id serial primary key,
	rule varchar(255)
);

Create Table role_rule (
	id serial primary key,
	role_id int references role(id),
	rule_id int references rules(id)
);

CREATE TABLE item (
	id serial primary key,
	name item_type,
	user_id int references service_user(id),
	category_id int references category(id),
	state_id int references state(id)
);

CREATE TABLE comments (
	id serial primary key,
	comment varchar(255),
	item_id int references item(id)
);

CREATE TABLE attachs (
	id serial primary key,
	item_id int references item(id)
);