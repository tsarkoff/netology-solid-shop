package companies;

import sales.Customer;
import sales.Order;

public interface DeliveryCompany {
    Order.Status getOrderStatus(Customer customer, Order order);

    Order.Location getOrderLocation(Customer customer, Order order);
}
