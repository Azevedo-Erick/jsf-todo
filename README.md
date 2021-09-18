# JSF ToDo

## O que é?

Uma simples aplicação para adicionar tarefas a  fazer, concluí-las e removê-las.

### Tecnologias usadas:

- Jsf
- Scss
- PostgreSQL
- Maven

### Como usar?

Tendo o projeto baixado, possuindo algum servidor como o TOMCAT configurado e tendo o PostgreSQL com ao menos um **esquema** criado e um usuário, faça o seguinte:

1. Vá em *src/java/br/com/todo/utils/ConnectionManager.java*
2. Troque a url pela URL do seu banco de dados colocando
3. Coloque seu nome de usuário do banco de dados
4. Coloque sua senha do banco de dados
5. Ao iniciar o servidor, caso não ocorra nenhum problema, ao entrar na url `http://localhost:8080/ToDo/faces/index.xhtml` as tabelas serão criadas
   

