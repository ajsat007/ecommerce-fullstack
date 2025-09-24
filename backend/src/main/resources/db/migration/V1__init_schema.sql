-- Flyway V1: initial schema
CREATE TABLE roles (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL UNIQUE
);
CREATE TABLE users (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  email VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  name VARCHAR(255),
  enabled BOOLEAN DEFAULT TRUE
);
CREATE TABLE user_roles (
  user_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  PRIMARY KEY (user_id, role_id),
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (role_id) REFERENCES roles(id)
);
CREATE TABLE categories (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL UNIQUE
);
CREATE TABLE products (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255),
  description TEXT,
  price DECIMAL(12,2),
  stock INT,
  category_id BIGINT,
  image_url VARCHAR(1024),
  FOREIGN KEY (category_id) REFERENCES categories(id)
);
CREATE TABLE cart (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id)
);
CREATE TABLE cart_items (
  cart_id BIGINT,
  product_id BIGINT,
  quantity INT,
  PRIMARY KEY(cart_id, product_id),
  FOREIGN KEY(cart_id) REFERENCES cart(id),
  FOREIGN KEY(product_id) REFERENCES products(id)
);
CREATE TABLE orders (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT,
  total DECIMAL(12,2),
  status VARCHAR(50),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id)
);
CREATE TABLE order_items (
  order_id BIGINT,
  product_id BIGINT,
  quantity INT,
  price DECIMAL(12,2),
  PRIMARY KEY(order_id, product_id),
  FOREIGN KEY(order_id) REFERENCES orders(id),
  FOREIGN KEY(product_id) REFERENCES products(id)
);
CREATE TABLE payments (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  order_id BIGINT,
  provider VARCHAR(255),
  amount DECIMAL(12,2),
  status VARCHAR(50),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY(order_id) REFERENCES orders(id)
);
