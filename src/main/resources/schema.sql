create table account (
		customer_id bigint not null,		
		first_name varchar(255),
		last_name varchar(255),
		card_number varchar(255),
		account_number bigint,
		pin integer,
		balance double,
		primary key (customer_id)
);

