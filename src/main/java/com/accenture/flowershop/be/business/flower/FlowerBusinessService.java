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
}