На регистрацию продавца
Hibernate:
select
    se1_0.phone_number,
    se1_0.email,
    se1_0.fio,
    se1_0.inn,
    se1_0.password,
    se1_0.role,
    c1_0.seller_phone,
    c1_0.id,
    c1_0.auto_number,
    c1_0.car_condition,
    c1_0.km,
    c1_0.mark_and_model_name,
    c1_0.price,
    c1_0.year
from
    seller se1_0
        left join
    cars c1_0
    on se1_0.phone_number=c1_0.seller_phone
where
    se1_0.phone_number=?

Вставка покупателя
Hibernate:
insert
into
    seller
(email, fio, inn, password, role, phone_number)
values
    (?, ?, ?, ?, ?, ?)
Поиск покупателя по номеру телефона
Hibernate:
select
    se1_0.phone_number,
    se1_0.email,
    se1_0.fio,
    se1_0.inn,
    se1_0.password,
    se1_0.role
from
    seller se1_0
where
    se1_0.phone_number=?

На регистрацию покупателя
Hibernate:
select
    be1_0.phone_number,
    be1_0.email,
    be1_0.fio,
    be1_0.passport_number,
    be1_0.password,
    be1_0.role,
    o1_0.buyer_phone,
    o1_0.order_number,
    o1_0.auto_number,
    o1_0.date,
    o1_0.initial_bid,
    o1_0.seller_phone,
    o1_0.status,
    o1_0.time
from
    buyer be1_0
        left join
    orders o1_0
    on be1_0.phone_number=o1_0.buyer_phone
where
    be1_0.phone_number=?

    Hibernate:
insert
into
    buyer
(email, fio, passport_number, password, role, phone_number)
values
    (?, ?, ?, ?, ?, ?)

Поиск продавца по номеру телефона
Hibernate:
select
    be1_0.phone_number,
    be1_0.email,
    be1_0.fio,
    be1_0.passport_number,
    be1_0.password,
    be1_0.role
from
    buyer be1_0
where
    be1_0.phone_number=?


Поиск продавца по емайл для открытия главной страницы
Hibernate:
select
    se1_0.phone_number,
    se1_0.email,
    se1_0.fio,
    se1_0.inn,
    se1_0.password,
    se1_0.role
from
    seller se1_0
where
    se1_0.email=?

Запрос на поиск всех автомобилей продавца для главной страницы совпадающие по номеру телефона
Hibernate:
select
    ce1_0.id,
    ce1_0.auto_number,
    ce1_0.car_condition,
    ce1_0.km,
    ce1_0.mark_and_model_name,
    ce1_0.price,
    ce1_0.seller_phone,
    ce1_0.year
from
    cars ce1_0
        left join
    seller s1_0
    on s1_0.phone_number=ce1_0.seller_phone
where
    s1_0.phone_number=?

Запрос на добавление автомобиля для покупателя
Hibernate:
select
    null,
    se1_0.email,
    se1_0.fio,
    se1_0.inn,
    se1_0.password,
    se1_0.role
from
    seller se1_0
where
    se1_0.phone_number=?
    Hibernate:
insert
into
    cars
(auto_number, car_condition, km, mark_and_model_name, price, seller_phone, year)
values
    (?, ?, ?, ?, ?, ?, ?)

    Запрос на удаление автомобиля
    Hibernate:
delete
from
    cars
where
    id=?

Выбор автомобиля по номеру авто
Hibernate:
select
    ce1_0.id,
    ce1_0.auto_number,
    ce1_0.car_condition,
    ce1_0.km,
    ce1_0.mark_and_model_name,
    ce1_0.price,
    ce1_0.seller_phone,
    ce1_0.year
from
    cars ce1_0
where
    ce1_0.auto_number=?

Выбор покупателя по номеру телефона
Hibernate:
select
    se1_0.phone_number,
    se1_0.email,
    se1_0.fio,
    se1_0.inn,
    se1_0.password,
    se1_0.role
from
    seller se1_0
where
    se1_0.phone_number=?

ПОИСК СТАВОК
Hibernate:
select
    oe1_0.order_number,
    oe1_0.auto_number,
    oe1_0.buyer_phone,
    oe1_0.date,
    oe1_0.initial_bid,
    oe1_0.seller_phone,
    oe1_0.status,
    oe1_0.time
from
    orders oe1_0
where
    oe1_0.auto_number=?

УДАЛЕНИЕ ПРОДАВЦА Hibernate:
delete
from
    seller
where
    phone_number=?

УДАЛЕНИЕ ПОКУПАТЕЛЯ
Hibernate:
delete
from
    buyer
where
    phone_number=?


ПОИСК СТАВОК ДЛЯ ПРОДАВЦА
Hibernate:
select
    oe1_0.order_number,
    oe1_0.auto_number,
    oe1_0.buyer_phone,
    oe1_0.date,
    oe1_0.initial_bid,
    oe1_0.seller_phone,
    oe1_0.status,
    oe1_0.time
from
    orders oe1_0
where
    oe1_0.seller_phone=?
  and oe1_0.status=1
order by
    cast(oe1_0.initial_bid as signed) desc

Поиск автомобилей для покупателя
Hibernate:
select
    ae1_0.id,
    ae1_0.auto_number
from
    auction ae1_0

поиск ставок для покупателя
Hibernate:
select
    oe1_0.order_number,
    oe1_0.auto_number,
    oe1_0.buyer_phone,
    oe1_0.date,
    oe1_0.initial_bid,
    oe1_0.seller_phone,
    oe1_0.status,
    oe1_0.time
from
    orders oe1_0
where
    oe1_0.buyer_phone=?
  and oe1_0.status=1
order by
    cast(oe1_0.initial_bid as signed) desc

ВСТАВКА СТАВКИ
Hibernate:
update
    orders_seq
set
    next_val= ?
where
    next_val=?
    Hibernate:
insert
into
    orders
(auto_number, buyer_phone, date, initial_bid, seller_phone, status, time, order_number)
values
    (?, ?, ?, ?, ?, ?, ?, ?)

зАПРОС НА ОБНОВЛЕНИЕ СТАВКИ
Hibernate:
update
    orders
set
    status=?
where
    order_number=?

ЗАПРОС НА ПОИСК ПОКУПАТЕЛЯ С ВЫЙГРАШНОЙ СТАВКОЙ
Hibernate:
select
    oe1_0.order_number,
    oe1_0.auto_number,
    oe1_0.buyer_phone,
    oe1_0.date,
    oe1_0.initial_bid,
    oe1_0.seller_phone,
    oe1_0.status,
    oe1_0.time
from
    orders oe1_0
where
    oe1_0.order_number=?
