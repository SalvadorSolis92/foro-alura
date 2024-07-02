create table usuarios(
    id int not null auto_increment,
    nombre varchar(150) not null,
    correo_electronico varchar(150) not null unique,
    contrasenia varchar(150) not null,

    primary key(id)
);