create table respuestas(
    id int not null auto_increment,
    mensaje varchar(150) not null,
    fecha_creacion datetime not null,
    autor_id int not null,
    topico_id int not null,

    primary key(id)
);