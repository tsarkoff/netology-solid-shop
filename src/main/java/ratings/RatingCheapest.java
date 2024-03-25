package ratings;

import products.Product;
import sales.Order;

import java.util.List;

public class RatingCheapest implements Rating<Order> {

    @Override
    public Product getBestProduct(List<Order> orders, RatingType ratingType) {
        float cheapestPrice = Float.MAX_VALUE;
        Product cheapestProduct = null;
        if (ratingType == RatingType.CHEAPEST_BY_ORDERS && orders != null) {
            for (Order order : orders) {
                for (Product product : order.getProducts()) {
                    float currentPrice = product.getPrice();
                    if (currentPrice < cheapestPrice) {
                        cheapestPrice = currentPrice;
                        cheapestProduct = product;
                    }
                }
            }
        }
        return cheapestProduct;
    }
}
