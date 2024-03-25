package sales;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import products.Product;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

@Setter
@Getter
public class Order extends Countable {
    public enum Status {
        NOT_DEFINED,
        EMPTY,
        DRAFT,
        PAID,
        ASSEMBLED,
        TRANSIT,
        DELIVERED
    }

    public enum Location {
        NOT_DEFINED,
        STORE,
        COURIER,
        CUSTOMER
    }

    private List<Product> products = new ArrayList<>();
    private Status status = Status.EMPTY;
    private Location location = Location.STORE;

    @Override
    public String toString() {
        return format("\nOrder № %03d:\n\tproducts : %s\n\tstatus=%s\n\tlocation=%s", id, products, status, location);
    }
}
