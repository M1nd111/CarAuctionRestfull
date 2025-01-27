CREATE TABLE IF NOT EXISTS auction
(
    id SERIAL PRIMARY KEY,
    auto_number VARCHAR(128) NOT NULL UNIQUE

);


CREATE TABLE IF NOT EXISTS buyer
(
    phone_number BIGINT PRIMARY KEY AUTO_INCREMENT,
    fio VARCHAR(128) NOT NULL ,
    passport_number BIGINT NOT NULL UNIQUE,
    email VARCHAR(192) NOT NULL UNIQUE,
    password VARCHAR(128),
    role ENUM('seller','buyer') NOT NULL
);

CREATE TABLE IF NOT EXISTS seller
(
    phone_number BIGINT  PRIMARY KEY AUTO_INCREMENT,
    fio VARCHAR(128) NOT NULL,
    inn BIGINT NOT NULL UNIQUE,
    email VARCHAR(192) NOT NULL UNIQUE,
    password VARCHAR(128),
    role ENUM('seller','buyer') NOT NULL
);


CREATE TABLE IF NOT EXISTS cars
(
    id SERIAL PRIMARY KEY,
    auto_number VARCHAR(128) NOT NULL UNIQUE,
    mark_and_model_name VARCHAR(128) NOT NULL,
    year YEAR NOT NULL,
    km VARCHAR(128) NOT NULL,
    car_condition VARCHAR(128) NOT NULL,
    price BIGINT NOT NULL,
    seller_phone BIGINT UNIQUE REFERENCES seller(phone_number)
);

CREATE TABLE IF NOT EXISTS orders
(
    order_number SERIAL PRIMARY KEY,
    auto_number VARCHAR(128) NOT NULL UNIQUE,
    seller_phone BIGINT NOT NULL,
    buyer_phone BIGINT UNIQUE REFERENCES buyer(phone_number),
    initial_bid  VARCHAR(128) NOT NULL,
    date  DATE NOT NULL,
    time  TIME NOT NULL,
    status BOOLEAN
);