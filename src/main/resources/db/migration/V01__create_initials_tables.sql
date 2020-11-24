CREATE TABLE accounts (
	account_id SERIAL PRIMARY KEY,
	document_number VARCHAR(11) NOT NULL
);

CREATE TABLE operations_types (
	operation_type_id INTEGER PRIMARY KEY,
	description VARCHAR(20)
);

INSERT INTO operations_types (operation_type_id, description) VALUES (1, 'COMPRA A VISTA');
INSERT INTO operations_types (operation_type_id, description) VALUES (2, 'COMPRA PARCELADA');
INSERT INTO operations_types (operation_type_id, description) VALUES (3, 'SAQUE');
INSERT INTO operations_types (operation_type_id, description) VALUES (4, 'PAGAMENTO');

CREATE TABLE transactions (
	transaction_id SERIAL PRIMARY KEY,
	account_id INTEGER,
	operation_type_id INTEGER,
	amount NUMERIC,
	event_date TIMESTAMP,
	FOREIGN KEY (account_id) REFERENCES accounts(account_id),
	FOREIGN KEY (operation_type_id) REFERENCES operations_types(operation_type_id)
);