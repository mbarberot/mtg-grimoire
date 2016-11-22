package org.github.mbarberot;

import static spark.Spark.get;

public interface App {
    static void main(String[] args) {
        get("/", (request, response) -> "Hello World");
    }
}
