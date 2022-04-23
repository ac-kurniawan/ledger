CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE SCHEMA IF NOT EXISTS "ledger"

CREATE TABLE ledger.ledgers (
  ledger_id UUID NOT NULL,
   name VARCHAR(255) NOT NULL,
   type VARCHAR(255) NOT NULL,
   status VARCHAR(255) NOT NULL,
   active_balance DECIMAL,
   user_id VARCHAR(255),
   created_at TIMESTAMP WITHOUT TIME ZONE,
   updated_at TIMESTAMP WITHOUT TIME ZONE,
   deleted_at TIMESTAMP WITHOUT TIME ZONE,
   CONSTRAINT pk_ledgers PRIMARY KEY (ledger_id)
);

ALTER TABLE ledger.ledgers ADD CONSTRAINT uc_ledgers_name UNIQUE (name);

CREATE TABLE ledger.transactions (
  transaction_id UUID NOT NULL,
   transaction_code VARCHAR(255),
   type VARCHAR(255) NOT NULL,
   amount DECIMAL NOT NULL,
   transaction_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   ledger_id UUID NOT NULL,
   CONSTRAINT pk_transactions PRIMARY KEY (transaction_id)
);

ALTER TABLE ledger.transactions ADD CONSTRAINT FK_TRANSACTIONS_ON_LEDGER FOREIGN KEY (ledger_id) REFERENCES ledger.ledgers (ledger_id);