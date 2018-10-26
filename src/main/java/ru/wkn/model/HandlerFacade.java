package ru.wkn.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.wkn.model.html.handlers.HtmlPageHandler;
import ru.wkn.model.html.page.Page;
import ru.wkn.model.html.page.elements.Element;
import ru.wkn.model.html.page.elements.image.Image;
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

    public List<String> getImageLinksFrom(String place, String httpMethod) throws IOException {
        String uriAddress = connector.getUriAddress();
        String httpResponse = requestManager.getResponseOnHttpRequest(httpMethod);
        Page page = getPage(httpResponse);

        List<Image> images = HtmlPageHandler.selectImagesFromHtmlElements(page.getElements());

        switch (place) {
            case "same":
                images = HtmlPageHandler.imagesFromTheSameSite(images, uriAddress);
                break;
            case "other":
                images = HtmlPageHandler.imagesFromTheOtherSite(images, uriAddress);
                break;
            default:
                images = null;
        }
        return Converter.convertImagesToTheirLinks(images);
    }

    private Page getPage(String httpResponse) {
        Document document = Jsoup.parse(httpResponse);
        List<Element> elements = Converter.convertJsoupElementsToHtmlElements(document.getAllElements());
        return new Page(elements);
    }
}
