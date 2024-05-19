FROM openjdk:21-ea-slim as builder

RUN mkdir /app
WORKDIR /app

RUN apt update
RUN apt install maven -y
COPY . .
RUN mvn clean package -DskipTests


FROM openjdk:21-ea-slim as release
RUN mkdir /release
WORKDIR /release

ENV SPRING_PROFILE prod

RUN mkdir inbound application outbound
COPY --from=builder /app/inbound/target ./inbound/target
COPY --from=builder /app/application/target ./application/target
COPY --from=builder /app/outbound/target ./outbound/target

EXPOSE 8080
CMD ["/bin/sh", "-c", "java -Dspring.profiles.active=${SPRING_PROFILE} -jar inbound/target/inbound-*.jar"]