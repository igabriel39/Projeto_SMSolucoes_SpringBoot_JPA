CREATE TABLE tbCliente (
  idCliente INT AUTO_INCREMENT(1,1) NOT NULL,
  nmCliente VARCHAR(50) NOT NULL,
  cpf VARCHAR(11) NOT NULL,
  dataCadastro DATETIME NOT NULL,
  dataAlteracao DATETIME NULL,

  CONSTRAINT PK_Cliente PRIMARY KEY (idCliente),
  CONSTRAINT UQ_Cliente UNIQUE (cpf)
);

CREATE TABLE tbProduto (
  idProduto INT AUTO_INCREMENT(1,1) NOT NULL,
  nmProduto VARCHAR(50) NOT NULL,
  vlProduto REAL NOT NULL,
  dataCadastro DATETIME NOT NULL,
  dataAlteracao DATETIME NULL,

  CONSTRAINT PK_Produto PRIMARY KEY (idProduto)
);

CREATE TABLE tbVenda (
  idVenda INT AUTO_INCREMENT(1,1) NOT NULL,
  idCliente INT NOT NULL,
  dataCadastro DATETIME NOT NULL,
  dataAlteracao DATETIME NULL,

  CONSTRAINT PK_Venda PRIMARY KEY (idVenda),
  CONSTRAINT FK_Venda_Cliente FOREIGN KEY (idCliente) REFERENCES tbCliente (idCliente)
);

CREATE TABLE tbProdutoVenda (
  idVenda INT NOT NULL,
  idProduto INT NOT NULL,
  vlProdutoVendido REAL NOT NULL,
  qtProdutoVendido INT NOT NULL,

  CONSTRAINT PK_ProdutoVenda PRIMARY KEY (idVenda, idProduto),
  CONSTRAINT FK_ProdutoVenda_Venda FOREIGN KEY (idVenda) REFERENCES tbVenda (idVenda),
  CONSTRAINT FK_ProdutoVenda_Produto FOREIGN KEY (idProduto) REFERENCES tbProduto (idProduto)
);


insert into tbCliente
(
nmCliente, cpf, dataCadastro
)
values
(
'Igor Gabriel', '07652469538', '2027-05-05'
);


insert into tbProduto
(
nmProduto, vlProduto, dataCadastro
)
values
(
'Pão', 2, '2025-09-08'
);



insert into tbVenda
(
idCliente, dataCadastro
)
values
(
1, '2024-05-07'
);




insert into tbProdutoVenda
(
idVenda, idProduto, qtProdutoVendido, vlProdutoVendido
)
values
(
1, 1, 3, 7
);