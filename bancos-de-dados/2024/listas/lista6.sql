SELECT placa       AS placa,
       modelo      AS modelo
FROM veiculo
WHERE modelo LIKE 'FIAT%'
ORDER BY placa;

SELECT estado AS estado,
       nome   AS "Nome do Proprietário"
FROM proprietario
WHERE estado IN ('MG', 'RJ', 'SP', 'PR', 'CE')
ORDER BY estado, nome;

SELECT placa,
       modelo,
       ano_fabricacao,
       valor_ipva,
       cod_proprietario
FROM veiculo
WHERE valor_ipva IS NULL
ORDER BY placa;

SELECT placa       AS placa,
       modelo      AS modelo
FROM veiculo
WHERE ano_fabricacao <> 1996
  AND valor_ipva BETWEEN 150 AND 200
ORDER BY placa;

SELECT cod_proprietario,
       nome,
       cidade,
       estado
FROM proprietario
WHERE cidade = 'Belo Horizonte'
ORDER BY nome;

SELECT placa            AS placa,
       ano_fabricacao   AS "Ano de Fabricação"
FROM veiculo
WHERE placa LIKE 'G__-__75'
ORDER BY placa;

SELECT placa,
       modelo,
       valor_ipva AS ipva
FROM veiculo
WHERE ano_fabricacao IN (1988, 1990, 1996, 1997)
  AND modelo LIKE 'FORD%'
  AND (valor_ipva < 200 OR valor_ipva > 800)
ORDER BY placa;

SELECT modelo      AS modelo,
       valor_ipva  AS ipva
FROM veiculo
WHERE modelo LIKE 'FIAT%'
  AND valor_ipva BETWEEN 100 AND 800
ORDER BY modelo, valor_ipva;

INSERT INTO proprietario (cod_proprietario, nome, cidade, estado)
VALUES
    (1234, 'PESSOA 1', 'Belo Horizonte', 'MG'),
    (2234, 'PESSOA 2', 'Belo Horizonte', 'MG'),
    (3234, 'PESSOA 3', 'Belo Horizonte', 'MG'),
    (4234, 'PESSOA 4', 'Belo Horizonte', 'MG'),
    (5234, 'PESSOA 5', 'Belo Horizonte', 'MG'),
    (6234, 'PESSOA 6', 'Belo Horizonte', 'MG'),
    (7234, 'PESSOA 7', 'Belo Horizonte', 'MG');

INSERT INTO veiculo (placa, modelo, ano_fabricacao, valor_ipva, cod_proprietario)
VALUES ('FE2-8I43', 'MOBI', 2022, NULL, 1234);

UPDATE veiculo
SET valor_ipva = 200
WHERE placa = 'FE2-8I43';
