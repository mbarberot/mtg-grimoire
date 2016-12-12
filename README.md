# MTG-Grimoire

[![Build Status](https://travis-ci.org/mbarberot/mtg-grimoire.svg?branch=master)](https://travis-ci.org/mbarberot/mtg-grimoire)

Yet another app for Magic card game.

## Demo

- [On Heroku](https://mtg-grimoire.herokuapp.com)

## Features

- [ ] Main page
- [ ] Browse cards
- [ ] Search cards
- [ ] Dashboard for users
- [ ] Create and manage a collection
- [ ] Create and manage decks

## Built with 

- Love <3,
- Java 8, 
- Spark micro-framework,
- Jade/Pug templates (with jade4j),
- Semantic UI,
- Gradle,
- Heroku,
- Docker

## How to run

```bash
./gradlew stage

docker run \
    --rm \
    -it \
    --name webserver \
    -p 8080:8080 \
    -v "$PWD/build/libs:/app" \
    -w /app \
    java:8-alpine \
    java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -jar mtg-grimoire-distrib.jar
```

Go to [http://localhost:8080](http://localhost:8080)