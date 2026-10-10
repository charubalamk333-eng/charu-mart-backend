package com.charumart.charumart.controller;

import com.charumart.charumart.entity.Cart;
import com.charumart.charumart.entity.Order;
import com.charumart.charumart.entity.OrderItem;
import com.charumart.charumart.entity.Product;

import com.charumart.charumart.repository.CartRepository;
import com.charumart.charumart.repository.OrderRepository;
import com.charumart.charumart.repository.OrderItemRepository;
import com.charumart.charumart.repository.ProductRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    public OrderController(
            CartRepository cartRepository,
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
    }

    @PostMapping
    @Transactional
    public Order placeOrder(@RequestBody Order request) {

        List<Cart> cartItems = cartRepository.findAll()
                .stream()
                .filter(item -> item.getUserId().equals(request.getUserId()))
                .toList();

        if (cartItems.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Your cart is empty");
        }

        double total = 0;

        for (Cart item : cartItems) {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Product not found"));

            total += product.getPrice() * item.getQuantity();
        }

        Order order = new Order();
        order.setUserId(request.getUserId());
        order.setTotalAmount(total);
        order.setStatus("Pending");

        Order savedOrder = orderRepository.save(order);

        for (Cart item : cartItems) {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Product not found"));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(savedOrder.getOrderId());
            orderItem.setProductId(item.getProductId());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(product.getPrice());

            orderItemRepository.save(orderItem);
        }

        cartRepository.deleteAll(cartItems);

        return savedOrder;
    }
}