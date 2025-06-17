# API - Controle-de-Estoque

## 📌 Visão Geral

Este repositório contém o projeto desenvolvido para o seminário acadêmico sobre a implementação de uma **API RESTful** utilizando **Spring Boot com Java**.  
O principal objetivo é construir um sistema funcional, estruturado nas camadas de **entidades**, **serviços**, **repositórios** e **controllers**.

---

## 📁 Estrutura do Projeto

Cada integrante do grupo será responsável pela implementação de uma **controller**, contemplando as seguintes camadas:

📦 entidade/
⚙️ service/
💾 repository/
🌐 controller/


---

## ✅ Códigos de Resposta HTTP

A API deverá seguir os seguintes **códigos de status HTTP**:

| Código | Significado                        |
| ------ | ---------------------------------- |
| 200    | OK – Requisição bem-sucedida       |
| 201    | Created – Recurso criado           |
| 400    | Bad Request – Dados inválidos      |
| 404    | Not Found – Recurso não encontrado |
| 409    | Conflict – Violação de regra       |

### 📄 Classes Padrão de Resposta

- `ApiResponse.java` – Para respostas bem-sucedidas
- `ErrorResponse.java` – Para erros e falhas de validação


---

## 🛠 Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- Maven
- H2 / PostgreSQL

---

## 👨‍💻 Contribuidores

- João Pedro Pereira Marques
- Matheus Alende Pires
- João Antonio de Souza

---
