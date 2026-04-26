CREATE TABLE book_history (
                              id BIGSERIAL PRIMARY KEY,
                              book_id BIGINT NOT NULL UNIQUE
);

CREATE TABLE book_history_descriptions (
                                           book_history_id BIGINT NOT NULL,
                                           description TEXT NOT NULL,
                                           FOREIGN KEY (book_history_id) REFERENCES book_history(id) ON DELETE CASCADE
);