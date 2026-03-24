CREATE OR REPLACE PROCEDURE refresh_books_statistics_mv()
LANGUAGE SQL
AS $$
    REFRESH MATERIALIZED VIEW books_statistics_mv;
$$;