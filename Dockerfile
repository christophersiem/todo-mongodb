FROM openjdk:14

ENV ENVIRONMENT=prod

MAINTAINER Christopher S. <...>

ADD /W5D4_FridayGroup-Fetch/backend/target/todo.jar app.jar

CMD ["sh", "-c", "java -Dserver.port=$PORT -Dspring.data.mongodb.uri=$MONGODB_URI -jar /app.jar"]
