create table topicos(
    id int not null auto_increment,
    titulo varchar(150) not null,
    mensaje varchar(150) not null,
    fecha_creacion varchar(6) not null,
    status tinyint not null,
    autor_id int not null,
    curso_id int not null,

    primary key(id),

    constraint fk_topicos_autor_id foreign key(autor_id) references usuarios(id),
    constraint fk_topicos_cursos_id foreign key(curso_id) references cursos(id)
);