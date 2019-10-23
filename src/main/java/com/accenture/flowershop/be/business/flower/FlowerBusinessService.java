package com.accenture.flowershop.be.business.flower;

import com.accenture.flowershop.be.business.exceptions.UnavailableQuantityException;
import com.accenture.flowershop.be.entity.flower.Flower;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import java.util.List;

/**
 * Интерфейс логики для цветов
 */
public interface FlowerBusinessService {

    /**
     * Метод для получения списка имеющихся цветов
     *
     * @return список цветов
     */
    List<Flower> getFlowers();

    /**
     * Метод для увеличения значения количества определенного вида цветка
     *
     * @param id       - идентификатор цветка
     * @param quantity - число, на которое необходимо увеличить количество цветов
     */
    void increaseQuantityOfFlower(Long id, int quantity)
            throws ObjectOptimisticLockingFailureException;

    /**
     * Метод для уменьшения значения количества определенного вида цветка
     *
     * @param id       - идентификатор цветка
     * @param quantity - число, на которое необходимо уменьшить количество цветов
     */
    void decreaseQuantityOfFlower(Long id, int quantity)
            throws ObjectOptimisticLockingFailureException, UnavailableQuantityException;

    /**
     * Метод для увеличения значения количества всех цветов на определенное значение
     *
     * @param count - число, на которое должно быть увеличено количество
     */
    void increaseQuantityOfAllFlowers(int count) throws ObjectOptimisticLockingFailureException;

    /**
     * Метод для поиска цветов по имени и диапазону цен
     *
     * @param name      - имя или часть имени цветка
     * @param priceFrom - нижняя граница диапазона цены
     * @param priceTo   - верхняя граница диапазона цены
     * @return список найденных цветов
     */
    List<Flower> findFlowers(String name, String priceFrom, String priceTo);
}