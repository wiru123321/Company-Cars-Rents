
# ---- Backend dockerfile ---- #

FROM gradle:jdk11

WORKDIR /home/app

RUN useradd -u 8877 carRentalBack

COPY . .

RUN gradle clean build && \ 
    mv build/libs/car-rental-*.jar ./car-rental.jar

CMD ["java","-jar","car-rental.jar"]

RUN rm -r build && \
    rm -r build.gradle && \
    rm -r build.gradle.orig

USER carRentalBack

# /bin/bash Work