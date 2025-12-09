-- Employee - id, name, last name, age, position, salary, phone number, email

create type position_type as enum('OWNER', 'MANAGER','PART_TIME_WORKER','CLEANING_LADY');

create table employees(
    id serial primary key,
    name text not null,
    last_name text not null,
    date_of_birth date not null,
    position position_type not null,
    salary numeric(15,2) not null,
    phone_number text not null,
    email text not null,
    adress text null
);

insert into employees(name, last_name, salary, date_of_birth, position, phone_number, email, adress)
values('David', 'Širůček', 100000, '1994-05-03', 'OWNER', '+420 737 943 315', 'davidsirucek@seznam.cz', 'U Cihelny 391, Jevíčko 569 43'),
('Vítek', 'Dvořák', 75000, '2000-05-07', 'MANAGER', '+420 730 674 812', 'vitekdvorak70@gmail.com', null);
