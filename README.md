## Sistema de Cadastro Empresarial

Exemplo de Aplica��o Servless utilizando Kotlin e Stack Spring.

Dom�nio
- Cadastro de Funcion�rios e Departamentos
- Sistema de Batimento de Ponto

Solu��es/Features Utilizadas:

- Endpoints REST de Funcion�rios e Departamentos
- Mapeamento Relacional com [JPA](https://spring.io/projects/spring-data-jpa) e [Banco em Mem�ria H2](https://www.h2database.com/html/main.html)
- Documenta��o de APIs com [OpenAPI](https://swagger.io/specification/)
- Configura��o de [Spring Security](https://spring.io/projects/spring-security#overview) para autorizar o uso de alguns servi�os
- Utiliza��o de Jobs schedulados com [Scheduling Tasks](https://spring.io/guides/gs/scheduling-tasks/)
- Utiliza��o de [Spring Batch](https://spring.io/guides/gs/batch-processing/) para processamento de sistema de pontos de Funcion�rios 
