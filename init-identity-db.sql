CREATE TABLE public.users (
	id serial NOT NULL,
	email varchar(255) NOT NULL,
	first_name varchar(255) NULL,
	last_name varchar(255) NULL,
	"password" varchar(255) NOT NULL,
	create_at timestamp NULL,
	CONSTRAINT email_unique UNIQUE (email),
	CONSTRAINT users_pk PRIMARY KEY (id)
);