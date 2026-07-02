CREATE TABLE users (
    id UUID  NOT NULL DEFAULT gen_random_uuid(),
    nome  VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255)  NOT NULL UNIQUE,
    senha  VARCHAR(100)  NOT NULL,
    role VARCHAR(10) NOT NULL,

    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE categoria (
    id UUID NOT NULL DEFAULT gen_random_uuid(),
    categoria VARCHAR(255) NOT NULL,
    usuario_id  UUID,

    CONSTRAINT pk_categoria PRIMARY KEY (id),
    CONSTRAINT fk_categoria_usuario FOREIGN KEY (usuario_id) REFERENCES users (id)
);

CREATE TABLE lancamento (
    id  UUID NOT NULL DEFAULT gen_random_uuid(),
    preco  DECIMAL(10,2)  NOT NULL,
    data_lancamento  DATE NOT NULL,
    tipo  VARCHAR(50),
    criado_em  TIMESTAMP,
    categoria_id UUID,
    usuario_id  UUID,

    CONSTRAINT pk_lancamento PRIMARY KEY (id),

    CONSTRAINT fk_lancamento_categoria FOREIGN KEY (categoria_id) REFERENCES categoria(id),

    CONSTRAINT fk_lancamento_usuario FOREIGN KEY (usuario_id) REFERENCES users(id)
);