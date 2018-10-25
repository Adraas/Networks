package ru.wkn.model;

import ru.wkn.model.html.handlers.HtmlPageHandler;
import ru.wkn.model.http.Connector;
import ru.wkn.model.http.RequestManager;

import java.util.ArrayList;
import java.util.List;

public class HandlerFacade {

    private RequestManager requestManager;
    private HtmlPageHandler htmlPageHandler;

    public HandlerFacade(Connector connector) {
        requestManager = new RequestManager(connector);
        htmlPageHandler = new HtmlPageHandler();
    }

    public void setConnector(Connector connector) {
        requestManager.setConnector(connector);
    }

    public List<String> imagesFromTheSameSite() {
        List<String> imagesFromTheSameSite = new ArrayList<>();
        // some instructions
        return imagesFromTheSameSite;
    }

    public List<String> imagesFromTheOtherSite() {
        List<String>  imagesFromTheOtherSite = new ArrayList<>();
        // some instructions
        return imagesFromTheOtherSite;
    }
}
