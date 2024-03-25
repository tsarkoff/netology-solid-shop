package companies;

import products.Product;
import sales.Order;

public interface RatingCompany {
    Product getCheapestProduct();

    Product getPopularProduct(boolean showLog);

    Product getHighMarkProduct();

    Order getRecommendedOrder();
}
