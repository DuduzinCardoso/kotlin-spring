create table resposta(
    id bigint not null auto_increment,
    mensagem varchar(300) not null,
    data_criacao datetime not null,
    message_id bigint not null,
    autor_id bigint not null,
    primary key(id),
    foreign key(message_id) references message(id),
    foreign key(autor_id) references usuario(id)
);