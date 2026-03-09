insert into countries (id, name, continent) values
                                                (1, 'Macedonia', 'Europe'),
                                                (2, 'United Kingdom', 'Europe'),
                                                (3, 'United States', 'North America'),
                                                (4, 'France', 'Europe');

insert into authors (id, created_at, updated_at, name, surname, country_id) values
                                                                                (1, now(), now(), 'George', 'Orwell', 2),
                                                                                (2, now(), now(), 'Victor', 'Hugo', 4),
                                                                                (3, now(), now(), 'Ernest', 'Hemingway', 3),
                                                                                (4, now(), now(), 'Blaze', 'Koneski', 1);

insert into books (id, created_at, updated_at, name, category, author_id, state, available_copies) values
                                                                                                       (1, now(), now(), '1984', 'NOVEL', 1, 'GOOD', 5),
                                                                                                       (2, now(), now(), 'Les Miserables', 'CLASSICS', 2, 'GOOD', 3),
                                                                                                       (3, now(), now(), 'The Old Man and the Sea', 'DRAMA', 3, 'GOOD', 2),
                                                                                                       (4, now(), now(), 'Poetry Collection', 'CLASSICS', 4, 'BAD', 0);