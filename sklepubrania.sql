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
    cena INTEGER
);
insert into ubrania (typ, opis, kolor, cena) values ('dol','zielono-niebieskie spodnie w kwiatki', 'blue', 147);
insert into ubrania (typ, opis, kolor, cena) values('dol','niebieska spódnica', 'blue', 90);
insert into ubrania (typ, opis, kolor, cena) values('wierzch','zielono-niebieski żakiet w paski', 'blue', 184);
insert into ubrania (typ, opis, kolor, cena) values('wierzch','niebieski sweter rozpinany', 'blue', 80);
insert into ubrania (typ, opis, kolor, cena) values('gora', 'zielono-niebieska bluzka koszulowa w kratkę', 'green', 61);
insert into ubrania (typ, opis, kolor, cena) values('gora','niebieska bluzka koszulowa', 'blue', 75);
insert into ubrania (typ, opis, kolor, cena) values('gora','biała bluzka koszulowa', 'white', 50);
insert into ubrania (typ, opis, kolor, cena) values('wierzch','niebieski sweter rozpinany', 'blue', 80);
insert into ubrania (typ, opis, kolor, cena) values('gora', 'niebieska bluzka koszulowa', 'blue', 61);
insert into ubrania (typ, opis, kolor, cena) values('dol','czarna spódnica', 'blue', 90);
insert into ubrania (typ, opis, kolor, cena) values('gora','zielona bluzka koszulowa', 'green', 60);
insert into ubrania (typ, opis, kolor, cena) values ('dol','czarne spodnie', 'black', 147);
insert into ubrania (typ, opis, kolor, cena) values('dol','czarna spódnica', 'black', 120);
insert into ubrania (typ, opis, kolor, cena) values('wierzch','niebieski żakiet', 'blue', 154);
insert into ubrania (typ, opis, kolor, cena) values('wierzch','czarny sweter rozpinany', 'black', 80);
insert into ubrania (typ, opis, kolor, cena) values('gora', 'szara bluzka koszulowa', 'grey', 75);
insert into ubrania (typ, opis, kolor, cena) values('gora','niebieska bluzka koszulowa w kwiaty', 'blue', 75);
insert into ubrania (typ, opis, kolor, cena) values('gora','biała bluzka koszulowa w kratkę', 'white', 50);
insert into ubrania (typ, opis, kolor, cena) values('wierzch','niebieski sweter', 'blue', 90);
insert into ubrania (typ, opis, kolor, cena) values('gora', 'biała bluzka koszulowa jedwab', 'white', 161);
insert into ubrania (typ, opis, kolor, cena) values('dol','czarna spódnica z guzikami', 'black', 190);
insert into ubrania (typ, opis, kolor, cena) values('gora','zielona bluzka koszulowa w kwiaty', 'green', 30);

select * from ubrania;
select * from zamowienia;
drop table ubrania;

select opis, kolor, cena from ubrania where typ= 'dol';
select opis, kolor, cena from ubrania where typ= 'gora';
select opis, kolor, cena from ubrania where typ= 'wierzch';

CREATE TABLE zamowienia (
    id_z INT PRIMARY KEY AUTO_INCREMENT,
    id int,
    typ VARCHAR(20),
    opis VARCHAR(80),
    kolor VARCHAR(10),
    cena INTEGER,
    FOREIGN KEY (id)
        REFERENCES ubrania (id)
);
insert into zamowienia (id, typ, opis, kolor, cena) values ( 1,'dol','zielono-niebieskie spodnie w kwiatki', 'blue', 147);
insert into zamowienia (typ, opis, kolor, cena) values('dol', 'niebieska spódnica', 'blue', 90);
select* from zamowienia;

select sum(cena) from zamowienia;

delete from zamowienia where id=5;
drop table zamowienia;
