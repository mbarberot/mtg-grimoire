# MTG-Grimoire

[![Build Status](https://travis-ci.org/mbarberot/mtg-grimoire.svg?branch=master)](https://travis-ci.org/mbarberot/mtg-grimoire)
[![codecov](https://codecov.io/gh/mbarberot/mtg-grimoire/branch/master/graph/badge.svg)](https://codecov.io/gh/mbarberot/mtg-grimoire)

Yet another app for Magic card game.

-----

## Built with 

- Kotlin, 
- OpenJFX (aka JavaFX) and TornadoFX for embedded browser,
- H2 database,
- Gradle

- Typescript,
- GlimmerJS framework,
- Semantic UI

- Docker,
- Docker Compose

- Love <3,

-----

## Build

### With docker

Requires : 
- docker
- docker-compose

```
docker-compose -f .docker/build/docker-compose.yml up
```

### With the build tools

Requires : 
- openjdk
- openjfx

```
./gradlew build
```