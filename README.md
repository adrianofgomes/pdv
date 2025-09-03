Projeto Open Source para explorar Spring Boot e outras ferramentas

Inicialmente temos backend com Java KDK 21 / Spring Boot 3.5.5

Banco de dados H2 para testes e MySQL para aplicação

backend\src\main\resources\application.properties configurado para carregar parâmetros de conexão com banco de dados:
- MYSQL_URL
- MYSQL_USER
- MYSQL_PASSWORD

Utilizando VSCode local e GitHub Codespaces para codificação. No Codespaces configurar variáveis de ambiente acima na opção "Secrets and variables".