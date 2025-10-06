-- Meals - id, name, price

create table meals(
    id serial primary key,
    name text not null,
    price numeric(15,2) not null
);

create table menus(
    id serial primary key,
    name text not null unique,
    price numeric(15,2) not null
);

create table menu_meals(
    menu_id int references menus(id),
    meal_id int references meals(id)
);

insert into meals(name, price) 
values('Hot Dog', 120), ('Pop Corn', 150), ('Nachos', 180);

insert into menus(name, price)
values('Menu PC-HD', 250), ('Menu N-HD', 280);

insert into menu_meals(menu_id, meal_id)
values(
    (select id from menus where name = 'Menu PC-HD'), 
    (select id from meals where name = 'Pop Corn')
    ),
    (
    (select id from menus where name = 'Menu PC-HD'), 
    (select id from meals where name = 'Hot Dog')
    ),
    (
    (select id from menus where name = 'Menu N-HD'), 
    (select id from meals where name = 'Nachos')
    ),
    (
    (select id from menus where name = 'Menu N-HD'), 
    (select id from meals where name = 'Hot Dog')
    );

    select * from menus
    left join menu_meals
    on menus.id = menu_meals.menu_id
    left join meals
    on menu_meals.meal_id = meals.id;