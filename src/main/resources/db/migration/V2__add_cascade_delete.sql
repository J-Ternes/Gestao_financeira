-- Remove as constraints antigas
ALTER TABLE lancamento DROP CONSTRAINT fk_lancamento_usuario;
ALTER TABLE lancamento DROP CONSTRAINT fk_lancamento_categoria;
ALTER TABLE categoria  DROP CONSTRAINT fk_categoria_usuario;

-- Recria com ON DELETE CASCADE
ALTER TABLE categoria ADD CONSTRAINT fk_categoria_usuario FOREIGN KEY (usuario_id) REFERENCES users(id) ON DELETE CASCADE;

ALTER TABLE lancamento ADD CONSTRAINT fk_lancamento_usuario FOREIGN KEY (usuario_id) REFERENCES users(id) ON DELETE CASCADE;

ALTER TABLE lancamento ADD CONSTRAINT fk_lancamento_categoria FOREIGN KEY (categoria_id) REFERENCES categoria(id) ON DELETE CASCADE;