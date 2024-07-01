CREATE TABLE usuarios_perfiles (
    id int not null auto_increment,
    usuario_id int NOT NULL,
    perfil_id int NOT NULL,
    PRIMARY KEY (id),
    constraint fk_usuarios_perfiles_usuario_id foreign key(usuario_id) references usuarios(id),
    constraint fk_usuarios_perfiles_perfil_id foreign key(perfil_id) references perfiles(id)
);