CREATE TABLE tbCliente (
  idCliente INT AUTO_INCREMENT(1,1) NOT NULL,
  nmCliente VARCHAR(50) NOT NULL,
  cpfCnpj VARCHAR(14) NOT NULL,
  dataCadastro DATETIME NOT NULL,
  dataAlteracao DATETIME NULL,

  CONSTRAINT PK_Cliente PRIMARY KEY (idCliente)
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
  dtVenda DATE NOT NULL,
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