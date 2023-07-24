CREATE TABLE Animal(
	id serial PRIMARY KEY,
	name VARCHAR( 20 ) NOT NULL,
	amount int8 NOT NULL,
	count int
);

CREATE CAR(
    id serial PRIMARY KEY,
    model VARCHAR( 50 ) NOT NULL,
    timebuy TIMESTAMP NOT NULL,
    coast int8 NOT NULL,
    number int4 NOT NULL
);