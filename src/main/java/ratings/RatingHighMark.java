package ratings;

import products.Product;

import java.util.List;

public class RatingHighMark implements Rating<Product> {
    @Override
    public Product getBestProduct(List<Product> products, RatingType ratingType) {
        int bestMark = -1;
        Product bestProduct = null;
        if (ratingType == RatingType.HIGH_MARK_BY_PRODUCT && products != null) {
            for (Product product : products) {
                int currentMark = product.getMark();
                if (currentMark > bestMark) {
                    bestMark = currentMark;
                    bestProduct = product;
                }
            }
        }
        return bestProduct;
    }
}
