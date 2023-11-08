# Create schemas

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
    id VARCHAR(50) NOT NULL UNIQUE,
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(50),
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS customer__addresses
(
    id BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
    address_name VARCHAR(50) NOT NULL UNIQUE,
    id_customer VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    street VARCHAR(100) NOT NULL,
    reference_phone VARCHAR(30) NOT NULL,
    house_number VARCHAR(10) NOT NULL,
    reference_name VARCHAR(50) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS checkouts
(
    id BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
    payment_code VARCHAR(255),
    ck_status VARCHAR(10),
    created_at DATE,
    updated_at DATE,
    id_customer VARCHAR(50),
    id_payment BIGINT,
    id_shipping_address BIGINT,
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

CREATE TABLE IF NOT EXISTS card__payments
(
    id BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
    id_customer VARCHAR(50) NOT NULL,
    card_number VARCHAR(50) NOT NULL UNIQUE,
    card_holdername VARCHAR(50) NOT NULL,
    expiration_date VARCHAR(5) NOT NULL,
    PRIMARY KEY(id)
);


# Create FKs
ALTER TABLE customer__addresses
    ADD CONSTRAINT FK_customers_addresses
    FOREIGN KEY (id_customer)
    REFERENCES customers(id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
;
    
ALTER TABLE checkouts
    ADD CONSTRAINT FK_customers_checkouts
    FOREIGN KEY (id_customer)
    REFERENCES customers(id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
;
    
ALTER TABLE checkout__products
    ADD CONSTRAINT FK_could_be
    FOREIGN KEY (id_product)
    REFERENCES products(id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
;
    
ALTER TABLE checkout__products
    ADD CONSTRAINT FK_must_have
    FOREIGN KEY (id_checkout)
    REFERENCES checkouts(id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
;
    
ALTER TABLE checkouts
    ADD    FOREIGN KEY (id_payment)
    REFERENCES card__payments(id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
;
    
ALTER TABLE card__payments
    ADD CONSTRAINT FK_customers_cards
    FOREIGN KEY (id_customer)
    REFERENCES customers(id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
;
    
ALTER TABLE checkouts
    ADD CONSTRAINT FK_address_ck
    FOREIGN KEY (id_shipping_address)
    REFERENCES customer__addresses(id)
;
    

# Create Indexes