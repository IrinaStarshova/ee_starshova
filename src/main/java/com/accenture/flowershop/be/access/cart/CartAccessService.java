package com.accenture.flowershop.be.access.cart;

import com.accenture.flowershop.be.entity.cart.Cart;

import java.util.List;

/**
 * Интерфейс доступа к базе данных для корзины
 */
public interface CartAccessService {

    /**
     * Возвращает список элементов корзины пользователя
     *
     * @param login - login пользователя, корзину которого требуется получить
     * @return список элементов корзины пользователя
     */
    List<Cart> getCarts(String login);

    /**
     * Возвращает элемент корзины пользователя с определенным цветком
     *
     * @param login    - login пользователя, корзину которого требуется получить
     * @param flowerId - идентификатор цветка, элемент корзины с которым требуется
     * @return элемент корзины
     */
    Cart getCart(String login, Long flowerId);

    /**
     * Метод для удаления определенных элементов корзины
     *
     * @param carts - список элементов корзины, которые требуется удалить
     */
    void clearCart(List<Cart> carts);
}
