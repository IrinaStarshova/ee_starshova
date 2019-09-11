package com.accenture.flowershop.be.business.order;

import com.accenture.flowershop.fe.dto.OrderDTO;
import com.accenture.flowershop.fe.dto.UserDTO;
import java.util.List;

/**
 * Интерфейс логики для заказов
 */
public interface OrderBusinessService {

    /**
     * Метод для создания нового заказа
     * @param user - пользователь, заказ которого должен быть создан
     * @return булево значение, указывающее создан ли заказ
     */
    boolean createNewOrder(UserDTO user);

    /**
     * Метод для получения списка DTO заказов
     * @return список DTO заказов
     */
    List<OrderDTO> getOrders();

    /**
     * Метод для получения списка DTO заказов определенного пользователя
     * @param login - login пользователя, заказы которого требуются
     * @return список DTO заказов определенного пользователя
     */
    List<OrderDTO> getOrders(String login);

    /**
     * Метод для закрытия заказа
     * @param id - идентификатор заказа, который требуется закрыть
     */
    void closeOrder(Long id);
}