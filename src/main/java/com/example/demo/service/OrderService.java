package com.example.demo.service;

import com.example.demo.model.dto.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public void processOrder(Order order) {
        validateOrder(order);
        // 在這裡處理訂單，例如儲存到資料庫
    }

    private void validateOrder(Order order) {
        validateNameContainsEnglishCharacters(order.getName());
        validateNameCapitalized(order.getName());
        validatePrice(order.getPrice());
        validateCurrency(order);
    }

    private void validateNameContainsEnglishCharacters(String name) {
        if (!name.matches("[a-zA-Z\\s]+")) {
            throw new IllegalArgumentException("400 - Name contains non-English characters");
        }
    }

    private void validateNameCapitalized(String name) {
        if (!isCapitalized(name)) {
            throw new IllegalArgumentException("400 - Name is not capitalized");
        }
    }

    private boolean isCapitalized(String name) {
        String[] words = name.split("\\s+");
        for (String word : words) {
            if (!Character.isUpperCase(word.charAt(0))) {
                return false;
            }
        }
        return true;
    }

    private void validatePrice(String price) {
        try {
            int numericPrice = Integer.parseInt(price);
            if (numericPrice > 2000) {
                throw new IllegalArgumentException("400 - price is over 2000");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("400 - price format is invalid");
        }
    }

    private void validateCurrency(Order order) {
        String currency = order.getCurrency();
        String price = order.getPrice();

        if (!currency.equals("TWD") && !currency.equals("USD")) {
            throw new IllegalArgumentException("400 - Currency format is wrong");
        }

        if (currency.equals("USD")) {
            try {
                int numericPrice = Integer.parseInt(price);
                int convertedPrice = numericPrice * 31;
                order.setPrice(String.valueOf(convertedPrice));
                order.setCurrency("TWD");
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("400 - price format is invalid");
            }
        }
    }
}
