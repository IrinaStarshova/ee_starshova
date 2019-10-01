package com.accenture.flowershop.be.access.repositories;

import com.accenture.flowershop.be.entity.cart.Cart;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends BaseRepository<Cart,Long> {
}
