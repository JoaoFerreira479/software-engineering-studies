SELECT DISTINCT p.nome,
       p.estado,
       v.ano_fabricacao
FROM proprietario p
INNER JOIN veiculo v ON v.cod_proprietario = p.cod_proprietario
WHERE v.ano_fabricacao IN (1990, 1996, 1998)
ORDER BY p.nome, p.estado, v.ano_fabricacao;

SELECT DISTINCT nome,
       estado
FROM proprietario
WHERE cod_proprietario IN (
    SELECT cod_proprietario
    FROM veiculo
    WHERE ano_fabricacao IN (1990, 1996, 1998)
)
ORDER BY nome, estado;

SELECT v.placa,
       v.modelo
FROM veiculo v
INNER JOIN proprietario p ON p.cod_proprietario = v.cod_proprietario
WHERE p.cidade IN ('Belo Horizonte', 'Rio de Janeiro')
ORDER BY v.placa;

SELECT placa,
       modelo
FROM veiculo
WHERE cod_proprietario IN (
    SELECT cod_proprietario
    FROM proprietario
    WHERE cidade IN ('Belo Horizonte', 'Rio de Janeiro')
)
ORDER BY placa;

SELECT DISTINCT p.nome
FROM proprietario p
INNER JOIN veiculo v ON v.cod_proprietario = p.cod_proprietario
WHERE v.valor_ipva BETWEEN 200 AND 600
ORDER BY p.nome;

SELECT DISTINCT nome
FROM proprietario
WHERE cod_proprietario IN (
    SELECT cod_proprietario
    FROM veiculo
    WHERE valor_ipva BETWEEN 200 AND 600
)
ORDER BY nome;

WITH media_sp AS (
    SELECT AVG(v2.valor_ipva) AS media
    FROM veiculo v2
    INNER JOIN proprietario p2 ON p2.cod_proprietario = v2.cod_proprietario
    WHERE p2.estado = 'SP'
      AND v2.valor_ipva IS NOT NULL
)
SELECT v.placa,
       v.modelo
FROM veiculo v
INNER JOIN proprietario p ON p.cod_proprietario = v.cod_proprietario
CROSS JOIN media_sp m
WHERE p.estado = 'MG'
  AND v.valor_ipva IS NOT NULL
  AND v.valor_ipva < m.media
ORDER BY v.placa;

WITH rank_ipva AS (
    SELECT cod_proprietario,
           modelo,
           valor_ipva,
           placa,
           ROW_NUMBER() OVER (PARTITION BY cod_proprietario ORDER BY valor_ipva DESC NULLS LAST) AS rn
    FROM veiculo
    WHERE valor_ipva IS NOT NULL
)
SELECT cod_proprietario,
       modelo,
       valor_ipva,
       placa
FROM rank_ipva
WHERE rn = 1
ORDER BY cod_proprietario;

WITH rank_por_ano AS (
    SELECT ano_fabricacao,
           valor_ipva,
           placa,
           ROW_NUMBER() OVER (PARTITION BY ano_fabricacao ORDER BY valor_ipva ASC NULLS LAST) AS rn
    FROM veiculo
    WHERE valor_ipva IS NOT NULL
)
SELECT ano_fabricacao,
       valor_ipva,
       placa
FROM rank_por_ano
WHERE rn = 1
ORDER BY ano_fabricacao;

WITH min_ano_por_cidade AS (
    SELECT p.cidade,
           MIN(v1.ano_fabricacao) AS min_ano
    FROM proprietario p
    INNER JOIN veiculo v1 ON v1.cod_proprietario = p.cod_proprietario
    GROUP BY p.cidade
)
SELECT p.cidade,
       p.estado,
       v.placa,
       v.modelo,
       v.ano_fabricacao
FROM veiculo v
INNER JOIN proprietario p ON p.cod_proprietario = v.cod_proprietario
INNER JOIN min_ano_por_cidade m ON m.cidade = p.cidade AND v.ano_fabricacao = m.min_ano
ORDER BY p.estado, p.cidade, v.placa;

WITH media_por_estado AS (
    SELECT p.estado,
           AVG(v2.valor_ipva) AS media
    FROM veiculo v2
    INNER JOIN proprietario p ON p.cod_proprietario = v2.cod_proprietario
    WHERE v2.valor_ipva IS NOT NULL
    GROUP BY p.estado
)
SELECT v.placa,
       v.modelo,
       v.valor_ipva
FROM veiculo v
INNER JOIN proprietario p ON p.cod_proprietario = v.cod_proprietario
INNER JOIN media_por_estado m ON m.estado = p.estado
WHERE v.valor_ipva IS NOT NULL
  AND v.valor_ipva > m.media
ORDER BY v.placa;
