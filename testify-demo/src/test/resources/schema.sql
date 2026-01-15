-- Database schema for Testify demo

CREATE TABLE IF NOT EXISTS users (
    user_id VARCHAR(100) PRIMARY KEY,
    user_name VARCHAR(200) NOT NULL,
    email VARCHAR(200) NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'ACTIVE',
    created_at BIGINT NOT NULL,
    updated_at BIGINT
);

CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_users_status ON users(status);
