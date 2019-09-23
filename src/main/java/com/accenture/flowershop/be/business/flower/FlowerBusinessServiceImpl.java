package com.accenture.flowershop.be.business.flower;

import com.accenture.flowershop.be.access.flower.FlowerAccessService;
import com.accenture.flowershop.be.entity.flower.Flower;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.*;

@Service
public class FlowerBusinessServiceImpl implements FlowerBusinessService {
    @Autowired
    private FlowerAccessService flowerAccessService;
    private static final Logger LOG = 	LoggerFactory.getLogger(FlowerBusinessService.class);

    @Override
    public List<Flower> getFlowers() {
        return flowerAccessService.getFlowers();
    }

    public void increaseQuantityOfFlowers(int count){
        flowerAccessService.increaseQuantityOfFlowers(count);
        LOG.debug("Quantity of flowers increased by "+ count);
    }

    @Override
    public List<Flower> findFlowers(String name, String priceFrom, String priceTo) {
        BigDecimal minPrice=BigDecimal.ZERO;
        BigDecimal maxPrice=new BigDecimal(100000.00);
        if(!priceFrom.isEmpty() && !priceFrom.equals("0")) {
            minPrice = new BigDecimal(priceFrom);
        }
        if(!priceTo.isEmpty())
            maxPrice=new BigDecimal(priceTo);
        return flowerAccessService.findFlowers(name, minPrice, maxPrice);
    }
}
