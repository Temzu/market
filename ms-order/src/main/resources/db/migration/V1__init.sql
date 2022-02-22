create table orders
(
    id         bigserial primary key,
    user_id    bigint NOT NULL,
    price      int NOT NULL,
    address    varchar(255) NOT NULL,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table order_items
(
    id                bigserial primary key,
    order_id          bigint references orders (id),
    product_id        bigint,
    title             varchar(255),
    quantity          int,
    price_per_product int,
    price             int,
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
);

create table carts
(
    id      UUID primary key,
    user_id bigint,
    price   int
);

create table cart_items
(
    id                bigserial primary key,
    cart_id           UUID references carts (id),
    product_id        bigint,
    title             varchar(255),
    quantity          int,
    price_per_product int,
    price             int,
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
);

insert into orders (user_id, address, price)
values (1, 'user 1 address', 24),
       (2, 'user 2 address', 65),
       (3, 'user 3 address', 320),
       (4, 'user 4 address', 322),
       (1, 'user 1 address', 323),
       (2, 'user 2 address', 324);

-- insert into carts (user_id, price)
-- values (1, 24),
--        (2, 65),
--        (3, 320),
--        (4, 322);