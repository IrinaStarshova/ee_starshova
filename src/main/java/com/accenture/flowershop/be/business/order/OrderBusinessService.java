package com.accenture.flowershop.be.business.order;

import com.accenture.flowershop.be.entity.order.Order;
import java.util.List;

/**
 * Интерфейс логики для заказов
 */
public interface OrderBusinessService {

    /**
     * Метод для создания нового заказа
     * @param login - login пользователя, заказ которого должен быть создан
     * @return булево значение, указывающее создан ли заказ
     */
    boolean createNewOrder(String login);

    /**
     * Метод для получения списка заказов
     * @return список заказов
     */
    List<Order> getOrders();

    /**
     * Метод для получения списка заказов определенного пользователя
     * @param login - login пользователя, заказы которого требуются
     * @return список заказов определенного пользователя
     */
    List<Order> getOrders(String login);

    /**
     * Метод для закрытия заказа
     * @param id - идентификатор заказа, который требуется закрыть
     */
    void closeOrder(Long id);
}