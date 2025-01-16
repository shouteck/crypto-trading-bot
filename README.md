# Crypto Trading API

### 1. Get Aggregated Prices

- **URL**: `/api/aggregated-tickers`
- **Method**: `GET`
- **Response Body**:
    ```json
    [
        {
            "symbol": "BTCUSDT",
            "bidPrice": 99405.05,
            "bidQuantity": 0.90554,
            "askPrice": 99410.01,
            "askQuantity": 1.13483
        },
        {
            "symbol": "ETHUSDT",
            "bidPrice": 3369.39,
            "bidQuantity": 86.283,
            "askPrice": 3369.74,
            "askQuantity": 1.9235
        }
    ]

### 2. Create a User

- **URL**: `/api/users`
- **Method**: `POST`
- **Request Body**:
    ```json
    {
        "username": "john_doe"
    }
- **Response Body**:
    ```json
    User created.

### 3. Check User Wallet

- **URL**: `/api/wallet/balance?username=john_doe`
- **Method**: `GET`
- **Response Body**:
    ```json
    {
      "cashBalance": 49484.9893,
      "walletItems": [
        {
          "symbol": "BTCUSDT",
          "quantity": 0.005
        },
        {
          "symbol": "ETHUSDT",
          "quantity": 0.005
        }
      ]
    }

### 4. Adjust Cash Balance

- **URL**: `/api/wallet/adjust`
- **Method**: `POST`
- **Request Body**:
    ```json
    {
        "username": "john_doe",
        "amount": 1000000.00
    }
- **Response Body**:
    ```json
    Wallet balance adjusted successfully.

### 5. Check User Trading History

- **URL**: `/api/trade/history?username=john_doe`
- **Method**: `GET`
- **Response Body**:
    ```json
    [
        {
            "symbol": "BTCUSDT",
            "quantity": 0.005,
            "price": 99554.04,
            "tradeTimestamp": "2025-01-16T13:23:38.901831",
            "buyOrder": true
        },
        {
            "symbol": "BTCUSDT",
            "quantity": 0.005,
            "price": 99554.04,
            "tradeTimestamp": "2025-01-16T13:23:47.481145",
            "buyOrder": true
        }
    ]

### 6. Buy Trade

- **URL**: `/api/trade/execute`
- **Method**: `POST`
- **Request Body**:
    ```json
    {
        "username": "john_doe",
        "symbol": "ETHUSDT",
        "quantity": 0.005,
        "isBuyOrder": true
    }
- **Response Body**:
    ```json
    Trade executed successfully.

### 7. Sell Trade

- **URL**: `/api/trade/execute`
- **Method**: `POST`
- **Request Body**:
    ```json
    {
        "username": "john_doe",
        "symbol": "ETHUSDT",
        "quantity": 0.005,
        "isBuyOrder": false
    }
- **Response Body**:
    ```json
    Trade executed successfully.