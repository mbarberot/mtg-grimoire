package org.github.mbarberot;

import spark.ModelAndView;
import spark.servlet.SparkApplication;
import spark.template.jade.JadeTemplateEngine;

import static java.util.Collections.emptyMap;
import static spark.Spark.*;

public class App implements SparkApplication {
    
    public static void main(String[] args) {
        App app = new App();
        app.init();
    }

    @Override
    public void init() {
        staticFiles.location("/public");
        port(8080);

        JadeTemplateEngine jadeTemplateEngine = new JadeTemplateEngine();

        get("/search", (request, response) -> new ModelAndView(emptyMap(), "pages/search"), jadeTemplateEngine);
        get("/cards/:id", (request, response) -> new ModelAndView(emptyMap(), "pages/cards/card"), jadeTemplateEngine);
        get("/", (request, response) -> new ModelAndView(emptyMap(), "pages/index"), jadeTemplateEngine);
    }
}
