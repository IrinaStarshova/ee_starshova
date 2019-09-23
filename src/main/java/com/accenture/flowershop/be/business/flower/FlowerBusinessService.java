package com.accenture.flowershop.be.business.flower;

import com.accenture.flowershop.be.entity.flower.Flower;
import java.util.List;

/**
 * Интерфейс логики для цветов
 */
public interface FlowerBusinessService {

    /**
     * Метод для получения списка имеющихся цветов
     * @return список цветов
     */
    List<Flower> getFlowers();

    /**
     * Метод для увеличения значения количества всех цветов на определенное значение
     * @param count - число, на которое должно быть уувеличено количество
     */
    void increaseQuantityOfFlowers(int count);

    /**
     * Метод для поиска цветов по имени и диапазону цен
     * @param name - имя или часть имени цветка
     * @param priceFrom - нижняя граница диапазона цены
     * @param priceTo - верхняя граница диапазона цены
     * @return список найденных цветов
     */
    List<Flower> findFlowers(String name, String priceFrom, String priceTo);
}