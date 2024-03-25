package products;

import lombok.*;
import sales.Countable;

import static java.lang.String.format;

@RequiredArgsConstructor
@Getter
@Setter
public abstract class Product extends Countable implements ProductWeighted {
    @NonNull
    protected String brand;
    @NonNull
    protected String name;
    @NonNull
    protected float price;
    @NonNull
    protected float weigth;
    protected int mark = 0;

    @Override
    public String toString() {
        return format("\n\t%03d Product : brand=%s | name=%s | price=%.1fруб | weigth=%.1fкг | mark=%d баллов | ",
                id,
                brand,
                name,
                price,
                weigth,
                mark);
    }
}
