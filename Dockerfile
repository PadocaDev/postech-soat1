FROM maven:3.9.2-amazoncorretto-20 AS builder

WORKDIR /app/

COPY pom.xml .
COPY src ./src/

RUN mvn dependency:go-offline

RUN mvn clean package -DskipTests


FROM amazoncorretto:20

COPY --from=builder /app/target/padocadev-0.0.1-SNAPSHOT.jar padocadev.jar

CMD ["java", "-jar", "padocadev.jar"]