# Rest API para sistema de gerenciamento de ativos de uma empresa

Api desenvolvida utilizando o [Ecossistema Spring](https://spring.io/) em conjunto com [Jakarta Persistence JPA](https://jakarta.ee/specifications/persistence/), [Jakarta Bean Validation](https://jakarta.ee/specifications/bean-validation/), [ModelMapper](http://modelmapper.org/)  e [Flyway](https://flywaydb.org/).

Recursos da API (endpoints):

1 - Usuários - /users;<br>
2 - Empresas - /companies;<br>
3 - Equipamentos - /equipments; (EM DESENVOLVIMENTO)<br>
4 - Setores - /sectors;<br>
5 - Tipos de equipamentos - /types.<br>

## Uso de endpoints

A usabilidade dos endpoins seguem o mesmo padrão, abeixo é possível ver o exemplo de consumo do recurso "Usuários".

### Usuários

- Listar os usuários (GET localhost:8080/users);
- Obter um usuário (GET localhost:8080/users/{userId});
- Adicionar um usuário (POST localhost:8080/users);<br>
  Exemplo de corpo:
  ```JSON
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
  {
    "name": "Fernando Souza",
    "email": "fernando1@gmail.com",
    "login": "fernando2000",
    "password": "e10adc3949ba59abbe56e057f20f883e"
  }
  ```
- Excluir um usuário (DELETE localhost:8080/users/{userId}).