CREATE TABLE TICKER (
    symbol VARCHAR(255) NOT NULL,
    service VARCHAR(255) NOT NULL,
    bidPrice DECIMAL(20, 8),
    bidQuantity DECIMAL(20, 8),
    askPrice DECIMAL(20, 8),
    askQuantity DECIMAL(20, 8),
    PRIMARY KEY (symbol, service)
);
