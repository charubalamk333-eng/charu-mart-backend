package com.charumart.charumart.controller;

import com.charumart.charumart.entity.Cart;
import com.charumart.charumart.repository.CartRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin
public class CartController {

    private final CartRepository cartRepository;

    public CartController(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    // Get all cart items
    @GetMapping
    public List<Cart> getAllCartItems() {
        return cartRepository.findAll();
    }

    // Add item to cart
    @PostMapping
public Cart addToCart(@RequestBody Cart cart) {

    Cart existingCart = cartRepository
            .findFirstByUserIdAndProductId(
                    cart.getUserId(),
                    cart.getProductId()
            )
            .orElse(null);

    if (existingCart != null) {
        existingCart.setQuantity(
                existingCart.getQuantity() + cart.getQuantity()
        );

        return cartRepository.save(existingCart);
    }

    return cartRepository.save(cart);
}

    // Delete cart item
    @DeleteMapping("/{id}")
    public void deleteCartItem(@PathVariable Long id) {
        cartRepository.deleteById(id);
    }
    // Update cart item quantity
@PutMapping("/{id}")
public Cart updateCartQuantity(
        @PathVariable Long id,
        @RequestBody Cart updatedCart) {

    Cart cart = cartRepository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException("Cart item not found"));

    cart.setQuantity(updatedCart.getQuantity());

    return cartRepository.save(cart);
}
}