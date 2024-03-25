package products;

import static java.lang.String.format;

public class ProductCarItems extends Product {
    private final float containerWeight = 10F;

    public ProductCarItems(String brand, String name, float price, float weigth) {
        super(brand, name, price, weigth);
    }

    @Override
    public float getWeightGross() {
        return super.weigth + containerWeight;
    }

    @Override
    public String toString() {
        return super.toString() + format(" boxWeight=%.1fкг | weightGross=%.1fкг", containerWeight, getWeightGross());
    }
}
