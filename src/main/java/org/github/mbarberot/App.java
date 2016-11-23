package org.github.mbarberot;

import spark.ModelAndView;
import spark.template.jade.JadeTemplateEngine;

import static java.util.Collections.emptyMap;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFiles;

public interface App {
    static void main(String[] args) {
        staticFiles.location("/public");
        port(8080);
                
        get("/", (request, response) -> new ModelAndView(emptyMap(), "pages/index"), new JadeTemplateEngine());
    }
}
