# nike-catalog-api

# 🏬 API Catálogo Nike

Este projeto é uma **API RESTful** desenvolvida em **Java** utilizando o **Spring Boot**, com o objetivo de gerenciar um catálogo de produtos de uma loja Nike.

Atualmente, a API permite **cadastrar**, **listar**, **buscar**, **atualizar** e **deletar** produtos, armazenando todas as informações em um banco de dados **MySQL**.

Uma funcionalidade futura prevista é a **integração com a AWS S3** para armazenamento de imagens dos produtos.

---

## 🚀 Funcionalidades principais

- **Cadastro de Produto:** Nome, descrição, tamanho e URL da imagem.
- **Listagem de Produtos:** Exibe todos os produtos cadastrados.
- **Busca de Produtos:** Pesquisa produtos pelo nome.
- **Atualização de Produto:** Atualiza as informações de um produto existente.
- **Deleção de Produto:** Remove um produto do catálogo.

---

## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- Banco de Dados Relacional (MySQL)
- Maven
- Jakarta Validation

---

## 📦 Estrutura do Projeto

- **Controller:** Gerencia as requisições e respostas da API.
- **DTO:** Realiza a transferência e validação dos dados de entrada.
- **Model:** Representa as entidades do banco de dados.
- **Repository:** Interface que facilita a comunicação com o banco de dados.

---

## 📋 Pré-requisitos

- Java JDK 17 ou superior
- Maven instalado
- Banco de Dados MySQL em execução

---

## ⚙️ Como rodar o projeto

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/nike-catalog-api.git
```

2. Acesse o diretório:

```bash
cd nike-catalog-api
```

3. Configure o `application.properties` com as informações do seu banco de dados MySQL:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/seu_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

4. Rode o projeto:

```bash
mvn spring-boot:run
```

---

## 📊 Melhorias Futuras

- Implementar upload de imagens para o AWS S3
- Implementar autenticação e autorização (JWT)
- Criar paginação na listagem de produtos
- Desenvolver integração com serviços de análise de dados (AWS Glue e Athena)

---

# 🎯 Objetivo final

Construir uma API robusta e escalável para o gerenciamento de produtos, servindo como base para sistemas de e-commerce ou integrações administrativas.

---

# 🔢 Exemplos de uso dos endpoints

### ➔ Cadastrar Produto

**POST** `/catalogo`

**Body JSON:**
```json
{
  "nome": "Nike Air Max",
  "descricao": "Tenis de corrida super leve",
  "foto": "https://link-da-imagem.com/airmax.jpg",
  "tamanho": 42
}
```

### ➔ Listar Todos os Produtos

**GET** `/catalogo`

**Resposta:**
```json
[
  {
    "id": 1,
    "nome": "Nike Air Max",
    "descricao": "Tenis de corrida super leve",
    "foto": "https://link-da-imagem.com/airmax.jpg",
    "tamanho": 42
  }
]
```

### ➔ Buscar Produto por Nome

**GET** `/catalogo/buscar?nome=Air`

**Resposta:**
```json
[
  {
    "id": 1,
    "nome": "Nike Air Max",
    "descricao": "Tenis de corrida super leve",
    "foto": "https://link-da-imagem.com/airmax.jpg",
    "tamanho": 42
  }
]
```

### ➔ Atualizar Produto

**PUT** `/catalogo/atualizar/Nike Air Max`

**Body JSON:**
```json
{
  "nome": "Nike Air Max 2025",
  "descricao": "Modelo atualizado",
  "foto": "https://link-da-imagem.com/airmax2025.jpg",
  "tamanho": 43
}
```

### ➔ Deletar Produto

**DELETE** `/catalogo/deletar?nome=Nike Air Max 2025`

**Resposta:**
```text
Nike Air Max 2025 foi deletado
```

