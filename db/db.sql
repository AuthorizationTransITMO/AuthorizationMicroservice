CREATE DATABASE checking_list ENCODING 'UTF-8';

CREATE TABLE customers (
account_number varchar(20),
data varchar(7),
balance integer,
first_name varchar(80),
second_name varchar(80),
middle_name varchar(80)
);

INSERT INTO customers (account_number, data, balance, first_name, second_name, middle_name) VALUES ('1111222233334444', '12/2013', 100500, 'IVAN', 'IVANOV', 'IVANOVICH');
INSERT INTO customers (account_number, data, balance, first_name, second_name, middle_name) VALUES ('9999888877776666', '07/2015', 80900, 'OLEG', 'TINKOV', 'YURIVICH');
INSERT INTO customers (account_number, data, balance, first_name, second_name, middle_name) VALUES ('9191282873736464', '04/2019', 403567894, 'JASON', 'STATHAM', 'AMERICANOVICH');