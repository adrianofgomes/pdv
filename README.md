# Projeto Open Source para explorar Spring Boot e outras ferramentas

Inicialmente temos backend com Java JDK 21 (Amazon Corretto) / Spring Boot 3.5.5

Banco de dados H2 para testes e MySQL para aplicação

Documentação de API utilizando Springdoc OpenAPI

backend\src\main\resources\application.properties configurado para carregar parâmetros de conexão com banco de dados:
- MYSQL_URL
- MYSQL_USER
- MYSQL_PASSWORD

Utilizando VSCode local e GitHub Codespaces para codificação. No Codespaces configurar variáveis de ambiente acima na opção "Secrets and variables".

## CI/CD
Utilizando Jenkins para CI/CD rodando via Docker num servidor Debian. Jenkins sendo executado através do docker-compose.yml da pasta servers. Utilizando Docker Cloud Agent no Docker utilizando o mesmo serviço Docker do host. Imagem do agente em server/agente-jenkis/Dockerfile

Para que o Jenkins consiga utilizar o próprio Docker do host é necessário configurá-lo com URI: unix:///var/run/docker.sock

A aplicação Spring Boot por sua vez também é executada via Docker e por isso o Agent também precisa da configuração do docker.sock, neste caso utilizando a configuração Mounts com "type=bind,source=/var/run/docker.sock,target=/var/run/docker.sock".

O job de build/deploy da aplicação no Jenkins é configurado para baixar o backend/Jenkinsfile diretamente do GitHub através da opção "Pipeline script from SCM"

### Comandos docker úteis:

docker ps -a

docker images

docker run -it pogger-jdk21-maven bash

docker build -t "pogger-jdk21-maven" .

docker logs pdv-app-container
