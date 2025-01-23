CREATE TABLE IF NOT EXISTS buyer
(
    phone_number SERIAL PRIMARY KEY,
    fio VARCHAR(128) NOT NULL ,
    passport_number BIGINT NOT NULL UNIQUE,
    email VARCHAR(192) NOT NULL UNIQUE,
    password VARCHAR(128),
#     order_number BIGINT REFERENCES orders(order_number),
    role ENUM('seller','buyer') NOT NULL
);

CREATE TABLE IF NOT EXISTS seller
(
    phone_number SERIAL PRIMARY KEY,
    fio VARCHAR(128) NOT NULL,
    inn BIGINT NOT NULL UNIQUE,
    email VARCHAR(192) NOT NULL UNIQUE,
    password VARCHAR(128),
#     auto_number BIGINT NOT NULL REFERENCES cars(auto_number),
    role ENUM('seller','buyer') NOT NULL
);


# CREATE TABLE IF NOT EXISTS cars
# (
#     auto_number SERIAL PRIMARY KEY,
#     mark_and_model_name VARCHAR(128) NOT NULL,
#     year YEAR NOT NULL,
#     km VARCHAR(128) NOT NULL,
#     car_condition VARCHAR(128) NOT NULL,
#     price BIGINT NOT NULL
# );
#
# CREATE TABLE IF NOT EXISTS orders
# (
#     order_number SERIAL PRIMARY KEY,
#     auto_number BIGINT NOT NULL REFERENCES cars(auto_number),
#     seller_phone BIGINT NOT NULL,
#     buyer_phone BIGINT NOT NULL,
#     initial_bid  VARCHAR(128) NOT NULL,
#     date  DATE NOT NULL,
#     time  TIME NOT NULL
# );