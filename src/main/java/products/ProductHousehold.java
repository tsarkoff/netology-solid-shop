package products;

import static java.lang.String.format;

public class ProductHousehold extends Product {
    private final float packageWeight = 5F;

    public ProductHousehold(String brand, String name, float price, float weigth) {
        super(brand, name, price, weigth);
    }

    @Override
    public float getWeightGross() {
        return super.weigth + packageWeight;
    }

    @Override
    public String toString() {
        return super.toString() + format(" boxWeight=%.1fкг | weightGross=%.1fкг", packageWeight, getWeightGross());
    }
}
