CREATE TABLE transactions (
    id BIGSERIAL PRIMARY KEY,
    public_id UUID NOT NULL,
    transaction_type VARCHAR(100) NOT NULL,
    payment_method   VARCHAR(100) NOT NULL,
    amount           NUMERIC(12, 2) NOT NULL,
    description      VARCHAR(255),
    transaction_date DATE NOT NULL,
    user_id UUID NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,

    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);