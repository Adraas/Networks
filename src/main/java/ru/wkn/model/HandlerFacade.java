package ru.wkn.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.wkn.model.html.handlers.HtmlPageHandler;
import ru.wkn.model.html.page.Page;
import ru.wkn.model.html.page.elements.Element;
import ru.wkn.model.html.utils.Converter;
import ru.wkn.model.http.Connector;
import ru.wkn.model.http.RequestManager;

import java.io.IOException;
import java.util.List;

public class HandlerFacade {

    private Connector connector;
    private RequestManager requestManager;

    public HandlerFacade(Connector connector) {
        this.connector = connector;
        requestManager = new RequestManager(connector);
    }

    public void setConnector(Connector connector) {
        this.connector = connector;
        requestManager.setConnector(connector);
    }

    public List<String> getImageLinksFrom(String server, String httpMethod) throws IOException {
        String uriAddress = connector.getUriAddress();
        String httpResponse = requestManager.getResponseOnHttpRequest(httpMethod);
        Page page = getPage(httpResponse);

        List<Element> images;

        switch (server) {
            case "same":
                images = HtmlPageHandler.getImagesFromSiteByCondition(page, uriAddress, true);
                break;
            case "other":
                images = HtmlPageHandler.getImagesFromSiteByCondition(page, uriAddress, false);
                break;
            default:
                images = null;
        }
        if (images != null) {
            return Converter.convertImagesToTheirLinks(images);
        }
        return null;
    }

    private Page getPage(String httpResponse) {
        Document document = Jsoup.parse(httpResponse);
        List<Element> elements = Converter.convertJsoupElementsToHtmlElements(document.getAllElements());
        return new Page(elements);
    }
}
