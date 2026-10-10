-- ============================================
-- CHARU MART DATABASE
-- ============================================

CREATE DATABASE IF NOT EXISTS charu_mart;

USE charu_mart;


-- ============================================
-- USERS TABLE
-- Used for customer, seller and admin accounts
-- ============================================

CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'CUSTOMER'
);


-- ============================================
-- PRODUCTS TABLE
-- Stores products added by sellers/admin
-- ============================================

CREATE TABLE IF NOT EXISTS product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    price DOUBLE NOT NULL,
    description VARCHAR(500),
    image_url VARCHAR(500),
    quantity INT NOT NULL DEFAULT 0
);


-- ============================================
-- ORDERS TABLE
-- Stores customer orders
-- ============================================

CREATE TABLE IF NOT EXISTS orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    total_amount DOUBLE NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(30) NOT NULL DEFAULT 'PLACED',

    FOREIGN KEY (user_id)
        REFERENCES users(id)
);


-- ============================================
-- ORDER ITEMS TABLE
-- Stores individual products inside an order
-- ============================================

CREATE TABLE IF NOT EXISTS order_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    price DOUBLE NOT NULL,

    FOREIGN KEY (order_id)
        REFERENCES orders(id),

    FOREIGN KEY (product_id)
        REFERENCES product(id)
);


-- ============================================
-- CART TABLE
-- Stores products added to customer carts
-- ============================================

CREATE TABLE IF NOT EXISTS cart (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL DEFAULT 1,

    FOREIGN KEY (user_id)
        REFERENCES users(id),

    FOREIGN KEY (product_id)
        REFERENCES product(id)
);


-- ============================================
-- FAVORITES TABLE
-- Stores products liked by customers
-- ============================================

CREATE TABLE IF NOT EXISTS favorites (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,

    UNIQUE (user_id, product_id),

    FOREIGN KEY (user_id)
        REFERENCES users(id),

    FOREIGN KEY (product_id)
        REFERENCES product(id)
);


-- ============================================
-- SAMPLE ADMIN ACCOUNT
-- ============================================

INSERT IGNORE INTO users
(name, email, password, role)
VALUES
('Admin', 'admin@charumart.com', 'admin123', 'ADMIN');


-- ============================================
-- SAMPLE CUSTOMER ACCOUNT
-- ============================================

INSERT IGNORE INTO users
(name, email, password, role)
VALUES
('Test Customer', 'customer@charumart.com', 'customer123', 'CUSTOMER');


-- ============================================
-- SAMPLE PRODUCT
-- ============================================

INSERT IGNORE INTO product
(name, price, description, image_url, quantity)
VALUES
(
    'Test Product',
    100.00,
    'This is a test product',
    'test.jpg',
    10
);


-- ============================================
-- CHECK DATABASE
-- ============================================

SHOW TABLES;


