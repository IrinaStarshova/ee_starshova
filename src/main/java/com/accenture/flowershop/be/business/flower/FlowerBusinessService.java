package com.accenture.flowershop.be.business.flower;

import com.accenture.flowershop.fe.dto.FlowerDTO;
import java.util.List;

/**
 * Интерфейс логики для цветов
 */
public interface FlowerBusinessService {

    /**
     * Метод для получения списка DTO имеющихся цветов
     * @return список DTO цветов
     */
    List<FlowerDTO> getFlowers();

    /**
     * Метод для поиска цветов по имени и диапазону цен
     * @param name - имя или часть имени цветка
     * @param priceFrom - нижняя граница диапазона цены
     * @param priceTo - верхняя граница диапазона цены
     * @return список найденных цветов
     */
    List<FlowerDTO> findFlowers(String name, String priceFrom, String priceTo);
}