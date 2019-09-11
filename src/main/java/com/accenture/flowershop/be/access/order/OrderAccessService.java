package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.entity.cart.Cart;
import com.accenture.flowershop.be.entity.order.Order;
import java.util.List;

/**
 * Интерфейс доступа к базе данных для заказов
 */
public interface OrderAccessService {

    /**
     * Добавляет новый заказ в список заказов определенного пользователя
     * @param order - заказ, который необходимо добавить в БД
     * @param login - login пользователя, которому должен принадлежать создаваемый заказ
     * @param carts - список элементов корзины, которые необходимо добавить в заказ
     */
    void createOrder(Order order, String login, List<Cart> carts);

    /**
     * Метод для получения заказа с определенным идентификатором
     * @param orderId - идентификатор заказа
     * @return сущность заказа с определенным идентификатором
     */
    Order getOrder(Long orderId);

    /**
     * Метод для получения всех имеющихся заказов
     * @return список имеющихся заказов
     */
    List<Order> getOrders();

    /**
     * Метод для получения заказов определенного пользователя
     * @param login - login пользователя, заказ которого необходимо получить
     * @return список имеющихся у данного пользователя заказов
     */
    List<Order> getOrders(String login);

    /**
     * Метод для обновления сущности заказа
     * @param order - сущность заказа для обновления
     */
    void updateOrder(Order order);
}
