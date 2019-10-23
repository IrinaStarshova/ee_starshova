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
import org.springframework.transaction.annotation.Propagation;
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
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void increaseQuantityOfFlower(Long id, int quantity)
            throws ObjectOptimisticLockingFailureException {
        Flower flower = flowerAccessService.getFlower(id);
        flower.setQuantity(flower.getQuantity() + quantity);
    }

    @Override
    @Retryable(value = {ObjectOptimisticLockingFailureException.class},
            maxAttempts = 5,
            backoff = @Backoff(delay = 50))
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void decreaseQuantityOfFlower(Long id, int quantity)
            throws ObjectOptimisticLockingFailureException,
            UnavailableQuantityException {
        Flower flower = flowerAccessService.getFlower(id);
        if (flower.getQuantity() < quantity)
            throw new UnavailableQuantityException();
        flower.setQuantity(flower.getQuantity() - quantity);
    }

    @Override
    @Retryable(value = {ObjectOptimisticLockingFailureException.class},
            maxAttempts = 5,
            backoff = @Backoff(delay = 50))
    @Transactional
    public void increaseQuantityOfAllFlowers(int count)
            throws ObjectOptimisticLockingFailureException {
        for (Flower f : flowerAccessService.getFlowers()) {
            f.setQuantity(f.getQuantity() + count);
        }
    }

    @Override
    public List<Flower> findFlowers(String name, String priceFrom, String priceTo) {
        BigDecimal minPrice = (!priceFrom.isEmpty()) ? new BigDecimal(priceFrom) : null;
        BigDecimal maxPrice = (!priceTo.isEmpty()) ? new BigDecimal(priceTo) : null;
        return flowerAccessService.findFlowers(name, minPrice, maxPrice);
    }
}
