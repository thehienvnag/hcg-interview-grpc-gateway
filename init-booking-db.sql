CREATE TABLE public.hotels (
	id serial NOT NULL,
	name varchar(255) NULL,
	CONSTRAINT hotels_pk PRIMARY KEY (id)
);

CREATE TABLE public.rooms (
    id serial NOT NULL,
    hotel_id int NOT NULL,
    name varchar(255) NULL,
    CONSTRAINT rooms_pk PRIMARY KEY (id),
    CONSTRAINT rooms_hotel_fk FOREIGN KEY (hotel_id) REFERENCES hotels (id)
);

CREATE TABLE public.Bookings (
    id serial NOT NULL,
    room_id integer NOT NULL,
    room_name varchar(255) NOT NULL,
    guest_id integer NOT NULL,
    guest_lastname varchar(255) NOT NULL,
    guest_firstname varchar(255) NOT NULL,
    checkin_time timestamp NOT NULL,
    checkout_time timestamp NOT NULL,
    created_at timestamp,
    active boolean NOT NULL,
    CONSTRAINT bookings_pk PRIMARY KEY (id),
    CONSTRAINT bookings_room_fk FOREIGN KEY (room_id) REFERENCES rooms (id)
);

INSERT INTO public.hotels (name) VALUES ('Hilton');
INSERT INTO public.hotels (name) VALUES ('Marriott');

INSERT INTO public.rooms (hotel_id, name) VALUES (1, 'Hilton - Room 1');
INSERT INTO public.rooms (hotel_id, name) VALUES (1, 'Hilton - Room 2');
INSERT INTO public.rooms (hotel_id, name) VALUES (1, 'Hilton - Room 3');
INSERT INTO public.rooms (hotel_id, name) VALUES (2, 'Marriott - Room 21');
INSERT INTO public.rooms (hotel_id, name) VALUES (2, 'Marriott - 22');
INSERT INTO public.rooms (hotel_id, name) VALUES (2, 'Marriott - 23');
INSERT INTO public.rooms (hotel_id, name) VALUES (2, 'Marriott - 24');

