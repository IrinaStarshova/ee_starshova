package com.accenture.flowershop.be.business.flower;

import com.accenture.flowershop.be.access.flower.FlowerAccessService;
import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.fe.dto.FlowerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class FlowerBusinessServiceImpl implements FlowerBusinessService {
    @Autowired
    private FlowerAccessService flowerAccessService;

    @Override
    public List<FlowerDTO> getFlowers() {
        return flowersToFlowerDTOs(flowerAccessService.getFlowers());
    }


    @Override
    public List<FlowerDTO> findFlowers(String name, String priceFrom, String priceTo) {
        BigDecimal minPrice=BigDecimal.ZERO;
        BigDecimal maxPrice=new BigDecimal(100000.00);
        if(!priceFrom.isEmpty() && !priceFrom.equals("0")) {
            minPrice = new BigDecimal(priceFrom);
        }
        if(!priceTo.isEmpty())
            maxPrice=new BigDecimal(priceTo);
        return flowersToFlowerDTOs(flowerAccessService.findFlowers(name, minPrice, maxPrice));
    }

    private List<FlowerDTO> flowersToFlowerDTOs(List<Flower> flowers){
        List<FlowerDTO> flowerDTOs=new ArrayList<>();
        for(Flower flower:flowers)
            flowerDTOs.add(new FlowerDTO(flower.getId(), flower.getName(),
                    flower.getPrice(),flower.getQuantity(),flower.getQuantityInCart()));
        return flowerDTOs;
    }
}
