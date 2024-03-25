package products;

import static java.lang.String.format;

public class ProductFood extends Product {
    private final float boxWeight = 0.5F;

    public ProductFood(String brand, String name, float price, float weigth) {
        super(brand, name, price, weigth);
    }

    @Override
    public float getWeightGross() {
        return super.weigth + boxWeight;
    }

    @Override
    public String toString() {
        return super.toString() + format(" boxWeight=%.1fкг | weightGross=%.1fкг", boxWeight, getWeightGross());
    }
}
