alter table books
    add column deleted boolean not null default false;