create table message(
    id bigint not null auto_increment,
    titulo varchar(50) not null,
    text varchar(300) not null,
    data_criacao datetime not null,
    status varchar(20) not null,
    anime_id bigint not null,
    autor_id bigint not null,
    primary key(id),
    foreign key(anime_id) references anime(id),
    foreign key(autor_id) references usuario(id)
);