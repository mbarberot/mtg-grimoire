# MTG-Grimoire

[![Build Status](https://travis-ci.org/mbarberot/mtg-grimoire-api.svg?branch=master)](https://travis-ci.org/mbarberot/mtg-grimoire-api)
[![codecov](https://codecov.io/gh/mbarberot/mtg-grimoire-api/branch/master/graph/badge.svg)](https://codecov.io/gh/mbarberot/mtg-grimoire-api)

Yet another app for Magic card game.

-----

## Demo

- [On Heroku](https://mtg-grimoire.herokuapp.com)

-----

## Built with 

- Love <3,
- Kotlin, 
- Spark micro-framework,
- Jade/Pug templates (with jade4j),
- MongoDB,
- Jongo,
- Semantic UI,
- Gradle,
- Heroku,
- MLab,
- Docker,
- Docker Compose

-----

## How to use

### Run with Docker

```bash
# Build jar :
./gradlew stage

# Mount DB container :
docker-compose up database

# In another shell
# Init db :
./docker/import/init.sh

# Back to first shell
# CTRL+C
# And then mount the whole app :
docker-compose down
docker-compose up
```

- [App at http://localhost:8080](http://localhost:8080)
- [Mongo UI at http://localhost:8081](http://localhost:8081)
- Java Debug port set to 5005 

### Init

Run the init script to create the test user in the database
```bash
./docker/import/init.sh
```

