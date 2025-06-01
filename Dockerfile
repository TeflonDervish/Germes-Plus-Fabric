FROM amazoncorretto:17

WORKDIR /app

COPY target/Germes-Plus-Fabric-1.0.jar germes-plus-manager.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "germes-plus-manager.jar"]