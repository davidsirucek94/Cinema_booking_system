alter table employees add column login text not null unique;
alter table employees add column password_hash text not null;

insert into employees(name, last_name, salary, date_of_birth, position, phone_number, email, adress, login, password_hash)
values('David', 'Širůček', 100000, '1994-05-03', 'OWNER', '+420 737 943 315', 'davidsirucek@seznam.cz', 'U Cihelny 391, Jevíčko 569 43', 'david', 'david'),
('Vítek', 'Dvořák', 75000, '2000-05-07', 'MANAGER', '+420 730 674 812', 'vitekdvorak70@gmail.com', null, 'vitek', 'vitek');