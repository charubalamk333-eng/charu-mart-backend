package com.charumart.charumart.repository;

import com.charumart.charumart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findFirstByUserIdAndProductId(
            Long userId,
            Long productId
    );
}