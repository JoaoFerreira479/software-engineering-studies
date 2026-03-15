USE [JogosOlimpicos];
GO

SELECT
    es.nome   AS Estádio,
    ev.nome   AS Evento,
    con.nome  AS Comitê
FROM dbo.Estadio es
INNER JOIN dbo.Evento ev ON ev.id_estadio = es.id_estadio
INNER JOIN dbo.ComiteOlimpicoNacional con ON con.id_comite_olimpico = es.id_comite_olimpico
ORDER BY es.capacidade DESC, es.nome, ev.nome;

SELECT
    ar.nome  AS Arbitro,
    es.nome  AS Esporte,
    mo.nome  AS Modalidade
FROM dbo.Arbitro ar
INNER JOIN dbo.Arbitragem arb ON arb.id_arbitro = ar.id_arbitro
INNER JOIN dbo.Esporte es ON es.id_esporte = arb.id_esporte
INNER JOIN dbo.Modalidade mo ON mo.id_esporte = es.id_esporte
WHERE ar.nacionalidade = N'Espanha'
ORDER BY ar.nome, es.nome, mo.nome;

SELECT
    ev.id_evento,
    ev.nome   AS nome_evento,
    es.nome   AS nome_estadio,
    ev.data_evento AS data,
    coi.nome  AS nome_comite
FROM dbo.Evento ev
INNER JOIN dbo.Estadio es ON es.id_estadio = ev.id_estadio
INNER JOIN dbo.ComiteOlimpicoNacional con ON con.id_comite_olimpico = es.id_comite_olimpico
INNER JOIN dbo.ComiteOlimpicoInternacional coi ON coi.id_comite_olimpico = con.id_comite_olimpico_internacional
WHERE es.nome LIKE N'%Olímpico%'
  AND ev.data_evento BETWEEN '20240715' AND '20240731'
ORDER BY ev.data_evento, ev.nome;

SELECT
    at.numero_passaporte,
    at.nome   AS nome_atleta,
    mo.nome   AS nome_modalidade,
    co.indice_olimpico
FROM dbo.Atleta at
INNER JOIN dbo.Competicao co ON co.numero_passaporte_atleta = at.numero_passaporte
INNER JOIN dbo.Modalidade mo ON mo.id_modalidade = co.id_modalidade
WHERE mo.id_modalidade IN (1, 2, 3)
  AND at.tipo_sanguineo IS NOT NULL
ORDER BY at.nome, mo.nome;

SELECT
    con.nome AS NomeComite,
    COUNT(at.numero_passaporte) AS NumeroDeAtletas
FROM dbo.ComiteOlimpicoNacional con
LEFT JOIN dbo.Atleta at ON at.id_comite_olimpico = con.id_comite_olimpico
GROUP BY con.id_comite_olimpico, con.nome
ORDER BY con.nome;

SELECT
    ev.nome AS NomeEvento,
    AVG(ing.preco) AS MediaPrecoIngressos
FROM dbo.Evento ev
INNER JOIN dbo.Ingresso ing ON ing.id_evento = ev.id_evento
GROUP BY ev.id_evento, ev.nome
HAVING AVG(ing.preco) > 50
ORDER BY ev.nome;

SELECT
    id_estadio,
    nome,
    capacidade
FROM dbo.Estadio
WHERE capacidade > (SELECT AVG(capacidade) FROM dbo.Estadio)
ORDER BY capacidade DESC, nome;

SELECT
    con.nome,
    COUNT(at.numero_passaporte) AS total_atletas
FROM dbo.ComiteOlimpicoNacional con
LEFT JOIN dbo.Atleta at ON at.id_comite_olimpico = con.id_comite_olimpico
GROUP BY con.id_comite_olimpico, con.nome
ORDER BY con.nome;

SELECT con.nome AS NomeComite
FROM dbo.ComiteOlimpicoNacional con
WHERE EXISTS (
    SELECT 1
    FROM dbo.Atleta at
    WHERE at.id_comite_olimpico = con.id_comite_olimpico
)
ORDER BY con.nome;

SELECT
    es.nome   AS NomeEstadio,
    coi.nome  AS NomeComite,
    COUNT(ev.id_evento) AS TotalEventos
FROM dbo.Evento ev
INNER JOIN dbo.Estadio es ON es.id_estadio = ev.id_estadio
INNER JOIN dbo.ComiteOlimpicoNacional con ON con.id_comite_olimpico = es.id_comite_olimpico
INNER JOIN dbo.ComiteOlimpicoInternacional coi ON coi.id_comite_olimpico = con.id_comite_olimpico_internacional
WHERE es.capacidade > 50000
GROUP BY es.id_estadio, es.nome, coi.id_comite_olimpico, coi.nome
ORDER BY TotalEventos DESC, es.nome;
