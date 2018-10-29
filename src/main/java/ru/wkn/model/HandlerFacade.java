package ru.wkn.model;

import ru.wkn.model.html.handlers.HtmlPageHandler;
import ru.wkn.model.html.page.Page;
import ru.wkn.model.html.page.elements.Element;
import ru.wkn.model.html.utils.Converter;
import ru.wkn.model.http.Connector;
import ru.wkn.model.http.RequestManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HandlerFacade {

    private Connector connector;
    private RequestManager requestManager;
    private String startUriAddress;
    private List<String> visitedLinks;
    private List<String> imageLinks;

    public HandlerFacade(Connector connector) {
        this.connector = connector;
        startUriAddress = connector.getUriAddress();
        requestManager = new RequestManager(connector);
    }

    public void setConnector(Connector connector) {
        this.connector = connector;
        requestManager.setConnector(connector);
    }

    public List<String> getVisitedLinks() {
        return visitedLinks;
    }

    public List<String> getImageLinks(boolean isSameServer) {
        List<String> links = new ArrayList<>();
        for (String link : imageLinks) {
            if (link.startsWith(startUriAddress) == isSameServer) {
                links.add(link);
            }
        }
        return links;
    }

    public void initLinks(String httpMethod, int depth) throws IOException {
        checkLists();

        String uriAddress = connector.getUriAddress();
        Page page = getPage(httpMethod);

        imageLinks.addAll(Converter.convertImagesToTheirLinks(getImages(uriAddress, page, true)));
        imageLinks.addAll(Converter.convertImagesToTheirLinks(getImages(uriAddress, page, false)));
        visitedLinks.add(connector.getUriAddress());

        List<Page> allPages = new ArrayList<>();
        allPages.add(page);

        for (int indexDepth = 1; indexDepth < depth; indexDepth++) {
            List<Page> currentPages = getPagesByAnchors(page, httpMethod);
            fillListOfLinks(currentPages);
            allPages.addAll(currentPages);
        }
    }

    private void checkLists() {
        if (visitedLinks != null) {
            visitedLinks = new ArrayList<>();
        }
        if (imageLinks != null) {
            imageLinks = new ArrayList<>();
        }
    }

    private Page getPage(String httpMethod) throws IOException {
        String httpResponse = requestManager.getResponseOnHttpRequest(httpMethod);
        List<Element> elements = Converter.convertJsoupElementsToHtmlElements(httpResponse);
        return new Page(elements);
    }

    private List<Element> getImages(String uriAddress, Page page, boolean isSameServer) {
        List<Element> images;

        if (isSameServer) {
            images = HtmlPageHandler.getImagesFromSiteByCondition(page, uriAddress, true);

        } else {
            images = HtmlPageHandler.getImagesFromSiteByCondition(page, uriAddress, false);
        }
        return Objects.requireNonNull(images);
    }

    private List<Page> getPagesByAnchors(Page currentPage, String httpMethod) throws IOException {
        List<Page> pages = new ArrayList<>();

        for (Element element : currentPage.getElements("a")) {
            String href = element.getValueOfAttribute("href");
            if (href.startsWith("/")) {
                setConnector(new Connector(connector.getUriAddress().concat(href), connector.getPort()));
                pages.add(getPage(httpMethod));
                visitedLinks.add(connector.getUriAddress());
            }
        }
        return pages;
    }

    private void fillListOfLinks(List<Page> pages) {
        // some instructions
    }
}
