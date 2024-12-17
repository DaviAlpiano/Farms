FROM maven:3-openjdk-17 AS build-image

# Diretório de trabalho
WORKDIR /to-build-app

# Copia os arquivos necessários (ajuste o caminho conforme sua estrutura)
COPY pom.xml .
COPY src ./src

# Instala as dependências
RUN mvn dependency:go-offline

# Constrói o pacote JAR
RUN mvn package

# Estágio 2: Imagem final
FROM eclipse-temurin:17-jre-alpine

# Diretório de trabalho
WORKDIR /app

# Copia o JAR construído do estágio anterior
COPY --from=build-image /to-build-app/target/*.jar app.jar

# Exposição da porta 8080
EXPOSE 8080

# Ponto de entrada
ENTRYPOINT ["java", "-jar", "app.jar"]
