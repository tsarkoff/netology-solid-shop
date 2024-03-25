package ratings;

import products.Product;

import java.util.List;

public interface Rating<T> {

    public enum RatingType {
        CHEAPEST_BY_ORDERS,
        POPULAR_BY_CUSTOMERS,
        HIGH_MARK_BY_PRODUCT
    }

    Product getBestProduct(List<T> list, RatingType ratingType);
}
