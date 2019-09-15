package com.accenture.flowershop.be.business.cart;

import com.accenture.flowershop.fe.dto.CartDTO;
import com.accenture.flowershop.fe.dto.UserDTO;

import java.util.List;

/**
 * Интерфейс логики для корзины
 */
public interface CartBusinessService {

    /**
     * Метод для добавления строки в корзину пользователя
     * @param id - идентификатор цветка, который необходимо добавить в корзину
     * @param availableQuantity - доступное количество цветов
     * @param quantity - количество добавляемых цветков данного вида
     * @param login - login пользователя, в корзину которого добавляется элемент
     * @return булево значение, указываюшее была ли добавлена строка в корзину
     */
    boolean addToCart(Long id, int availableQuantity, int quantity, String login);

    /**
     * Метод для получения списка DTO корзины определенного пользователя
     * @param login - login пользователя, элементы корзины которого требуются
     * @return список DTO корзины
     */
    List<CartDTO> getCart(String login);

    /**
     * Метод для очистки корзины пользователя
     * @param login - login пользователя, корзину которого необходимо очистить
     */
    void clearCart(String login);
}