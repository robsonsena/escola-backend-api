# API de Gestão Escolar (Java/Spring Boot)

Este projeto implementa uma API RESTful para gerenciar alunos, cursos e matrículas, utilizando Java com o framework Spring Boot.

## Tecnologias Utilizadas

*   **Spring Boot:** Framework para desenvolvimento de aplicações Java.
*   **Spring Data JPA:** Facilita o acesso e a manipulação de dados com o banco de dados.
*   **Jakarta Bean Validation:** Para validação de dados nas requisições.
*   **H2 Database:** Banco de dados em arquivo (para desenvolvimento e testes).
*   **Maven:** Gerenciador de dependências e ferramenta de build.
*   **Docker:** Para containerizar a aplicação.
*   **Springdoc OpenAPI:** Para gerar a documentação interativa da API (Swagger UI).

## Pré-requisitos

*   **Java Development Kit (JDK) 17 ou superior**
*   **Docker e Docker Compose** (Opcional, para execução em contêiner)

## Como Executar

Existem duas maneiras principais de executar a aplicação:

1.  **Clone o repositório:**
    ```bash
    git clone <URL_DO_REPOSITORIO>
    cd <nome-do-diretorio>
    ```

### Opção 1: Usando o Maven Wrapper (Recomendado para desenvolvimento)

O Maven Wrapper (`mvnw`) garante que você use a versão correta do Maven sem precisar instalá-lo globalmente.

1.  No terminal, na raiz do projeto, execute o comando:

    *   **Linux/macOS:**
        ```bash
        ./mvnw spring-boot:run
        ```
    *   **Windows (CMD/PowerShell):**
        ```bash
        .\mvnw.cmd spring-boot:run
        ```

### Opção 2: Usando Docker Compose (Mais simples)

Esta é a forma mais fácil se você tiver o Docker instalado. Ele irá construir a imagem e iniciar o contêiner.

1.  No terminal, na raiz do projeto, execute:
    ```bash
    docker-compose up --build
    ```

Após iniciar a aplicação por qualquer um dos métodos, a API estará disponível em `http://localhost:8000`.

## Documentação da API

Com a aplicação em execução, a documentação interativa da API (Swagger UI), gerada pelo Springdoc, pode ser acessada no seu navegador:

```
http://localhost:8000/swagger-ui.html
```

Nesta página, você pode visualizar todos os endpoints disponíveis, seus parâmetros e testá-los diretamente.

## Banco de Dados

O projeto está configurado para usar o banco de dados **H2** em modo de arquivo. Na primeira execução, um arquivo chamado `escola.mv.db` será criado dentro de um diretório `data` na raiz do projeto. Isso garante que os dados persistam entre as reinicializações da aplicação.

