BEGIN;

CREATE TABLE proprietario (
    cod_proprietario INTEGER      NOT NULL,
    nome             VARCHAR(200) NOT NULL,
    cidade           VARCHAR(100) NOT NULL,
    estado           CHAR(2)      NOT NULL,

    CONSTRAINT pk_proprietario PRIMARY KEY (cod_proprietario),
    CONSTRAINT chk_proprietario_estado CHECK (estado ~ '^[A-Z]{2}$')
);

CREATE INDEX ix_proprietario_estado ON proprietario (estado);
CREATE INDEX ix_proprietario_cidade ON proprietario (cidade);
CREATE INDEX ix_proprietario_estado_cidade ON proprietario (estado, cidade);

CREATE TABLE veiculo (
    placa            VARCHAR(8)   NOT NULL,
    modelo           VARCHAR(120) NOT NULL,
    ano_fabricacao   SMALLINT     NOT NULL,
    valor_ipva       NUMERIC(10,2) NULL,
    cod_proprietario INTEGER      NULL,

    CONSTRAINT pk_veiculo PRIMARY KEY (placa),
    CONSTRAINT fk_veiculo_proprietario
        FOREIGN KEY (cod_proprietario)
        REFERENCES proprietario (cod_proprietario)
        ON DELETE SET NULL
        ON UPDATE CASCADE,
    CONSTRAINT chk_veiculo_ano CHECK (ano_fabricacao >= 1900 AND ano_fabricacao <= 2100),
    CONSTRAINT chk_veiculo_valor_ipva CHECK (valor_ipva IS NULL OR valor_ipva >= 0)
);

CREATE INDEX ix_veiculo_cod_proprietario ON veiculo (cod_proprietario)
    WHERE cod_proprietario IS NOT NULL;

CREATE INDEX ix_veiculo_modelo ON veiculo (modelo);

CREATE INDEX ix_veiculo_ano_fabricacao ON veiculo (ano_fabricacao);

CREATE INDEX ix_veiculo_valor_ipva ON veiculo (valor_ipva)
    WHERE valor_ipva IS NOT NULL;

CREATE INDEX ix_veiculo_modelo_valor_ipva ON veiculo (modelo, valor_ipva)
    WHERE valor_ipva IS NOT NULL;

COMMIT;
