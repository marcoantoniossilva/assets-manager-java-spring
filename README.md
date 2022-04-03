# Rest API para sistema de gerenciamento de ativos de uma empresa

Api desenvolvida utilizando o [Ecossistema Spring](https://spring.io/) em conjunto
com [Jakarta Persistence JPA](https://jakarta.ee/specifications/persistence/)
, [Jakarta Bean Validation](https://jakarta.ee/specifications/bean-validation/), [ModelMapper](http://modelmapper.org/)
e [Flyway](https://flywaydb.org/).

Recursos da API (endpoints):

1 - Usuários - /users;<br>
2 - Empresas - /companies;<br>
3 - Equipamentos - /equipments;<br>
4 - Setores - /sectors;<br>
5 - Tipos de equipamentos - /types;<br>
6 - Autenticação (Login) - /auth.<br>

Todos os endoins com exceção do /auth suportam as operações CRUD.<br>

Operação | Método
:---------:|:-------:
CREATE   | POST
READ     | GET
UPDATE   | PUT
DELETE   | DELETE

## Authenticação

Para consumir a API, primeiro é necessário logar no endpoint /auth passando um corpo contendo login e senha com o abaixo:<br>

  ```JSON
  {
  "login": "fernando",
  "password": "minhasenha123456"
}
  ```

O retorno será algo como:<br>

  ```JSON
{
  "id": 96,
  "token": "TOKEN_GERADO",
  "expirationTime": "2022-04-03T01:38:33.706536919"
}
```
O token retornado no corpo da requisição deverá ser passado no cabeçalho "Authentication" 
de todas as outras requisições e tem duração de 30 minutos.<br>

## Uso de endpoints

A usabilidade dos endpoins seguem o mesmo padrão, abeixo é possível ver o exemplo de consumo do recurso "Usuários".

### Usuários

- Listar os usuários (GET localhost:8080/users);
- Obter um usuário (GET localhost:8080/users/{userId});
- Adicionar um usuário (POST localhost:8080/users);<br>
  Exemplo de corpo:
  ```JSON
  Authentication: {TOKEN_GERADO_NA_ETAPA_DE_AUTENTICAÇÃO}
  {
    "name": "Fernando",
    "email": "fernando@gmail.com",
    "login": "nando2000",
    "password": "e10adc3949ba59abbe56e057f20f883e"
  }
  ```
- Atualizar um usuário (PUT localhost:8080/users/{userId});<br>
  Exemplo de corpo:
  ```JSON
  Authentication: {TOKEN_GERADO_NA_ETAPA_DE_AUTENTICAÇÃO}
  {
    "name": "Fernando Souza",
    "email": "fernando1@gmail.com",
    "login": "fernando2000",
    "password": "e10adc3949ba59abbe56e057f20f883e"
  }
  ```
- Excluir um usuário (DELETE localhost:8080/users/{userId}).