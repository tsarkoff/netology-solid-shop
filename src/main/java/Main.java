import companies.InternetMarket;
import products.Product;
import products.ProductCarItems;
import products.ProductFood;
import products.ProductHousehold;
import sales.Customer;
import sales.Order;

import java.util.List;

/**
 * @SOLID
 * @Single-responsibility-principle = Логирование ведет не каждый класс отдельно, а отдельный класс Logger,
 * без имплементации его методов другими классами, у которого есть Принцип Единственной Ответственности
 *
 * @Open-closed-principle = обработка веса разных типов Продуктов (пищевые товары, хозяйственные товары, итд)
 * производится через Интерфейс ProductWeighted, который позволяет вычислять вес БРУТТО Продукта, при этом,
 * если появится новый тип продукта (электротовары, авто-товары, итд), то изменения не потребуются
 *
 * @Liskov-substitution-principle = Специализированные типы Продуктов(еда, хозтовары, итд) наследуются от
 * родительского абстрактного класса, потому что они могут играть роль Общего Типа Продукта, у которого
 * есть наименование, цена, итд - т.о., спец. Продукты играют роль за предка
 *
 * @Interface-segregation-principle = наш Интернет Магазин является Компанией и использует возможности
 * операционной деятельности других компаний: так, есть Общая Компания, а есть интерфейсы, реализующие разные
 * виды Компаний: компания электронных интернет заказов, торговая компания по продаже товаров, логистическая
 * компания доставки товаров покупателю, компания контроля-учёта-подсчета и предоставления рейтинга товаров:
 * т.о., если нужно создать Магазин, который торгует еще и в Торговом Центре, то он сможет реализовать нужный
 * Интерфейс Функций магазина ТРЦ
 *
 * @Dependency-inversion-principle = для исключения зависимости от реализации, например, у нас есть разные классы,
 * реализующие сложные математические подсчеты Рейтинга, но, чтобы не зависеть от конкретного подсчета, мы
 * реализуем Интерфейс, выдающий Рейтинг Товара, которая не зависит от Математической Модели расчета и, если
 * нужно добавить новую Математическую Модель, то она просто реализует Интерфейс Рейтинга и нам не надо
 * беспокоит1ься о деталях реализации Подсчета Рейтинга (или замены его на другой тип подсчета - не по оценке
 * Пользователя, а, например, по частоте покупки данного Товара)
 */

public class Main {
    public static void main(String[] args) {
        // Создание магазина, наполнение товарами, привлечение клиентов
        List<Product> products = loadProducts();
        List<Customer> customers = inviteCustomers();
        InternetMarket shop = new InternetMarket("Продам всё", "Благовещенск", "1234567890", products, customers);

        // Вывод доступных для покупки товаров
        shop.getProducts();

        // Вывод клиентов магазина
        shop.getCustomers();

        // Фильтрация товаров по ключевым словам, ценам, производителям +
        // + Составление продуктовой корзины пользователя
        Order o1 = new Order();
        o1.getProducts().add(shop.searchProduct("Карда"));
        o1.getProducts().add(shop.searchProduct("Белизна"));
        Order o2 = new Order();
        o2.getProducts().add(shop.searchProduct("Тартар"));
        o2.getProducts().add(shop.searchProduct("Мука"));
        o2.getProducts().add(shop.searchProduct("Мука"));
        shop.addOrder(shop.searchCustomer("Иван"), o1);
        shop.addOrder(shop.searchCustomer("Иван"), o2);

        // Вывод всех заказов
        shop.getOrders(true);

        // Трекинг заказа в системе доставки:
        // установка и чтение Статуса и Местоположения
        shop.searchCustomer("Иван").getOrders().getFirst().setStatus(Order.Status.PAID);
        shop.searchCustomer("Иван").getOrders().getFirst().setLocation(Order.Location.COURIER);
        shop.getOrderLocation(shop.searchCustomer("Иван"), o1);
        shop.getOrderStatus(shop.searchCustomer("Иван"), o1);

        // Возврат заказа, повторение заказа
        shop.returnOrder(shop.searchCustomer("Иван"), o2);
        shop.repeatOrder(shop.searchCustomer("Иван"), o2); // возрастает популярность товаров Мука и Тартар

        // Система рейтинга для товаров
        // установка и получение Оценки + получение Продукта = Дешевого,Популярного, с Высокой Оценкой
        shop.searchCustomer("Иван").getOrders().getLast().getProducts().getFirst().setMark(10);
        shop.searchCustomer("Иван").getOrders().getLast().getProducts().getLast().setMark(5);
        shop.getCheapestProduct();
        shop.getPopularProduct(true);
        shop.getHighMarkProduct();

        // Простая рекомендательная система для покупок:
        // узнать Самый Популярный Заказ
        shop.getRecommendedOrder();

        // узнать ВЕС БРУТТО всех заказанных товаров магазина
        // для заказа автомобиля для доставки
        shop.getTotalOrdersWeightGross();
    }

    private static List<Product> loadProducts() {
        return List.of(
                new ProductFood("Мираторг", "Тартар", 950F, 0.9F),
                new ProductFood("Мираторг", "Пельмени", 250F, 0.8F),
                new ProductFood("Агорокомплекс", "Мука", 120F, 2.0F),
                new ProductFood("Агорокомплекс", "Рис", 160F, 1.0F),
                new ProductHousehold("Сантор", "Белизна", 90F, 1.0F),
                new ProductHousehold("Пропер", "Ксилит", 150F, 0.5F),
                new ProductCarItems("ВАЗ", "Кардан", 7500F, 35.0F),
                new ProductCarItems("КАМАЗ", "Пневмотормоз", 5000F, 15.0F)
        );
    }

    private static List<Customer> inviteCustomers() {
        return List.of(
                new Customer("Иван Грозный", "Москва"),
                new Customer("Петр Первый", "Санкт-Петербург")
        );
    }
}
