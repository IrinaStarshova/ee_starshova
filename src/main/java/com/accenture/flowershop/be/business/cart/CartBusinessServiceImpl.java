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
    @Autowired
    private UserAccessService userAccessService;
    @Override
    public boolean addToCart(Long id, int availableQuantity, int quantity, String login) {
        if(availableQuantity < quantity)
            return false;
        Flower flower=flowerAccessService.setQuantityInCart(id,quantity);
        Cart cart=new Cart(flower.getId(), flower.getName(), quantity,
                flower.getPrice().multiply(new BigDecimal(quantity)));
        cartAccessService.addCartItem(cart,login);
        return true;
    }

    @Override
    public List<CartDTO> getCart(String login) {
        return cartToCartDTO(cartAccessService.getCarts(login));
    }


    @Override
    public void clearCart(String login) {
        cartAccessService.clearCart(login);
        userAccessService.changeCartCost(login);
    }

    private List<CartDTO> cartToCartDTO(List<Cart> cartList){
        List<CartDTO> cartDTOs=new ArrayList<>();
        for(Cart cart:cartList)
            cartDTOs.add(new CartDTO(cart.getId(),cart.getFlowerName(),
                    cart.getQuantity(),cart.getTotalPrice()));
        return cartDTOs;
    }
}
