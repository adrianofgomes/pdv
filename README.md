Projeto Open Source para explorar Spring Boot e outras ferramentas

Inicialmente temos backend com Java JDK 21 (Amazon Corretto) / Spring Boot 3.5.5

Banco de dados H2 para testes e MySQL para aplicação

Documentação de API utilizando Springdoc OpenAPI

backend\src\main\resources\application.properties configurado para carregar parâmetros de conexão com banco de dados:
- MYSQL_URL
- MYSQL_USER
- MYSQL_PASSWORD

Utilizando VSCode local e GitHub Codespaces para codificação. No Codespaces configurar variáveis de ambiente acima na opção "Secrets and variables".