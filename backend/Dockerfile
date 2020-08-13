#FROM adoptopenjdk/openjdk11:ubi
FROM gradle:jdk11

WORKDIR /home/app

RUN useradd -u 8877 carRentalBack

COPY . .

RUN gradle clean build && \ 
    mv build/libs/car-rental-*.jar ./car-rental.jar

USER carRentalBack

CMD ["java","-jar","car-rental.jar"]

RUN rm build && \
    rm build.gradle && \
    rm build.gradle.orig

# /bin/bash Work