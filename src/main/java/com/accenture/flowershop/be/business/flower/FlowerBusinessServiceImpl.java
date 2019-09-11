package com.accenture.flowershop.be.business.flower;

import com.accenture.flowershop.be.access.flower.FlowerAccessService;
import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.fe.dto.FlowerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class FlowerBusinessServiceImpl implements FlowerBusinessService {
    @Autowired
    private FlowerAccessService flowerAccessService;

    @Override
    public List<FlowerDTO> getFlowers() {
        List<FlowerDTO> flowerDTOs=new ArrayList<>();
        for(Flower flower:flowerAccessService.getFlowers())
            flowerDTOs.add(new FlowerDTO(flower.getId(), flower.getName(),
                    flower.getPrice(),flower.getQuantity(),flower.getQuantityInCart()));
        return flowerDTOs;
    }
}
