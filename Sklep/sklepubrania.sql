create database sklepubrania;
use sklepubrania;

#drop database sklepubrania;
create table users(
login varchar(20) unique,
pass varchar(15),
perm varchar(3)
);
insert into users(login,pass,perm) values('user1','pass1','100');
insert into users(login,pass,perm) values('user2','pass2','100');
insert into users(login,pass,perm) values('admin1','pass1','111');
insert into users(login,pass,perm) values('admin2','pass2','111');
#drop table users;

select*from users;
select*from users where login='user1' and pass= 'pass1';

CREATE TABLE ubrania (
    id int primary key auto_increment,
    typ VARCHAR(20),
    opis VARCHAR(80),
    kolor VARCHAR(10),
    cena INTEGER,
    cenaB INTEGER
);
insert into ubrania (typ, opis, kolor, cena, cenaB) values ('dol','zielono-niebieskie spodnie w kwiatki', 'blue', 120, 147);
insert into ubrania (typ, opis, kolor, cena, cenaB) values('dol','niebieska spódnica', 'blue', 90, 110);
insert into ubrania (typ, opis, kolor, cena, cenaB) values('wierzch','zielono-niebieski żakiet w paski', 'blue', 150, 184);
insert into ubrania (typ, opis, kolor, cena, cenaB) values('wierzch','niebieski sweter rozpinany', 'blue', 80, 98);
insert into ubrania (typ, opis, kolor, cena, cenaB) values('gora', 'zielono-niebieska bluzka koszulowa w kratkę', 'green', 50, 61);
insert into ubrania (typ, opis, kolor, cena, cenaB) values('gora','niebieska bluzka koszulowa', 'blue', 50, 61);
insert into ubrania (typ, opis, kolor, cena, cenaB) values('gora','zielono-biała bluzka koszulowa w kwiatki', 'green', 50, 61);
#drop table ubrania;

select opis, kolor, cena, cenaB from ubrania where typ= 'dol';
select opis, kolor, cena, cenaB from ubrania where typ= 'gora';
select opis, kolor, cena, cenaB from ubrania where typ= 'wierzch';

CREATE TABLE zamowienia (
    id_z int primary key auto_increment,
    typ VARCHAR(20),
    opis VARCHAR(80),
    kolor VARCHAR(10),
    wz VARCHAR(3),
    cena INTEGER
);
insert into zamowienia (typ, opis, kolor, wz, cena) values ('dół','zielono-niebieskie spodnie w kwiatki', 'blue', 'wz', 120);
insert into zamowienia (typ, opis, kolor, wz, cena) values('dół','niebieska spódnica', 'blue', 'j', 90);