package companies;

import products.Product;
import sales.Customer;
import sales.Order;

import java.util.List;

public interface EcommerceCompany {
    List<Product> getProducts();

    Product searchProduct(String name);

    List<Customer> getCustomers();

    Customer searchCustomer(String name);

    List<Order> getOrders(boolean showLog);

    List<Order> getOrders(Customer customer, boolean showLog);

    boolean addOrder(Customer customer, Order order);

    boolean deleteOrder(Customer customer, Order order);

    boolean returnOrder(Customer customer, Order order);

    boolean repeatOrder(Customer customer, Order order);
}
