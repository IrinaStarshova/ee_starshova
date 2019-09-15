package com.accenture.flowershop.be.access.cart;

import com.accenture.flowershop.be.entity.cart.Cart;
import java.util.List;

/**
 * Интерфейс доступа к базе данных для корзины
 */
public interface CartAccessService {

    /**
     * Добавляет строку в корзину пользователя
     * @param cart - элемент корзины, который нужно добавить
     * @param login - login пользователя, в корзину которого добавляется элемент
     */
    void addCartItem(Cart cart, String login);

    /**
     * Возвращает список элементов корзины пользователя
     * @param login - login пользователя, корзину которого требуется получить
     * @return список элементов корзины пользователя
     */
    List<Cart> getCarts(String login);

    /**
     *Возвращает список элементов корзины определенного заказа
     * @param orderId - идентификатор заказа, список элементов которого нужно получить
     * @return список элементов корзины определенного заказа
     */
    List<Cart> getCarts(Long orderId);

    /**
     * Метод для очистки корзины пользователя после создания заказа
     * @param login - login пользователя, корзину которого необходимо очистить
     */
    void clearCartWhenOrdering(String login);

    /**
     * Метод для очистки корзины пользователя и удаления соответствующих строк из таблицы БД
     * @param login- login пользователя, корзину которого необходимо очистить
     */
    void clearCart(String login);
}
