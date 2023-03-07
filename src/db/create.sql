create database OneToOne;

use OneToOne;

CREATE TABLE address(
    id int(11) auto_increment primary key,
    street varchar(45),
    city varchar(45)
);

CREATE TABLE user (
    id int(11) auto_increment primary key,
    phone int(11),
    name varchar(45),
    address_id int(11)
);