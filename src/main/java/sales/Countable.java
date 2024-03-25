package sales;

import lombok.Getter;

/**
 * Данный класс наследуется другими, чтобы получить автоматическое назначение ID идентификатора каждого нового
 * Объекта класса применяется для авто-назначения ID для Продукта (Product), Заказа (Order), Клиента (Customer)
 */
@Getter
public class Countable {
    private static int counter = 0;
    protected int id;

    public Countable() {
        id = ++counter;
    }

}
