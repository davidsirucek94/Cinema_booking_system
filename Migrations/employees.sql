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
