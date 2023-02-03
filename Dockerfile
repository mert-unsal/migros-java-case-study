FROM maven:3.8.4-openjdk-17-slim AS builder
WORKDIR /
COPY pom.xml ./pom.xml
RUN mvn dependency:go-offline
COPY src/ ./src/
RUN mvn clean install -Dmaven.test.skip=true

FROM openjdk:17-jdk-slim AS runner
WORKDIR /opt/migros-case-study
COPY --from=builder target/app.jar /opt/migros-case-study/app.jar
RUN chown -R nobody /opt/migros-case-study
USER nobody
EXPOSE 8080
CMD ["sh", "-c","java -jar /opt/migros-case-study/app.jar"]
