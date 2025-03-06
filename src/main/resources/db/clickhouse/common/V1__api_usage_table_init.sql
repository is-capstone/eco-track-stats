CREATE TABLE api_usage_item
(
    id UUID DEFAULT generateUUIDv4(),
    endpoint String,
    method Enum8('GET' = 1, 'HEAD' = 2, 'POST' = 3, 'PUT' = 4, 'PATCH' = 5, 'DELETE' = 6, 'OPTIONS' = 7, 'TRACE' = 8),
    at DateTime64(3, 'UTC'),
    usedBy String
)
ENGINE = MergeTree()
PRIMARY KEY id;
