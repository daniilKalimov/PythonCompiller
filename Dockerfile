FROM openjdk:11
RUN mkdir /app
COPY build/libs/rpc-server-*.jar /app/rpcserver.jar
WORKDIR /app
ENTRYPOINT ["java","-jar","/app/rpcserver.jar"]
