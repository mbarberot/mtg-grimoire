# MTG-Grimoire

[![Build Status](https://travis-ci.org/mbarberot/mtg-grimoire.svg?branch=master)](https://travis-ci.org/mbarberot/mtg-grimoire)
[![codecov](https://codecov.io/gh/mbarberot/mtg-grimoire/branch/master/graph/badge.svg)](https://codecov.io/gh/mbarberot/mtg-grimoire)

Yet another app for Magic card game.

-----

## Demo

- [On Heroku](https://mtg-grimoire.herokuapp.com)

-----

## To do
### Road to v1.0

Goal : App setup, basic search, view cards

- [x] Create project
- [x] Continuous integration (with travis)
- [x] Continuous deployment (with heroku)
- [x] Docker dev environment
- [x] Use a mongo database with Jongo
- [x] Connect the Heroku app to a remote mongo database (with mlab)
- [x] Move to kotlin
- [x] Use semantic-ui for css
- [x] Use Jade for templating
- [x] Search view
- [x] Use intercooler.js for easy ajax handling
- [x] Cleaner view
- [x] Show a card
- [x] Code coverage
- [ ] Cleanup

### Road to v1.1

Goal : Tags, auto updating database

- [ ] Search with tags
- [ ] Load MTG.json data
- [ ] Generate tags
- [ ] Handle database version
- [ ] Automatically fetching MTG.json data and updating database at app startup with site lock

### Road to v1.2

Goal : Account, deck and collection

- [ ] Create(Sign up)/Edit/Remove account
- [ ] Login/Logout
- [ ] Show decks
- [ ] Create/Edit/Remove deck
- [ ] Show deck
- [ ] Add/remove card from/to deck
- [ ] Create/Edit/Remove collection
- [ ] Add/remove card from/to collection
- [ ] Search into collection

### Road to v1.3

Goal : Other features (view sets and card by sets)

- [ ] Code quality
- [ ] Show sets
- [ ] Show card by sets

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
./gradlew stage
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

