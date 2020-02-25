package com.stock;

import java.util.Map;

public class Main {
    private static StockList stockList = new StockList();
    public static void main(String[] args) {

	StockItem item = new StockItem("bread",0.89,1);
    stockList.addStock(item);
    item = new StockItem("cake",9.0,484);
    stockList.addStock(item);

        System.out.println(stockList);

        for (String s: stockList.items().keySet()){
            System.out.println(s);
        }
        Basket basket = new Basket("bread");
        sellItem(basket,"bread",1);
        System.out.println(basket);
        sellItem(basket,"bread",1);
        System.out.println(basket);
        sellItem(basket,"banana",9);

        System.out.println(stockList);
        for (Map.Entry<String, Double> prices: stockList.priceList().entrySet()){
            System.out.println(prices.getKey() + "cost" + prices.getValue());
        }
    }

    public static int sellItem(Basket basket, String item, int quantity){
        StockItem stockItem = stockList.get(item);
        if (stockItem == null){
            System.out.println("we don't sell" + item);
            return 0;
        }
        if (stockList.reserveStock(item,quantity) != 0){
            return basket.addToBasket(stockItem, quantity);
        }
        return 0;
    }

    public static int removeItem(Basket basket, String item, int quantity){
        StockItem stockItem = stockList.get(item);
        if (stockItem == null){
            System.out.println("we don't sell" + item);
            return 0;
        }
        if (basket.removeFromBasket(stockItem,quantity) == quantity){
            return stockList.unReserveStock(item, quantity);
        }
        return 0;
    }

    public static void checkout(Basket basket){
        for (Map.Entry<StockItem, Integer> item: basket.items().entrySet()){
            stockList.sellStock(item.getKey().getName(), item.getValue());
        }
        basket.clearBasket();
    }
}
