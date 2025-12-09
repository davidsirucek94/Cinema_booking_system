create table orders(
    id serial primary key not null,
    number int not null
);

create table order_items(
    id serial primary key not null,
    description text not null,
    price numeric(15,2) not null,
    order_id int not null references orders(id)
);

alter table orders add column creation_date_time timestamp not null;