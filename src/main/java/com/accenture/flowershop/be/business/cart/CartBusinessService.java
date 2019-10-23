package com.accenture.flowershop.be.business.cart;

/**
 * Интерфейс логики для корзины
 */
public interface CartBusinessService {

    /**
     * Метод для добавления строки в корзину пользователя
     *
     * @param id       - идентификатор цветка, который необходимо добавить в корзину
     * @param quantity - количество добавляемых цветков данного вида
     * @param login    - login пользователя, в корзину которого добавляется элемент
     * @return булево значение, указываюшее была ли добавлена строка в корзину
     */
    boolean addToCart(Long id, int quantity, String login);

    /**
     * Метод для очистки корзины пользователя и удаления соответствующих элементов корзины из БД
     *
     * @param login - login пользователя, корзину которого необходимо очистить
     */
    void clearCart(String login);
}