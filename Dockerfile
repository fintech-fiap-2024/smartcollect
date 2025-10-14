#FROM eclipse-temurin:21-alpine
#VOLUME /tmp
#EXPOSE 8080
#ARG JAR_FILE=target/smartcollect-0.0.1-SNAPSHOT.jar
#ADD ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]



# ====================
# ESTÁGIO 1: BUILD (Compilação e Empacotamento)
# ====================
# Utiliza o Maven e JDK 21 para compilar o código
FROM maven:3.9.5-eclipse-temurin-21 AS build
WORKDIR /app

# Copia o pom.xml e baixa dependências para cache
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código-fonte
COPY src /app/src

# Compila o projeto e cria o JAR na pasta target
# O JAR_NAME será usado no estágio de runtime
ARG JAR_NAME="smartcollect-0.0.1-SNAPSHOT.jar"
RUN mvn package -DskipTests

# ====================
# ESTÁGIO 2: RUNTIME (Execução)
# ====================
# Utiliza uma imagem JRE (Runtime Environment) leve baseada no JDK 21 Alpine
# Isso garante que a imagem final seja pequena e segura, sem as ferramentas de build
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Define a variável de ambiente JAVA_OPTS
# Otimiza o uso de memória em ambientes containerizados
ENV JAVA_OPTS="-XX:MinRAMPercentage=50.0 -XX:MaxRAMPercentage=75.0"

# Expõe a porta do Spring Boot
EXPOSE 8080

# Copia o JAR do estágio de build
# Note o uso de 'build' e o nome do arquivo gerado
COPY --from=build /app/target/smartcollect-0.0.1-SNAPSHOT.jar app.jar

# Define o ponto de entrada usando as JAVA_OPTS
ENTRYPOINT ["java", "$JAVA_OPTS", "-jar", "/app.jar"]
