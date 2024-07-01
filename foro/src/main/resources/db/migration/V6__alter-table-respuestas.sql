ALTER TABLE respuestas
ADD CONSTRAINT fk_respuestas_topico_id
FOREIGN KEY (topico_id) REFERENCES topicos(id),
ADD CONSTRAINT fk_respuestas_autor_id
FOREIGN KEY (autor_id) REFERENCES usuarios(id);
