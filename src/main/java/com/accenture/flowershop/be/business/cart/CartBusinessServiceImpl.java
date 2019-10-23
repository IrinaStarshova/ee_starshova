package com.accenture.flowershop.be.business.cart;

import com.accenture.flowershop.be.access.cart.CartAccessService;
import com.accenture.flowershop.be.access.flower.FlowerAccessService;
import com.accenture.flowershop.be.access.user.UserAccessService;
import com.accenture.flowershop.be.entity.cart.Cart;
import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.user.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartBusinessServiceImpl implements CartBusinessService {
    @Autowired
    private FlowerAccessService flowerAccessService;
    @Autowired
    private CartAccessService cartAccessService;
    @Autowired
    private UserAccessService userAccessService;
    private static final Logger LOG = LoggerFactory.getLogger(CartBusinessService.class);

    @Override
    @Transactional
    public boolean addToCart(Long flowerId, int quantity, String login) {
        Flower flower = flowerAccessService.getFlower(flowerId);
        Cart cart = cartAccessService.getCart(login, flowerId);
        if (flower.getQuantity() < ((cart != null) ? quantity + cart.getQuantity() : quantity))
            return false;
        Customer customer = (Customer) userAccessService.getUser(login);
        BigDecimal totalPrice = flower.getPrice().multiply(new BigDecimal(quantity));
        if (cart != null) {
            cart.setQuantity(cart.getQuantity() + quantity);
            cart.setTotalPrice
                    (cart.getTotalPrice().add(totalPrice));
        } else {
            cart = new Cart(flower.getId(), flower.getName(),
                    quantity, totalPrice);
            customer.addCart(cart);
        }
        customer.setCartCostWithDiscount(totalPrice);
        LOG.info("Add cart item to cart of user - {}", customer.getLogin());
        return true;
    }

    @Override
    @Transactional
    public void clearCart(String login) {
        List<Cart> carts = cartAccessService.getCarts(login);
        if (!carts.isEmpty()) {
            cartAccessService.clearCart(carts);
            Customer customer = (Customer) userAccessService.getUser(login);
            customer.setCartCost(BigDecimal.ZERO);
        }
    }
}
