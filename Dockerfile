FROM fabric8/java-jboss-openjdk8-jdk:1.0.13

ENV JAVA_APP_JAR test-1.0-SNAPSHOT.jar
ENV AB_OFF true
ADD target_jar/test-1.0-SNAPSHOT.jar /app/
