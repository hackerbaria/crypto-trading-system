INSERT INTO crypto_currency (symbol, `name`) VALUES
('BTCUSDT', 'Bitcoin'),('ETHUSDT', 'Ethereum');

INSERT INTO wallet (crypto_currency_id, qty) VALUES
(1, 0),
(2, 0);

INSERT INTO users (`name`, balance) VALUES
('admin', 50000);

COMMIT;





