SET NOCOUNT ON;
GO

USE [master];
GO

IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = N'JogosOlimpicos')
BEGIN
    CREATE DATABASE [JogosOlimpicos]
    COLLATE Latin1_General_CI_AI;
END
GO

USE [JogosOlimpicos];
GO

ALTER DATABASE [JogosOlimpicos] SET COMPATIBILITY_LEVEL = 150;
ALTER DATABASE [JogosOlimpicos] SET AUTO_UPDATE_STATISTICS ON;
GO

CREATE TABLE dbo.ComiteOlimpicoInternacional (
    id_comite_olimpico INT NOT NULL,
    nome               NVARCHAR(100) NOT NULL,
    CONSTRAINT PK_ComiteOlimpicoInternacional PRIMARY KEY (id_comite_olimpico)
);
GO

CREATE TABLE dbo.ComiteOlimpicoNacional (
    id_comite_olimpico              INT NOT NULL,
    nome                            NVARCHAR(100) NOT NULL,
    bandeira                        NVARCHAR(255) NULL,
    id_comite_olimpico_internacional INT NULL,
    CONSTRAINT PK_ComiteOlimpicoNacional PRIMARY KEY (id_comite_olimpico),
    CONSTRAINT FK_ComiteOlimpicoNacional_Internacional
        FOREIGN KEY (id_comite_olimpico_internacional)
        REFERENCES dbo.ComiteOlimpicoInternacional (id_comite_olimpico)
);
GO

CREATE TABLE dbo.Estadio (
    id_estadio         INT NOT NULL,
    nome               NVARCHAR(100) NOT NULL,
    endereco           NVARCHAR(200) NOT NULL,
    capacidade         INT NOT NULL,
    id_comite_olimpico INT NULL,
    CONSTRAINT PK_Estadio PRIMARY KEY (id_estadio),
    CONSTRAINT FK_Estadio_ComiteNacional
        FOREIGN KEY (id_comite_olimpico)
        REFERENCES dbo.ComiteOlimpicoNacional (id_comite_olimpico),
    CONSTRAINT CK_Estadio_Capacidade CHECK (capacidade > 0)
);
GO

CREATE TABLE dbo.Atleta (
    numero_passaporte   INT NOT NULL,
    nome               NVARCHAR(100) NOT NULL,
    peso               DECIMAL(6,2) NOT NULL,
    data_nascimento    DATE NOT NULL,
    sexo               CHAR(1) NOT NULL,
    tipo_sanguineo     VARCHAR(4) NULL,
    altura             DECIMAL(5,2) NOT NULL,
    id_comite_olimpico INT NOT NULL,
    CONSTRAINT PK_Atleta PRIMARY KEY (numero_passaporte),
    CONSTRAINT FK_Atleta_ComiteNacional
        FOREIGN KEY (id_comite_olimpico)
        REFERENCES dbo.ComiteOlimpicoNacional (id_comite_olimpico),
    CONSTRAINT CK_Atleta_Peso CHECK (peso > 0),
    CONSTRAINT CK_Atleta_Altura CHECK (altura > 0),
    CONSTRAINT CK_Atleta_Sexo CHECK (sexo IN ('M', 'F'))
);
GO

CREATE TABLE dbo.Arbitro (
    id_arbitro      INT NOT NULL,
    nome            NVARCHAR(100) NOT NULL,
    sexo            CHAR(1) NOT NULL,
    data_nascimento DATE NOT NULL,
    nacionalidade   NVARCHAR(50) NOT NULL,
    CONSTRAINT PK_Arbitro PRIMARY KEY (id_arbitro),
    CONSTRAINT CK_Arbitro_Sexo CHECK (sexo IN ('M', 'F'))
);
GO

CREATE TABLE dbo.Esporte (
    id_esporte        INT NOT NULL,
    nome              NVARCHAR(50) NOT NULL,
    genero_masculino  BIT NOT NULL,
    CONSTRAINT PK_Esporte PRIMARY KEY (id_esporte)
);
GO

CREATE TABLE dbo.Modalidade (
    id_modalidade INT NOT NULL,
    nome          NVARCHAR(50) NOT NULL,
    ind_coletivo  BIT NOT NULL,
    id_esporte    INT NOT NULL,
    CONSTRAINT PK_Modalidade PRIMARY KEY (id_modalidade),
    CONSTRAINT FK_Modalidade_Esporte
        FOREIGN KEY (id_esporte)
        REFERENCES dbo.Esporte (id_esporte)
);
GO

