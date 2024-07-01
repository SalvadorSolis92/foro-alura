create table usuarios(
    id int not null auto_increment,
    nombre varchar(150) not null,
    correo_electronico varchar(150) not null,
    contrasenia varchar(150) not null,
    perfiles varchar(150) not null,

    primary key(id)
);