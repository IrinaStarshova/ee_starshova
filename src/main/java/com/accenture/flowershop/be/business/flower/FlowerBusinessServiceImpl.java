package com.accenture.flowershop.be.business.flower;

import com.accenture.flowershop.be.access.flower.FlowerAccessService;
import com.accenture.flowershop.be.business.exceptions.UnavailableQuantityException;
import com.accenture.flowershop.be.entity.flower.Flower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@EnableRetry
public class FlowerBusinessServiceImpl implements FlowerBusinessService {
    @Autowired
    private FlowerAccessService flowerAccessService;

    @Override
    public List<Flower> getFlowers() {
        return flowerAccessService.getFlowers();
    }

    @Override
    @Retryable(value = {ObjectOptimisticLockingFailureException.class},
            maxAttempts = 5,
            backoff = @Backoff(delay = 50))
    @Transactional
    public void increaseQuantityOfFlower(Flower flower, int quantity) {
        flower.increaseQuantity(quantity);
        flowerAccessService.update();
    }

    @Override
    @Retryable(value = {ObjectOptimisticLockingFailureException.class},
            maxAttempts = 5,
            backoff = @Backoff(delay = 50))
    @Transactional
    public void decreaseQuantityOfFlower(Flower flower, int quantity){
        if (flower.getQuantity() < quantity) {
            throw new UnavailableQuantityException();
        }
        flower.decreaseQuantity(quantity);
        flowerAccessService.update();
    }

    @Override
    @Retryable(value = {ObjectOptimisticLockingFailureException.class},
            maxAttempts = 5,
            backoff = @Backoff(delay = 50))
    @Transactional
    public void increaseQuantityOfAllFlowers(int count) {
        flowerAccessService.getFlowers().forEach(flower -> flower.increaseQuantity(count));
    }

    @Override
    public List<Flower> findFlowers(String name, String priceFrom, String priceTo) {
        BigDecimal minPrice = (!priceFrom.isEmpty()) ? new BigDecimal(priceFrom) : null;
        BigDecimal maxPrice = (!priceTo.isEmpty()) ? new BigDecimal(priceTo) : null;
        return flowerAccessService.findFlowers(name, minPrice, maxPrice);
    }
}