CREATE TABLE dbo.Evento (
    id_evento         INT IDENTITY(1,1) NOT NULL,
    nome              NVARCHAR(100) NOT NULL,
    data_evento       DATE NOT NULL,
    horario_inicio    DATETIME2(0) NOT NULL,
    horario_termino   DATETIME2(0) NOT NULL,
    id_modalidade     INT NOT NULL,
    id_estadio        INT NOT NULL,
    CONSTRAINT PK_Evento PRIMARY KEY (id_evento),
    CONSTRAINT FK_Evento_Modalidade
        FOREIGN KEY (id_modalidade)
        REFERENCES dbo.Modalidade (id_modalidade),
    CONSTRAINT FK_Evento_Estadio
        FOREIGN KEY (id_estadio)
        REFERENCES dbo.Estadio (id_estadio),
    CONSTRAINT CK_Evento_Horario CHECK (horario_termino > horario_inicio)
);
GO

CREATE TABLE dbo.Arbitragem (
    id_esporte INT NOT NULL,
    id_arbitro INT NOT NULL,
    CONSTRAINT PK_Arbitragem PRIMARY KEY (id_esporte, id_arbitro),
    CONSTRAINT FK_Arbitragem_Esporte
        FOREIGN KEY (id_esporte)
        REFERENCES dbo.Esporte (id_esporte),
    CONSTRAINT FK_Arbitragem_Arbitro
        FOREIGN KEY (id_arbitro)
        REFERENCES dbo.Arbitro (id_arbitro)
);
GO

CREATE TABLE dbo.Competicao (
    numero_passaporte_atleta INT NOT NULL,
    id_modalidade            INT NOT NULL,
    indice_olimpico          DECIMAL(10,2) NOT NULL,
    CONSTRAINT PK_Competicao PRIMARY KEY (numero_passaporte_atleta, id_modalidade),
    CONSTRAINT FK_Competicao_Atleta
        FOREIGN KEY (numero_passaporte_atleta)
        REFERENCES dbo.Atleta (numero_passaporte),
    CONSTRAINT FK_Competicao_Modalidade
        FOREIGN KEY (id_modalidade)
        REFERENCES dbo.Modalidade (id_modalidade)
);
GO

CREATE TABLE dbo.Torcedor (
    passaporte           INT NOT NULL,
    pais                 NVARCHAR(50) NOT NULL,
    num_cartao_credito   VARCHAR(19) NULL,
    endereco_residencial NVARCHAR(200) NULL,
    CONSTRAINT PK_Torcedor PRIMARY KEY (passaporte)
);
GO

CREATE TABLE dbo.Ingresso (
    id_ingresso         INT IDENTITY(1,1) NOT NULL,
    id_evento           INT NOT NULL,
    passaporte_torcedor INT NOT NULL,
    preco               DECIMAL(10,2) NOT NULL,
    setor_assento       NVARCHAR(50) NULL,
    numero_assento      INT NULL,
    CONSTRAINT PK_Ingresso PRIMARY KEY (id_ingresso),
    CONSTRAINT FK_Ingresso_Evento
        FOREIGN KEY (id_evento)
        REFERENCES dbo.Evento (id_evento),
    CONSTRAINT FK_Ingresso_Torcedor
        FOREIGN KEY (passaporte_torcedor)
        REFERENCES dbo.Torcedor (passaporte),
    CONSTRAINT CK_Ingresso_Preco CHECK (preco > 0),
    CONSTRAINT CK_Ingresso_NumeroAssento CHECK (numero_assento IS NULL OR numero_assento > 0)
);
GO

CREATE TABLE dbo.Telefone (
    id_telefone       INT NOT NULL,
    numero            VARCHAR(20) NOT NULL,
    id_comite_olimpico INT NOT NULL,
    CONSTRAINT PK_Telefone PRIMARY KEY (id_telefone),
    CONSTRAINT FK_Telefone_ComiteInternacional
        FOREIGN KEY (id_comite_olimpico)
        REFERENCES dbo.ComiteOlimpicoInternacional (id_comite_olimpico)
);
GO
