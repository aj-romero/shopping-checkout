# Create tables
CREATE TABLE IF NOT EXISTS products
(
    id BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
    code VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(50) NOT NULL,
    stock INT DEFAULT 0 NOT NULL
        CHECK (stock >= 0),
    price DOUBLE DEFAULT 0 NOT NULL
        CHECK (price >=0),
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS customers
(
    id VARCHAR(50) NOT NULL,
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(50),
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS customer__addresses
(
    id BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
    address_name VARCHAR(50),
    id_customer VARCHAR(50) NOT NULL,
    state VARCHAR(50),
    city VARCHAR(50),
    street VARCHAR(100),
    reference_phone VARCHAR(30),
    house_number VARCHAR(10),
    reference_name VARCHAR(50),
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS checkouts
(
    id BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
    payment VARCHAR(255),
    status VARCHAR(10),
    created_at DATE,
    updated_at DATE,
    id_customer VARCHAR(50),
    id_customer_billing BIGINT,
    id_payment BIGINT,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS checkout__products
(
    id BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
    id_product BIGINT NOT NULL,
    id_checkout BIGINT NOT NULL,
    quantity INT DEFAULT 1 NOT NULL
        CHECK (quantity >= 1),
    price DOUBLE DEFAULT 0.1 NOT NULL
        CHECK (price > 0),
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS payment__methods
(
    id BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
    method_name VARCHAR(50) NOT NULL UNIQUE,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS order__shipping
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    id_address BIGINT NOT NULL,
    id_checkout BIGINT NOT NULL,
    PRIMARY KEY(id)
);


# Create FKs
ALTER TABLE customer__addresses
    ADD    FOREIGN KEY (id_customer)
    REFERENCES customers(id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
;

ALTER TABLE checkouts
    ADD CONSTRAINT FK_pay
    FOREIGN KEY (id_customer)
    REFERENCES customers(id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
;

ALTER TABLE checkouts
    ADD    FOREIGN KEY (id_customer_billing)
    REFERENCES customer__addresses(id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
;

ALTER TABLE checkout__products
    ADD    FOREIGN KEY (id_product)
    REFERENCES products(id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
;

ALTER TABLE checkout__products
    ADD    FOREIGN KEY (id_checkout)
    REFERENCES checkouts(id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
;

ALTER TABLE checkouts
    ADD    FOREIGN KEY (id_payment)
    REFERENCES payment__methods(id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
;

ALTER TABLE order__shipping
    ADD    FOREIGN KEY (id_address)
    REFERENCES customer__addresses(id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
;

ALTER TABLE order__shipping
    ADD    FOREIGN KEY (id_checkout)
    REFERENCES checkouts(id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
;
