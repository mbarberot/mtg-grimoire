package org.github.mbarberot;

import spark.ModelAndView;
import spark.template.jade.JadeTemplateEngine;

import static java.util.Collections.emptyMap;
import static spark.Spark.*;

public interface App {
    static void main(String[] args) {
        staticFiles.location("/public");
        port(8080);

        JadeTemplateEngine jadeTemplateEngine = new JadeTemplateEngine();

        get("/search", (request, response)      -> new ModelAndView(emptyMap(), "pages/search"), jadeTemplateEngine);
        get("/cards/:id", (request, response)   -> new ModelAndView(emptyMap(), "pages/cards/card"), jadeTemplateEngine);
        get("/", (request, response)            -> new ModelAndView(emptyMap(), "pages/index"), jadeTemplateEngine);
    }
}
