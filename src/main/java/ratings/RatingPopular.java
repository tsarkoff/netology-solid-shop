package ratings;

import products.Product;
import sales.Customer;
import sales.Order;

import java.util.*;
import java.util.stream.Collectors;

public class RatingPopular implements Rating<Customer> {

    @Override
    public Product getBestProduct(List<Customer> customers, RatingType ratingType) {
        Map<Product, Integer> productPopularity = new HashMap<>();
        if (ratingType == RatingType.POPULAR_BY_CUSTOMERS && customers != null) {
            for (Customer customer : customers) {
                for (Order order : customer.getOrders()) {
                    for (Product product : order.getProducts()) {
                        productPopularity.put(product, productPopularity.getOrDefault(product, 0) + 1);
                    }
                }
            }
        }
        Integer maxValue = Collections.max(productPopularity.values());
        Set<Product> set = productPopularity
                .entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), maxValue))
                .distinct()
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
        Product product = (Product) set.toArray()[0];
        return product;
    }
}
