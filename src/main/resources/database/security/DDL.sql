/* for HSQL*/
create table users(
	user_id BIGINT IDENTITY,
  	username varchar(100) not null,
	password varchar(100) not null,
	enabled BOOLEAN not null,
	constraint pk_users primary key (user_id)
);

create table authorities(
	authority_id BIGINT IDENTITY,
  	username varchar(100) not null,
	authority varchar(100) not null,
	constraint pk_authorities primary key (authority_id)
);