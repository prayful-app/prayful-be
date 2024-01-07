CREATE TABLE IF NOT EXISTS users
(
    id         SERIAL PRIMARY KEY,
    username   VARCHAR(255) NOT NULL,
    enabled    BOOLEAN      NOT NULL,
    created_at TIMESTAMP    NOT NULL DEFAULT current_timestamp,
    unique (username)
);

CREATE TABLE IF NOT EXISTS user_role
(
    user_id INT          NOT NULL,
    role    VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS pray_request
(
    id           SERIAL PRIMARY KEY,
    requester_id INT           NOT NULL,
    request      VARCHAR(1024) NOT NULL,
    created_at   TIMESTAMP     NOT NULL DEFAULT current_timestamp,
    FOREIGN KEY (requester_id) REFERENCES users (id) ON DELETE CASCADE
);
