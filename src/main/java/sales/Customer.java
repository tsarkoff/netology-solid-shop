package sales;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

@RequiredArgsConstructor
@Getter
public class Customer extends Countable {
    @NonNull
    private String name;
    @NonNull
    private String address;
    private final List<Order> orders = new ArrayList<>();

    @Override
    public String toString() {
        return format("\n\t%03d Customer : name=%s | address=%s | orders : %s", id, name, address, orders);
    }
}
