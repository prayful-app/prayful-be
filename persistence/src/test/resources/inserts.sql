INSERT INTO users(id, username, enabled, created_at)
VALUES (1, 'user', true, current_timestamp);
INSERT INTO users(id, username, enabled, created_at)
VALUES (2, 'admin', true, current_timestamp);

ALTER SEQUENCE users_id_seq RESTART WITH 100;