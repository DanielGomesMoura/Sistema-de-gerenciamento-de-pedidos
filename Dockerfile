# Etapa de build
FROM ubuntu:latest AS build

# Instalação do OpenJDK e Maven, e atualização do sistema
RUN apt-get update && \
     apt-get install -y openjdk-17-jdk maven curl && \
     apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Define o diretório de trabalho
WORKDIR /app

# Copia os arquivos do projeto
COPY . .

# Compila o projeto usando Maven
RUN mvn clean install -DskipTests

# Etapa de execução
FROM openjdk:17-jdk-slim

# Exposição da porta da aplicação (ajuste a porta conforme sua aplicação)
EXPOSE 8080

# Define o diretório de trabalho na etapa final
WORKDIR /app

# Copia o arquivo gerado na etapa de build
COPY --from=build /app/target/brigadeiro-0.0.1-SNAPSHOT.jar app.jar

# Comando de inicialização da aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
