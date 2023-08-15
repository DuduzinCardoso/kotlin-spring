create table anime(
    id bigint not null auto_increment,
    nome varchar(50) not null,
    categoria varchar(50) not null,
    primary key(id)
);

insert into anime values(1, 'Konosuba', 'Shounnen');
insert into anime values(2, 'Pokemon', 'Aventura');