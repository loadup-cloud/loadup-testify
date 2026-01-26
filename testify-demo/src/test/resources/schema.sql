-- Database schema for Testify demo

CREATE TABLE IF NOT EXISTS users
(
    user_id
    VARCHAR
(
    100
) PRIMARY KEY,
    user_name VARCHAR
(
    200
) NOT NULL,
    email VARCHAR
(
    200
) NULL,
    status VARCHAR
(
    50
) NULL DEFAULT 'ACTIVE',
    created_at BIGINT NULL,
    updated_at BIGINT
    );

