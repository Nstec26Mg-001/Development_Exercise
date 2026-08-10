-- DDL for fullness_ec database

CREATE TABLE department (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE employee (
    id SERIAL PRIMARY KEY,
    department_id INT NOT NULL REFERENCES department(id),
    name VARCHAR(100) NOT NULL,
    name_kana VARCHAR(100) NOT NULL
);

CREATE TABLE employee_account (
    id SERIAL PRIMARY KEY,
    employee_id INT NOT NULL REFERENCES employee(id),
    name VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN DEFAULT TRUE
);

CREATE TABLE product_category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    product_category_id INT NOT NULL REFERENCES product_category(id),
    name VARCHAR(200) NOT NULL,
    price INT NOT NULL,
    image_url VARCHAR(500),
    delete_flag INT DEFAULT 0
);

CREATE TABLE product_stock (
    id SERIAL PRIMARY KEY,
    product_id INT NOT NULL REFERENCES product(id),
    quantity INT NOT NULL DEFAULT 0
);

CREATE TABLE order_status (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE payment_method (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE customer (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    name_kana VARCHAR(100) NOT NULL,
    address1 VARCHAR(255) NOT NULL,
    address2 VARCHAR(255),
    phone_number VARCHAR(20) NOT NULL,
    mail_address VARCHAR(255) NOT NULL,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    customer_id INT NOT NULL REFERENCES customer(id),
    order_status_id INT NOT NULL REFERENCES order_status(id),
    payment_method_id INT NOT NULL REFERENCES payment_method(id),
    amount_total INT NOT NULL
);

CREATE TABLE order_detail (
    id SERIAL PRIMARY KEY,
    order_id INT NOT NULL REFERENCES orders(id),
    product_id INT NOT NULL REFERENCES product(id),
    customer_id INT NOT NULL REFERENCES customer(id),
    count INT NOT NULL
);
