create table book_activity (
                               id bigserial primary key,
                               book_name varchar(255) not null,
                               event_time timestamp not null,
                               event_type varchar(100) not null,
                               message text
);