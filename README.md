# crypto-trading-system
A crypto trading system using Spring Boot

1. Price aggregation from the source below

we have a scheduler run per 10s and get Price aggregation from the source below --> save into DB.

![image](https://user-images.githubusercontent.com/6282021/233560504-8d841222-6b13-4ca5-9270-61cd1610f602.png)


2. Create an api to retrieve the latest best aggregated price.
http://localhost:8080/api/price/latest

![image](https://user-images.githubusercontent.com/6282021/233561616-dc0e32ed-66d1-48c7-9df5-0512ccafb75f.png)


example response:
[
{
"tradingPair": "BTCUSDT",
"bestBidPrice": 28041.06,
"bidQty": 0.56,
"bestAskPrice": 28037.89,
"askQty": 0.04,
"bidPriceSourceExchange": "Binance",
"askPriceSourceExchange": "Huobi",
"lastUpdated": "2023-04-21T13:42:13.226123"
},
{
"tradingPair": "ETHUSDT",
"bestBidPrice": 1920.58,
"bidQty": 1.57,
"bestAskPrice": 1920.32,
"askQty": 15.35,
"bidPriceSourceExchange": "Huobi",
"askPriceSourceExchange": "Binance",
"lastUpdated": "2023-04-21T13:42:13.225126"
}
]


3. Create an api which allows users to trade based on the latest best aggregated
price
- example for buy:
POST METHOD: localhost:8080/api/trade/execute

![image](https://user-images.githubusercontent.com/6282021/233561779-d2c0cf01-0dd9-46e8-a1de-4f9b8156ed42.png)

request body: 
{
    "symbol": "ETHUSDT",
    "tradeType": "BUY",
    "quantity": 0.1

}
response example:
{
    "tradeId": 10,
    "result": "success",
    "symbol": "ETHUSDT",
    "tradeType": "BUY",
    "price": 1926.03
}

- example for sell:
POST METHOD: localhost:8080/api/trade/execute
request body: 
{
    "symbol": "ETHUSDT",
    "tradeType": "SELL",
    "quantity": 0.1

}
response example:
{
    "tradeId": 10,
    "result": "success",
    "symbol": "ETHUSDT",
    "tradeType": "SELL",
    "price": 1926.03
}

4. Create an api to retrieve the user’s crypto currencies wallet balance
GET method: http://localhost:8080/api/wallet/balance
response example: 50000.18

![image](https://user-images.githubusercontent.com/6282021/233561961-a1543a20-224b-4fb1-a005-b44815523d86.png)


5. Create an api to retrieve the user trading history.
request GET: http://localhost:8080/api/trade/history

![image](https://user-images.githubusercontent.com/6282021/233561712-d65f01e0-a6c1-4be1-a345-beb1b759f31c.png)


response example: 
[
{
"symbol": "ETHUSDT",
"transactionType": "BUY",
"qty": 0.1,
"price": 1926.76,
"sourceChange": "Binance",
"result": "success",
"timestamp": "2023-04-21T13:35:16.125128"
},
{
"symbol": "ETHUSDT",
"transactionType": "BUY",
"qty": 0.1,
"price": 1926.76,
"sourceChange": "Binance",
"result": "success",
"timestamp": "2023-04-21T13:35:17.160932"
},
{
"symbol": "ETHUSDT",
"transactionType": "BUY",
"qty": 0.1,
"price": 1926.03,
"sourceChange": "Huobi",
"result": "success",
"timestamp": "2023-04-21T13:35:18.49414"
},
{
"symbol": "ETHUSDT",
"transactionType": "BUY",
"qty": 0.1,
"price": 1926.03,
"sourceChange": "Huobi",
"result": "success",
"timestamp": "2023-04-21T13:35:19.127355"
},
{
"symbol": "ETHUSDT",
"transactionType": "BUY",
"qty": 0.1,
"price": 1926.03,
"sourceChange": "Huobi",
"result": "success",
"timestamp": "2023-04-21T13:35:20.006803"
}
]




