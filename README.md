# API - Módulo de Produtos (Avaliação 1)

Este repositório contém a implementação do módulo de gerenciamento de Produtos, desenvolvido para atender aos requisitos da Avaliação 1. A API fornece operações completas de CRUD (Create, Read, Update, Delete) com suporte a paginação, filtros e documentação automatizada.

## Funcionalidades e Requisitos Atendidos

*   **Cadastro de Produtos (POST):** Validação de dados e tratamento de conflitos (ex: nome duplicado) e regras de negócio (ex: estoque negativo).
*   **Listagem e Busca (GET):** Retorno de lista paginada (tamanho padrão de 20 itens ordenados por `dataCadastro`) com filtros opcionais por `nome` (parcial) e `categoria`.
*   **Atualização (PUT):** Substituição completa dos dados do produto com validações de ID e regras de negócio.
*   **Exclusão (DELETE):** Remoção de produtos retornando o status HTTP adequado (204 No Content).
*   **Tratamento de Exceções:** Retornos padronizados utilizando `ProblemDetail` para os status `400`, `404`, `409` e `422`.

## Uso de Inteligência Artificial (IA)

Para garantir a qualidade, padronização e o cumprimento rigoroso dos critérios da avaliação, a ferramente de Inteligência Artificial Gemini foi utilizada durante o ciclo de desenvolvimento deste módulo com os seguintes propósitos:

1.  **Correção de Erros (Troubleshooting):** A IA foi utilizada para analisar inconsistências no mapeamento de rotas (como o vínculo correto de `@PathVariable` nos métodos PUT e DELETE) e identificar falhas de comunicação entre a interface do Swagger e o Controller.
2.  **Criação de Anotações (Swagger/OpenAPI):** O assistente virtual auxiliou na estruturação e geração das anotações `@Operation`, `@Parameter` e `@ApiResponse`, garantindo que todos os fluxos de sucesso (200, 201, 204) e de erro (400, 404, 409, 422) exigidos pela regra de negócio fossem documentados corretamente na interface do Swagger.
3.  **Criação do Arquivo READ.ME** O assistente virtual auxiliou na estruturação e geração do arquivo READ.ME.

## Endpoints da API

A documentação interativa (Swagger UI) pode ser acessada através do navegador quando a aplicação estiver em execução, geralmente no caminho:
`http://localhost:8080/swagger-ui.html`

### Resumo das Rotas:
*   `GET /api/v1/produtos` - Lista os produtos (com paginação e filtros).
*   `GET /api/v1/produtos/{id_produto}` - Busca um produto específico pelo seu ID.
*   `POST /api/v1/produtos` - Cadastra um novo produto.
*   `PUT /api/v1/produtos/{id_produto}` - Atualiza integralmente um produto existente.
*   `DELETE /api/v1/produtos/{id_produto}` - Remove um produto da base de dados.

## Padrão de Commits

Este projeto segue rigorosamente o padrão **Conventional Commits** para manter o histórico limpo e rastreável. Exemplos de prefixos utilizados:
*   `feat:` para novas funcionalidades (ex: `feat(produtos): cria controller de produtos`).
*   `fix:` para correção de bugs ou ajustes (ex: `fix(produtos): corrige mapeamento do swagger no metodo put`).
