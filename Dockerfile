FROM openjdk:20

VOLUME /tmp
ADD /shopping-checkout.jar myapp.jar

RUN sh -c 'touch /myapp.jar'

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/ ./urandom", "-jar", "/myapp.jar"]
