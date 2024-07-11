SOLID 
1.單一職責原則
OrderController 負責處理 HTTP 請求並將其轉發給 OrderService。
OrderService 負責處理訂單的檢核和處理。
2.開放封閉原則
OrderService 中的 validateOrder 方法，可以通過添加新的檢核方法來擴展訂單檢查邏輯，而不必改變現有的檢查過程。

