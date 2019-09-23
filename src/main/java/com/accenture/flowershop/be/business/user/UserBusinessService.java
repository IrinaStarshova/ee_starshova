package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.entity.user.Customer;
import com.accenture.flowershop.be.entity.user.User;
import javax.jms.JMSException;
import java.io.IOException;

/**
 * Интерфейс логики для пользователей
 */
public interface UserBusinessService {

    /**
     * Метод для создания нового пользователя
     * @param username - login пользователя
     * @param password - пароль пользователя
     * @param firstName - имя пользователя
     * @param patronymic - отчество пользователя
     * @param lastName - фамилия пользователя
     * @param address - адрес пользователя
     * @param phoneNumber - номер телефона пользователя
     */
    void createNewUser(String username, String password,
                          String firstName, String patronymic,
                          String lastName, String address, String phoneNumber) throws IOException, JMSException;

    boolean isUserExists(String login);

    /**
     * Метод для осуществления входа пользователя
     * @param login - login пользователя
     * @param password - пароль пользователя
     * @return сущность пользователя при успешном входе
     */
    User userLogin(String login, String password);

    /**
     * Метод для оплаты заказа
     * @param login - login пользователя, заказ которого должен быть оплачен
     * @param orderId - идентификатор заказа, котороый требуется оплатить
     * @return булево значение, указывающее произошла ли оплата
     */
    boolean payOrder(String login,Long orderId);

    /**
     * Метод для получения пользователя с определенным логином
     * @param login - login пользователя
     * @return сущность пользователя
     */
    Customer getCustomer(String login);
}