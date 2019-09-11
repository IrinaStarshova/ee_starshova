package com.accenture.flowershop.be.business.cart;

import com.accenture.flowershop.be.access.cart.CartAccessService;
import com.accenture.flowershop.be.access.flower.FlowerAccessService;
import com.accenture.flowershop.be.access.user.UserAccessService;
import com.accenture.flowershop.be.entity.cart.Cart;
import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.fe.dto.CartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.*;

@Service
public class CartBusinessServiceImpl implements CartBusinessService {
    @Autowired
    private FlowerAccessService flowerAccessService;
    @Autowired
    private CartAccessService cartAccessService;

    @Override
    public BigDecimal addToCart(Long id, int quantity, String login) {
        Flower flower = flowerAccessService.getFlower(id);
        if(flower.getQuantity()-flower.getQuantityInCart() < quantity)
            return null;
        Cart cart=new Cart(flower.getId(), flower.getName(), quantity,
                flower.getPrice().multiply(new BigDecimal(quantity)));
        flower.setQuantityInCart(flower.getQuantityInCart() + quantity);
        flowerAccessService.updateFlower(flower);
        return cartAccessService.addCartItem(cart,login);
    }

    @Override
    public List<CartDTO> getCartByOrderId(Long orderId) {
        List<CartDTO> cartDTOs=new ArrayList<>();
        for(Cart cart:cartAccessService.getCarts(orderId))
            cartDTOs.add(new CartDTO(cart.getId(),cart.getFlowerName(),
                    cart.getQuantity(),cart.getTotalPrice()));
        return cartDTOs;
    }

    @Override
    public List<CartDTO> getCart(String login) {
        List<CartDTO> cartDTOs=new ArrayList<>();
        for(Cart cart:cartAccessService.getCarts(login))
            cartDTOs.add(new CartDTO(cart.getId(),cart.getFlowerName(),
                    cart.getQuantity(),cart.getTotalPrice()));
        return cartDTOs;
    }

    @Override
    public void clearCart(String login) {
        cartAccessService.clearCart(login);
    }
}
