package org.github.mbarberot.mtg.grimoire;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import spark.ModelAndView;
import spark.TemplateEngine;
import spark.template.jade.JadeTemplateEngine;

import static java.lang.Integer.valueOf;
import static java.util.Collections.emptyMap;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static spark.Spark.*;

interface App {
    int DEFAULT_PORT = 8080;
    Log LOGGER = LogFactory.getLog(App.class.getName());

    static void main(String[] args) {
        configure();
        declareRoutes();
    }

    static void declareRoutes() {
        TemplateEngine templateEngine = initTemplateEngine();

        get("/search", (request, response) -> new ModelAndView(emptyMap(), "pages/search"), templateEngine);
        get("/cards/:id", (request, response) -> new ModelAndView(emptyMap(), "pages/cards/card"), templateEngine);
        get("/", (request, response) -> new ModelAndView(emptyMap(), "pages/index"), templateEngine);
    }

    static JadeTemplateEngine initTemplateEngine() {
        return new JadeTemplateEngine();
    }

    static void configure() {
        port(getPort());
        staticFiles.location("/public");
    }

    static int getPort() {
        String givenPort = System.getProperty("server.port");
        if (isNotBlank(givenPort)) {
            try {
                return valueOf(givenPort);
            } catch (NumberFormatException e) {
                LOGGER.warn("Unparseable port in environment : " + givenPort, e);
            }
        }
        return DEFAULT_PORT;
    }
}
