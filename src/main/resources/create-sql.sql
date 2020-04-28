DROP TABLE nasdaq_totalview IF EXISTS;

CREATE TABLE nasdaq_totalview  (
    record_id IDENTITY NOT NULL PRIMARY KEY,
    soup_partition INTEGER,
    soup_sequence INTEGER,
    msg_type LONGVARCHAR,
    symbol_locate INTEGER,
    unique_timestamp INTEGER,
    order_id INTEGER,
    side LONGVARCHAR,
    quantity INTEGER,
    mpid LONGVARCHAR,
    symbol LONGVARCHAR,
    price INTEGER
);