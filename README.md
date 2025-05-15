# Trabalho_OOP

 # 🏗️ API - Sistema de Estoque de Materiais de Construção

Este projeto consiste em uma API RESTful desenvolvida com **Spring Boot e Java**, como parte de um seminário acadêmico. O objetivo é gerenciar um sistema de estoque para materiais de construção, incluindo funcionalidades como cadastro, consulta, atualização e remoção de dados relacionados aos produtos.

## 👥 Integrantes do Grupo

- Matheus A
- João P
- João A

## 📌 Estrutura do Projeto

Cada membro do grupo foi responsável pela implementação completa de uma *controller*, incluindo todas as camadas relacionadas:

- **Entidade (Entity)**
- **Repositório (Repository)**
- **Serviço (Service)**
- **Controlador (Controller)**

## 📦 Camadas e Arquitetura

O projeto segue o padrão MVC (Model-View-Controller), com divisão clara de responsabilidades:


## ✅ Requisitos de Entrega

O desenvolvimento foi dividido em etapas:

1. Entidades (Modelos e Mapeamento JPA)
2. Repositórios (Interfaces de persistência)
3. Services (Regras de negócio)
4. Controllers (Endpoints da API)
5. Apresentação final da API

## 📋 Códigos de Resposta HTTP

- `200 OK` – Requisição bem-sucedida
- `201 Created` – Recurso criado com sucesso
- `400 Bad Request` – Erro nos dados enviados
- `404 Not Found` – Recurso não encontrado
- `409 Conflict` – Violação de regra de negócio

## 📄 Classes Padrão de Resposta

- `ApiResponse.java`: Modelo de resposta padrão para sucesso
- `ErrorResponse.java`: Modelo para tratamento e exibição de erros

## 🚀 Como Executar

Certifique-se de ter o Java 17+ e Maven instalados.

```bash
# Clone o projeto
git clone https://github.com/Moosy-Joao/Trabalho_OOP.git
cd Trabalho_OOP

# Execute o projeto
./mvnw spring-boot:run
```
## 📚 Licença
Este projeto é parte de um trabalho acadêmico e está disponível sob a licença MIT.
