INSERT INTO concert (name, created_at, updated_at) VALUES ('NewJeans Concert', now(), now());
INSERT INTO concert (name, created_at, updated_at) VALUES ('NaHunAh The Legend', now(), now());

INSERT INTO  concert_time (concert_id, concert_time, created_at, updated_at) VALUES (1, '2024-06-02T13:16:01+03:00', now(), now());
INSERT INTO  concert_time (concert_id, concert_time, created_at, updated_at) VALUES (2, '2024-06-02T13:16:01+03:00', now(), now());

INSERT INTO seat (seat_num, concert_time_id, price, status, created_at, updated_at) VALUES (1, 1, 500000, 'AVAILABLE', now(), now());
INSERT INTO seat (seat_num, concert_time_id, price, status, created_at, updated_at) VALUES (2, 1, 300000, 'AVAILABLE', now(), now());
INSERT INTO seat (seat_num, concert_time_id, price, status, created_at, updated_at) VALUES (3, 1, 200000, 'RESERVED', now(), now());
INSERT INTO seat (seat_num, concert_time_id, price, status, created_at, updated_at) VALUES (4, 2, 500000, 'RESERVED', now(), now());
INSERT INTO seat (seat_num, concert_time_id, price, status, created_at, updated_at) VALUES (5, 2, 300000, 'RESERVED', now(), now());
INSERT INTO seat (seat_num, concert_time_id, price, status, created_at, updated_at) VALUES (6, 2, 200000, 'RESERVED', now(), now());

INSERT INTO users (balance, created_at, updated_at) VALUES (0, now(), now());
INSERT INTO users (balance, created_at, updated_at) VALUES (0, now(), now());
