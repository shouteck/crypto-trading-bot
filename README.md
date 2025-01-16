# Crypto Trading API

## Functionalities
#### 1. Create a User

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

#### 2. Check User Wallet

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

#### 3. Adjust Cash Balance

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

#### 4. Check User Trading History

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

#### 5. Buy Trade

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

#### 6. Sell Trade

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