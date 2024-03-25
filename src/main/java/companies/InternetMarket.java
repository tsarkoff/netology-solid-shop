package companies;

import products.Product;
import ratings.*;
import sales.Customer;
import sales.Logger;
import sales.Order;

import java.util.*;

public class InternetMarket extends Company implements EcommerceCompany, DeliveryCompany, RatingCompany {
    private final Logger logger = Logger.getInstance();
    private List<Product> products = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();

    public InternetMarket(String name, String address, String taxID, List<Product> products, List<Customer> customers) {
        super(name, address, taxID);
        this.products = products;
        this.customers = customers;
    }

    @Override
    public List<Product> getProducts() {
        logger.logf("СПИСОК ТОВАРОВ НА СКЛАДЕ:");
        logger.log(products);
        return products;
    }

    @Override
    public Product searchProduct(String name) {
        Product product = products.stream().filter(p -> p.getName().contains(name)).toList().getFirst();
        logger.logf("ИЩЕМ ПРОДУКТ по НАЗВАНИЮ: " + name);
        logger.log(product);
        return product;
    }

    @Override
    public List<Customer> getCustomers() {
        logger.logf("СПИСОК КЛИЕНТОВ МАГАЗИНА:");
        Logger.getInstance().log(customers);
        return customers;
    }

    @Override
    public Customer searchCustomer(String name) {
        return customers.stream().filter(c -> c.getName().contains(name)).toList().getFirst();
    }

    @Override
    public List<Order> getOrders(boolean showLog) {
        List<Order> orders = new ArrayList<>();
        for (Customer c : customers) {
            orders.addAll(c.getOrders());
        }
        if (showLog) {
            logger.logf("СПИСОК ЗАКАЗОВ МАГАЗИНА:");
            Logger.getInstance().log(orders);
        }
        return orders;
    }

    @Override
    public List<Order> getOrders(Customer customer, boolean showLog) {
        List<Order> orders = customers.get(customers.indexOf(customer)).getOrders();
        if (showLog) {
            logger.logf("СПИСОК ЗАКАЗОВ КЛИЕНТА:");
            Logger.getInstance().log(customer);
        }
        return orders;
    }

    @Override
    public boolean addOrder(Customer customer, Order order) {
        return getOrders(customer, false).add(order);
    }

    @Override
    public boolean deleteOrder(Customer customer, Order order) {
        return getOrders(customer, false).remove(order);
    }

    @Override
    public boolean returnOrder(Customer customer, Order order) {
        return deleteOrder(customer, order);
    }

    @Override
    public boolean repeatOrder(Customer customer, Order order) {
        return addOrder(customer, order);
    }

    @Override
    public Order.Status getOrderStatus(Customer customer, Order order) {
        Order.Status status = Order.Status.NOT_DEFINED;
        for (Order o : getOrders(customer, false)) {
            if (o.equals(order)) {
                status = o.getStatus();
                break;
            }
        }
        logger.log("СТАТУС ЗАКАЗА КЛИЕНТА:");
        logger.log((Object)(customer.getName() + " | Order ID = " + order.getId() + " | Order Status = " + order.getStatus()));
        //Logger.getInstance().log(customer);
        //Logger.getInstance().log(order);
        //Logger.getInstance().log(status);
        return status;
    }

    @Override
    public Order.Location getOrderLocation(Customer customer, Order order) {
        Order.Location location = Order.Location.NOT_DEFINED;
        for (Order o : getOrders(customer, false)) {
            if (o.equals(order)) {
                location = o.getLocation();
                break;
            }
        }
        logger.log("МЕСТОПОЛОЖЕНИЕ ЗАКАЗА КЛИЕНТА:");
        logger.log((Object) ("\t" + customer.getName() + " | Order ID = " + order.getId() + " | Order Location = " + order.getLocation()));
        //Logger.getInstance().log(customer);
        //Logger.getInstance().log(order);
        //Logger.getInstance().log(location);
        return location;
    }

    @Override
    public Product getCheapestProduct() {
        Rating rating = new RatingCheapest();
        Product product = rating.getBestProduct(getOrders(false), Rating.RatingType.CHEAPEST_BY_ORDERS);
        logger.logf("РЕЙТИНГ: САМЫЙ ДЕШЕВЫЙ ПОКУПАЕМЫЙ ТОВАР:");
        Logger.getInstance().log(product);
        return product;
    }

    @Override
    public Product getPopularProduct(boolean showLog) {
        Rating rating = new RatingPopular();
        Product product = rating.getBestProduct(customers, Rating.RatingType.POPULAR_BY_CUSTOMERS);
        if (showLog) {
            logger.logf("РЕЙТИНГ: САМЫЙ ПОПУЛЯРНЫЙ ПОКУПАЕМЫЙ ТОВАР:");
            Logger.getInstance().log(product);
        }
        return product;
    }

    @Override
    public Product getHighMarkProduct() {
        Rating rating = new RatingHighMark();
        Product product = rating.getBestProduct(products, Rating.RatingType.HIGH_MARK_BY_PRODUCT);
        logger.logf("РЕЙТИНГ: САМЫЙ ВЫСОКООЦЕНЕННЫЙ ПОКУПАЕМЫЙ ТОВАР:");
        Logger.getInstance().log(product);
        return product;
    }

    @Override
    public Order getRecommendedOrder() {
        Order order = null;
        for (Order o : getOrders(false)) {
            for (Product p : o.getProducts()) {
                if (p == getPopularProduct(false)) {
                    order = o;
                    break;
                }
            }
        }
        logger.logf("РЕЙТИНГ: РЕКОМЕНДУЕМЫЙ ПОПУЛЯРНЫЙ ЗАКАЗ:");
        Logger.getInstance().log(order);
        return order;
    }

    public float getTotalOrdersWeightGross() {
        float totalWeight = 0;
        for (Customer c : customers) {
            for (Order o : c.getOrders()) {
                for (Product p : o.getProducts()) {
                    totalWeight += p.getWeightGross();
                }
            }
        }
        logger.logf("МАГАЗИН: СУММАРНЫЙ ВЕС БРУТТО ВСЕХ СДЕЛАНЫХ ЗАКАЗАОВ : ");
        Logger.getInstance().log((Object)(totalWeight + " кг"));
        return totalWeight;
    }
}
