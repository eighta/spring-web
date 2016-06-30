/* for HSQL*/
create table books(
	book_id BIGINT IDENTITY,
  	book_name varchar(100) not null,
	book_author varchar(100) not null,
	constraint pk_books primary key (book_id)
);

/* for H2 DATABASE
create table books(
	book_id bigint auto_increment,
  	book_name varchar(100) not null,
	book_author varchar(100) not null,
	constraint pk_books primary key (book_id)
);
*/