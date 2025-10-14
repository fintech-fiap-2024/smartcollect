# Como rodar a aplicação Spring Boot:

## 1. Certifique de ter o Docker instalado e rodando.
## 2. Navegue até a pasta do projeto
## 3.Execute o comando abaixo para criar a imagem Docker:
```bash
  docker-compose up -d
```


# 🧾 Documentação da API de Resíduos


API RESTful desenvolvida para registrar pontos de coleta, resíduos e descartes.

---

## 📘 Endpoints

### ✅ Inserir Resíduo
`POST /residuos`

> Cadastra um novo tipo de resíduo.

#### Request Body:
```json
{
  "tipo": "Orgânico",
  "descricao": "Resíduos biodegradáveis como restos de comida"
}
```

#### Response:
```json
{
  "id": 1,
  "tipo": "Orgânico",
  "descricao": "Resíduos biodegradáveis como restos de comida"
}
```

---

### ✅ Listar todos os pontos de coleta
`GET /pontos-coleta`

> Retorna todos os pontos de coleta cadastrados.

#### Response:
```json
[
  {
    "idPonto": 1,
    "nome": "Ponto Zona Norte",
    "endereco": "Av. Central, 500",
    "capacidadeTotal": 1000.0
  }
]
```

---

### ✅ Inserir ponto de coleta
`POST /pontos-coleta`

> Registra um novo ponto de coleta.

#### Request Body:
```json
{
  "nome": "Ponto Zona Sul",
  "endereco": "Rua das Palmeiras, 200",
  "capacidadeTotal": 1200.0
}
```

#### Response:
```json
{
  "idPonto": 2,
  "nome": "Ponto Zona Sul",
  "endereco": "Rua das Palmeiras, 200",
  "capacidadeTotal": 1200.0
}
```

---

### ♻️ Listar descartes de um ponto
`GET /pontos-coleta/{id}/descartes`

> Lista todos os descartes vinculados a um ponto de coleta.

#### Exemplo:
`GET /pontos-coleta/1/descartes`

#### Response:
```json
[
  {
    "idDescarte": 1,
    "quantidadeKg": 50.5,
    "dataDescarte": "2025-05-13",
    "pontoColetaId": 1,
    "residuoId": 2
  }
]
```

---

### 📊 Obter quantidade total descartada por ponto
`GET /pontos-coleta/{id}/descartes/quantidade-total`

> Mostra o total de kg descartados no ponto informado.

#### Exemplo:
`GET /pontos-coleta/1/descartes/quantidade-total`

#### Response:
```json
{
  "pontoColetaId": 1,
  "totalKg": 150.0
}
```

---

### 📈 Listar pontos de coleta com descarte acima de X kg
`GET /pontos-coleta/descartes/acima-de/{quantidade}`

> Lista todos os pontos que ultrapassaram um determinado total de descarte.

#### Exemplo:
`GET /pontos-coleta/descartes/acima-de/100.0`

#### Response:
```json
[
  {
    "pontoColetaId": 1,
    "totalKg": 150.0
  }
]
```

---

### ➕ Criar descarte vinculado a um ponto
`POST /pontos-coleta/{id}/descartes`

> Registra um novo descarte no ponto informado.

#### Request Body:
```json
{
  "quantidadeKg": 30.5,
  "dataDescarte": "2025-05-13",
  "pontoColetaId": 1,
  "residuoId": 2
}
```

#### Response:
```json
{
  "idDescarte": 3,
  "quantidadeKg": 30.5,
  "dataDescarte": "2025-05-13",
  "pontoColetaId": 1,
  "residuoId": 2
}
```

---

## ✅ Regras de validação
- Campos obrigatórios usam validação `@NotNull` ou `@NotBlank`.
- Datas devem estar no formato `YYYY-MM-DD`.
- `idPonto`, `idDescarte` e `idResiduo` são gerados automaticamente.
