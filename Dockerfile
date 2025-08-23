FROM maven AS base

RUN mkdir -p /app

WORKDIR /app

COPY . /app


FROM base AS build
RUN mvn package

FROM marctv/minecraft-papermc-server:latest AS server

RUN mkdir -p /data/plugins
RUN rm -rf /data/plugins
RUN echo '[{"uuid": "1f64a03f-d9d2-4811-ae20-f8111ed01950","name": "PasShai", "level": 4, "bypassesPlayerLimit": true}] '> /data/ops.json
COPY --from=build /app/target/CS3-1.0-SNAPSHOT.jar /data/plugins/

CMD ["tail", "-d", "/dev/null"]
