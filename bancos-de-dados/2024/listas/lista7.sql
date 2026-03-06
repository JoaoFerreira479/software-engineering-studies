SELECT p.nome   AS "Nome do Proprietário",
       v.modelo AS modelo
FROM veiculo v
INNER JOIN proprietario p ON p.cod_proprietario = v.cod_proprietario
WHERE p.estado = 'MG'
ORDER BY p.nome, v.modelo;

SELECT p.estado      AS estado,
       v.placa       AS placa,
       v.modelo      AS modelo,
       v.valor_ipva  AS "Valor do IPVA",
       p.nome        AS "Nome do Proprietário"
FROM veiculo v
INNER JOIN proprietario p ON p.cod_proprietario = v.cod_proprietario
WHERE p.estado IN ('MG', 'RJ', 'SP')
  AND v.valor_ipva BETWEEN 150 AND 350
ORDER BY p.estado, v.placa;

SELECT p.nome  AS "Nome do Proprietário",
       v.placa AS placa
FROM proprietario p
LEFT JOIN veiculo v ON v.cod_proprietario = p.cod_proprietario
ORDER BY p.nome, v.placa;

SELECT p.nome  AS "Nome do Proprietário",
       v.placa AS placa
FROM proprietario p
LEFT JOIN veiculo v ON v.cod_proprietario = p.cod_proprietario
UNION
SELECT NULL    AS "Nome do Proprietário",
       v.placa AS placa
FROM veiculo v
WHERE v.cod_proprietario IS NULL
ORDER BY "Nome do Proprietário" NULLS LAST, placa;

SELECT p.estado        AS estado,
       COUNT(v.placa)  AS "Quantidade de Veículos"
FROM proprietario p
INNER JOIN veiculo v ON v.cod_proprietario = p.cod_proprietario
GROUP BY p.estado
HAVING COUNT(v.placa) > 4
ORDER BY p.estado;

SELECT p.cidade              AS cidade,
       SUM(v.valor_ipva)      AS "Total IPVA Arrecadado"
FROM proprietario p
INNER JOIN veiculo v ON v.cod_proprietario = p.cod_proprietario
WHERE v.valor_ipva IS NOT NULL
GROUP BY p.cidade
ORDER BY SUM(v.valor_ipva) DESC;

SELECT p.estado                 AS estado,
       COUNT(DISTINCT v.modelo) AS "Quantidade de Modelos"
FROM proprietario p
INNER JOIN veiculo v ON v.cod_proprietario = p.cod_proprietario
GROUP BY p.estado
ORDER BY p.estado;

SELECT p.nome           AS "Nome do Proprietário",
       COUNT(v.placa)   AS "Quantidade de Veículos"
FROM proprietario p
INNER JOIN veiculo v ON v.cod_proprietario = p.cod_proprietario
GROUP BY p.cod_proprietario, p.nome
HAVING COUNT(v.placa) > 1
ORDER BY COUNT(v.placa) DESC;

SELECT v.modelo           AS modelo,
       SUM(v.valor_ipva)  AS "Total IPVA"
FROM veiculo v
WHERE v.modelo LIKE 'FIAT%'
  AND v.valor_ipva IS NOT NULL
GROUP BY v.modelo
ORDER BY SUM(v.valor_ipva) DESC;

SELECT AVG(v.valor_ipva) AS "Média do IPVA em SP"
FROM veiculo v
INNER JOIN proprietario p ON p.cod_proprietario = v.cod_proprietario
WHERE p.estado = 'SP'
  AND v.valor_ipva IS NOT NULL;
