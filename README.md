# Magic Viewer

Yet another app for Magic card game.

## Features

- [ ] Main page
- [ ] Browse cards
- [ ] Search cards
- [ ] Dashboard for users
- [ ] Create and manage a collection
- [ ] Create and manage decks

## Built with 

- Java 8
- Spark micro-framework
- Jade/Pug templates (with jade4j)
- Semantic UI
- Tomcat 8
- Gradle

## How to run

```bash
./gradlew clean build

docker run \
  --rm \
  -it \
  -v "./build/libs:/usr/local/tomcat/webapps" \ 
  -p 8080:8080 \
  --name tomcat \
  tomcat:8-jre8-alpine
```

Go to [http://localhost:8080](http://localhost:8080)