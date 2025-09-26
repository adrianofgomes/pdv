# Usa uma imagem base Java leve (ideal para Spring Boot)
FROM jenkins/agent:alpine-jdk21

# O arquivo JAR é gerado em 'backend/target/nome-do-seu-jar-SNAPSHOT.jar'
# É uma boa prática usar um nome genérico no Dockerfile
ARG JAR_FILE=target/*.jar

# Copia o JAR do diretório 'target' para o contêiner
COPY ${JAR_FILE} app.jar

# Expõe a porta utilizada pela aplicação Spring Boot
EXPOSE 8081

# Comando para rodar a aplicação Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]