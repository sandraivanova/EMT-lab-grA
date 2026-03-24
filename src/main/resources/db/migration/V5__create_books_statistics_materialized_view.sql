create materialized view books_statistics_mv as
select
    b.category as category,
    count(b.id) as total_books,
    sum(b.available_copies) as total_available_copies,
    count(case when b.state <> 'GOOD' then 1 end) as books_not_in_good_condition
from books b
where b.deleted = false
group by b.category;
