INSERT INTO plj.authority (id, name) VALUES (1, 'SYS_ADMIN');
INSERT INTO plj.authority (id, name) VALUES (2, 'FLIGHT_ADMIN');
INSERT INTO plj.authority (id, name) VALUES (3, 'HOTEL_ADMIN');
INSERT INTO plj.authority (id, name) VALUES (4, 'RENT_ADMIN');
INSERT INTO plj.authority (id, name) VALUES (5, 'REGISTERED');

INSERT INTO plj.system_admin (id, email, first_name, last_name, password, username, confirmed, logged_before) VALUES (1, 'admin@gmail.com', 'Adminko', 'Adminic', '$2a$10$hrmNZo48hnMoudARpKUg7.jtFZRYRFIc1e/uShtGX7dmz2M6k/VOW', 'admin', true, true );

INSERT INTO plj.discounts (id, flight_discount, hotel_discount, number_of_flight_reservations, number_of_hotel_reservations, number_ofracreservations, rentacar_discount) VALUES (1, 5, 5, 10, 10, 10, 5);