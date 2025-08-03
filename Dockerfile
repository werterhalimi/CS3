FROM maven AS base

RUN mkdir -p /app

WORKDIR /app

COPY . /app


FROM base AS build
RUN mvn package

FROM marctv/minecraft-papermc-server:1.21.8-21 AS server

RUN mkdir -p /data/plugins
RUN rm -rf /data/plugins
COPY --from=build /app/target/CS3-1.0-SNAPSHOT.jar /data/plugins/

CMD ["tail", "-d", "/dev/null"]
