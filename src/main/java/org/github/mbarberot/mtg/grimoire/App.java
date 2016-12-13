package org.github.mbarberot.mtg.grimoire;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import spark.ModelAndView;
import spark.servlet.SparkApplication;
import spark.template.jade.JadeTemplateEngine;

import static java.lang.Integer.valueOf;
import static java.util.Collections.emptyMap;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static spark.Spark.*;

public class App implements SparkApplication {

    public static final int DEFAULT_PORT = 8080;
    private static final Log LOGGER = LogFactory.getLog(App.class.getName());

    public static void main(String[] args) {
        App app = new App();
        app.init();
    }

    @Override
    public void init() {
        staticFiles.location("/public");

        port(getPort());

        JadeTemplateEngine jadeTemplateEngine = new JadeTemplateEngine();

        get("/search", (request, response) -> new ModelAndView(emptyMap(), "pages/search"), jadeTemplateEngine);
        get("/cards/:id", (request, response) -> new ModelAndView(emptyMap(), "pages/cards/card"), jadeTemplateEngine);
        get("/", (request, response) -> new ModelAndView(emptyMap(), "pages/index"), jadeTemplateEngine);
    }

    private int getPort() {
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
