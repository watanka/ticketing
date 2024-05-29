INSERT INTO concert (concert_id, name, created_at, updated_at) VALUES (1, '아일릿 데뷔 콘서트', now(), now());
INSERT INTO concert (concert_id, name, created_at, updated_at) VALUES (2, '나훈아 50주년 콘서트', now(), now());

INSERT INTO  concert_time (concert_time_id, concert_id, time, created_at, updated_at) VALUES (1, 1, '2024-05-20T18:30:00Z', now(), now());
INSERT INTO  concert_time (concert_time_id, concert_id, time, created_at, updated_at) VALUES (2, 2, '2024-05-20T18:30:00Z', now(), now());

INSERT INTO seat (seat_num, concert_time_id, price, status, created_at, updated_at, version) VALUES (1, 1, 500000, 'AVAILABLE', now(), now(), 1);
INSERT INTO seat (seat_num, concert_time_id, price, status, created_at, updated_at, version) VALUES (2, 1, 300000, 'AVAILABLE', now(), now(), 1);
INSERT INTO seat (seat_num, concert_time_id, price, status, created_at, updated_at, version) VALUES (3, 1, 200000, 'RESERVED', now(), now(), 1);
INSERT INTO seat (seat_num, concert_time_id, price, status, created_at, updated_at, version) VALUES (4, 2, 500000, 'RESERVED', now(), now(), 1);
INSERT INTO seat (seat_num, concert_time_id, price, status, created_at, updated_at, version) VALUES (5, 2, 300000, 'RESERVED', now(), now(), 1);
INSERT INTO seat (seat_num, concert_time_id, price, status, created_at, updated_at, version) VALUES (6, 2, 200000, 'RESERVED', now(), now(), 1);

INSERT INTO users (balance, created_at, updated_at) VALUES (0, now(), now());
INSERT INTO users (balance, created_at, updated_at) VALUES (0, now(), now());
