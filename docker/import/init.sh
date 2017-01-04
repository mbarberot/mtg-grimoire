#!/bin/bash

docker exec -it mtg_grimoire_database mongo admin --eval 'db.getSiblingDB("mtg_grimoire").createUser({user: "test", pwd: "test", roles: [{ role: "dbOwner", db: "mtg_grimoire" }]})'