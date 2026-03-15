SET NOCOUNT ON;
GO

USE [JogosOlimpicos];
GO

IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'idx_Atleta_nome' AND object_id = OBJECT_ID(N'dbo.Atleta'))
    CREATE NONCLUSTERED INDEX idx_Atleta_nome ON dbo.Atleta (nome);
GO

IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'idx_Atleta_id_comite_olimpico' AND object_id = OBJECT_ID(N'dbo.Atleta'))
    CREATE NONCLUSTERED INDEX idx_Atleta_id_comite_olimpico ON dbo.Atleta (id_comite_olimpico);
GO

IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'idx_Evento_id_estadio' AND object_id = OBJECT_ID(N'dbo.Evento'))
    CREATE NONCLUSTERED INDEX idx_Evento_id_estadio ON dbo.Evento (id_estadio);
GO

IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'idx_Evento_id_modalidade' AND object_id = OBJECT_ID(N'dbo.Evento'))
    CREATE NONCLUSTERED INDEX idx_Evento_id_modalidade ON dbo.Evento (id_modalidade);
GO

IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'idx_Evento_data' AND object_id = OBJECT_ID(N'dbo.Evento'))
    CREATE NONCLUSTERED INDEX idx_Evento_data ON dbo.Evento (data_evento);
GO

IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'idx_Ingresso_id_evento' AND object_id = OBJECT_ID(N'dbo.Ingresso'))
    CREATE NONCLUSTERED INDEX idx_Ingresso_id_evento ON dbo.Ingresso (id_evento);
GO

IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'idx_Estadio_capacidade' AND object_id = OBJECT_ID(N'dbo.Estadio'))
    CREATE NONCLUSTERED INDEX idx_Estadio_capacidade ON dbo.Estadio (capacidade DESC);
GO

IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'idx_Arbitro_nacionalidade' AND object_id = OBJECT_ID(N'dbo.Arbitro'))
    CREATE NONCLUSTERED INDEX idx_Arbitro_nacionalidade ON dbo.Arbitro (nacionalidade);
GO

IF OBJECT_ID(N'dbo.v_Atletas_ComiteNacional', N'V') IS NOT NULL
    DROP VIEW dbo.v_Atletas_ComiteNacional;
GO

CREATE VIEW dbo.v_Atletas_ComiteNacional
WITH SCHEMABINDING
AS
SELECT A.numero_passaporte,
       A.nome AS nome_atleta,
       A.sexo,
       A.data_nascimento,
       A.peso,
       A.altura,
       CN.nome AS nome_comite_nacional
FROM dbo.Atleta A
INNER JOIN dbo.ComiteOlimpicoNacional CN
    ON A.id_comite_olimpico = CN.id_comite_olimpico;
GO

IF OBJECT_ID(N'dbo.v_Eventos_Estadio', N'V') IS NOT NULL
    DROP VIEW dbo.v_Eventos_Estadio;
GO

CREATE VIEW dbo.v_Eventos_Estadio
WITH SCHEMABINDING
AS
SELECT E.id_evento,
       E.nome AS nome_evento,
       E.data_evento AS data,
       E.horario_inicio,
       E.horario_termino,
       M.nome AS modalidade,
       S.nome AS nome_estadio,
       S.endereco,
       S.capacidade
FROM dbo.Evento E
INNER JOIN dbo.Estadio S ON E.id_estadio = S.id_estadio
INNER JOIN dbo.Modalidade M ON E.id_modalidade = M.id_modalidade;
GO

IF OBJECT_ID(N'dbo.v_Eventos_ComiteInternacional', N'V') IS NOT NULL
    DROP VIEW dbo.v_Eventos_ComiteInternacional;
GO

CREATE VIEW dbo.v_Eventos_ComiteInternacional
WITH SCHEMABINDING
AS
SELECT E.id_evento,
       E.nome AS nome_evento,
       E.data_evento AS data,
       E.horario_inicio,
       E.horario_termino,
       M.nome AS modalidade,
       S.nome AS nome_estadio,
       S.capacidade,
       COI.nome AS nome_comite_internacional
FROM dbo.Evento E
INNER JOIN dbo.Estadio S ON E.id_estadio = S.id_estadio
INNER JOIN dbo.ComiteOlimpicoNacional CON ON S.id_comite_olimpico = CON.id_comite_olimpico
INNER JOIN dbo.ComiteOlimpicoInternacional COI ON CON.id_comite_olimpico_internacional = COI.id_comite_olimpico;
GO
