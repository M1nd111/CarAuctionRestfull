CREATE TABLE IF NOT EXISTS buyer
(
    phone_number SERIAL PRIMARY KEY,
    fio VARCHAR(128) NOT NULL ,
    passport_number INT NOT NULL UNIQUE,
    email VARCHAR(192) NOT NULL UNIQUE,
    password VARCHAR(64),
    order_number BIGINT REFERENCES orders(order_number)
);

CREATE TABLE IF NOT EXISTS seller
(
    phone_number SERIAL PRIMARY KEY,
    fio VARCHAR(128) NOT NULL,
    inn INT NOT NULL UNIQUE,
    email VARCHAR(192) NOT NULL UNIQUE,
    password VARCHAR(64),
    auto_number BIGINT NOT NULL REFERENCES cars(auto_number)
);


CREATE TABLE IF NOT EXISTS cars
(
    auto_number SERIAL PRIMARY KEY,
    mark_and_model_name VARCHAR(128) NOT NULL,
    year YEAR NOT NULL,
    km VARCHAR(128) NOT NULL,
    feel VARCHAR(128) NOT NULL,
    price BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS orders
(
    order_number SERIAL PRIMARY KEY,
    auto_number BIGINT NOT NULL REFERENCES cars(auto_number),
    seller_phone BIGINT NOT NULL,
    buyer_phone BIGINT NOT NULL,
    order_now  VARCHAR(128) NOT NULL,
    date  DATE NOT NULL,
    time  TIME NOT NULL
);

CREATE TABLE IF NOT EXISTS  company_locales
(
    company_id INT REFERENCES company(id),
    lang VARCHAR(2),
    description VARCHAR(255) NOT NULL,
    PRIMARY KEY (company_id, lang)
);

CREATE TABLE IF NOT EXISTS users
(
    id SERIAL PRIMARY KEY,
    username VARCHAR(64) NOT NULL UNIQUE,
    birth_date DATE,
    firstname VARCHAR(64),
    lastname VARCHAR(64),
    role VARCHAR(32),
    company_id INT REFERENCES company(id)
);

CREATE TABLE IF NOT EXISTS payment
(
    id SERIAL PRIMARY KEY,
    amount INT NOT NULL,
    receiver_id BIGINT NOT NULL REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS chat
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS users_char
(
    id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id),
    chat_id BIGINT NOT NULL REFERENCES chat(id),
    UNIQUE (user_id, chat_id)
);
