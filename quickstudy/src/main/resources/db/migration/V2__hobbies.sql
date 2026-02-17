CREATE TABLE hobbies (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE hobbies_users (
    hobby_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    PRIMARY KEY (hobby_id, user_id),
    FOREIGN KEY (hobby_id) REFERENCES hobbies(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);