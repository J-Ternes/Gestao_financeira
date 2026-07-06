# Gestão Financeira (Backend: Java/SpringBoot e FrontEnd: React)

Este é o meu primeiro projeto FullStack: Um software de gestão financeira pessoal. O objetivo do projeto é consolidar conhecimentos em Java, Spring Boot, React e arquitetura de sistemas financeiros, focando em boas práticas de mercado e segurança de dados.

O projeto está sendo construído seguindo os princípios de **Clean Code** e **Arquitetura MVC**, garantindo uma separação clara entre as camadas de controle, serviço e persistência.

## 🚀 Funcionalidades Atuais

- **Gestão de Usuários:** Cadastro de usuários com diferentes níveis de acesso (`USER`, `ADMIN`).
- **Controle de Categorias:** Gerenciamento de categorias customizadas por usuário (ex: Alimentação, Lazer, Mercado).
- **Lançamentos Financeiros:** Cadastro e controle de despesas e receitas com precisão decimal (`BigDecimal`).
- **Relatórios:** Feature inicial para consulta de histórico de despesas por categoria e cálculo automático de valor total gasto.

## 🚧 Status do Projeto

Este projeto está em **fase de desenvolvimento**.
Atualmente, as funcionalidades de CRUD básico e relatórios simples estão concluídas. As próximas etapas incluem:
- Implementação de Segurança com **Spring Security** e JWT.
- Criptografia de senhas com BCrypt.
- Dashboards com comparativos mensais.

## 🛠️ Tecnologias Utilizadas
### Bckend:
- **Java 17+**
- **Spring Boot 3**
- **Spring Data JPA** (Persistência de Dados)
- **Hibernate** (ORM)
- **PostgreSQL** (Banco de Dados)
- **Flyway** (Migrations e Versionamento de Banco de Dados)
- **Lombok** (Produtividade no código Java)
- **Jackson** (Manipulação de JSON)

## 🏗️ Estrutura de Tabelas (Migrations)

O projeto utiliza o **Flyway** para garantir que a estrutura do banco seja versionada. As tabelas principais são:
- `users`: Armazena informações dos usuários.
- `categoria`: Define as categorias vinculadas aos usuários.
- `lancamento`: Registra cada movimentação financeira (valor, data, tipo).

---

## 🔧 Como Rodar o Projeto

1. Clone o repositório.
2. Certifique-se de ter o PostgreSQL rodando.
3. Crie um banco de dados chamado `gestao_financeira`.
4. Configure suas credenciais no arquivo `src/main/resources/application.properties` (ou use um arquivo de exemplo).
5. Execute a aplicação através da sua IDE ou via terminal:
   ```bash
   ./mvnw spring-boot:run