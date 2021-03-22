## Sistema de Cadastro Empresarial

Exemplo de Aplicação Servless utilizando Kotlin e Stack Spring.

Domínio
- Cadastro de Funcionários e Departamentos
- Sistema de Batimento de Ponto

Soluções/Features Utilizadas:

- Endpoints REST de Funcionários e Departamentos
- Mapeamento Relacional com [JPA](https://spring.io/projects/spring-data-jpa) e [Banco em Memória H2](https://www.h2database.com/html/main.html)
- Documentação de APIs com [OpenAPI](https://swagger.io/specification/)
- Configuração de [Spring Security](https://spring.io/projects/spring-security#overview) para autorizar o uso de alguns serviços
- Utilização de Jobs schedulados com [Scheduling Tasks](https://spring.io/guides/gs/scheduling-tasks/)
- Utilização de [Spring Batch](https://spring.io/guides/gs/batch-processing/) para processamento de sistema de pontos de Funcionários 
