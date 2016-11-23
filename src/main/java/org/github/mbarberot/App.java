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

        JadeTemplateEngine jadeTemplateEngine = new JadeTemplateEngine();
        
        get("/search", (request, response)  -> new ModelAndView(emptyMap(), "pages/search"), jadeTemplateEngine);
        get("/", (request, response)        -> new ModelAndView(emptyMap(), "pages/index"), jadeTemplateEngine);
    }
}
