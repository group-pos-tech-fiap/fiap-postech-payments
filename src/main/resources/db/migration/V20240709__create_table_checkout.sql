
CREATE TABLE IF NOT EXISTS checkout (
  id SERIAL PRIMARY KEY,
  order_id VARCHAR(255) NOT NULL,
  value DECIMAL(10,2) NOT NULL,
  status VARCHAR(255) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_checkout_order_id ON checkout (order_id);
