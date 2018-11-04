package ru.wkn.model;

import ru.wkn.model.html.handlers.HtmlPageHandler;
import ru.wkn.model.html.page.Page;
import ru.wkn.model.html.page.elements.Element;
import ru.wkn.model.html.utils.Converter;
import ru.wkn.model.http.Connector;
import ru.wkn.model.http.RequestManager;

import java.io.IOException;
import java.util.*;

public class HandlerFacade {

    private Connector connector;
    private RequestManager requestManager;
    private Map<String, Boolean> linksForVisit;
    private List<String> visitedLinks;
    private List<String> imageLinks;

    public HandlerFacade(Connector connector) {
        this.connector = connector;
        requestManager = new RequestManager(connector);

        linksForVisit = new HashMap<>();
        visitedLinks = new ArrayList<>();
        imageLinks = new ArrayList<>();
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
            if (link.startsWith((String) linksForVisit.keySet().toArray()[0]) == isSameServer) {
                links.add(link);
            }
        }
        return links;
    }

    public void initLinks(String httpMethod, final int depth) throws IOException {
        List<Page> pages = new ArrayList<>();
        Page page = getPage(httpMethod);

        initPages(pages, page, httpMethod, depth);
        fillVisitedLinksList();
        fillImagesLinksList(pages);
    }

    private void fillVisitedLinksList() {
        for (String link : linksForVisit.keySet()) {
            if (linksForVisit.get(link)) {
                visitedLinks.add(link);
            }
        }
    }

    private void fillImagesLinksList(List<Page> pages) {
        for (Page page : pages) {
            imageLinks.addAll(Converter
                    .convertElementsToTheirAttributeValues(HtmlPageHandler
                            .selectElementsFromHtmlPage(page, "img"), "src"));
        }
    }

    private void initPages(List<Page> pages, Page currentPage, String httpMethod, final int depth) throws IOException {
        pages.add(currentPage);
        List<String> linksFromCurrentPage = Converter
                .convertElementsToTheirAttributeValues(HtmlPageHandler
                        .selectElementsFromHtmlPage(currentPage, "a"), "href");
        addAllLinksToCollection(linksFromCurrentPage);

        if (!linksFromCurrentPage.isEmpty()) {

            for (String currentLink : linksFromCurrentPage) {

                if (currentLink.startsWith(connector.getUriAddress())) {
                    int newDepth = checkNewDepth(currentLink);

                    if (newDepth < depth) {
                        setConnector(new Connector(currentLink, connector.getPort()));
                        initPages(pages, getPage(httpMethod), httpMethod, depth);
                    }
                }

                if (currentLink.startsWith("/")) {
                    String newLink = connector.getUriAddress().concat(currentLink);
                    int newDepth = checkNewDepth(newLink);

                    if (newDepth < depth) {
                        setConnector(new Connector(newLink, connector.getPort()));
                        initPages(pages, getPage(httpMethod), httpMethod, depth);
                    }
                }
            }
        }
    }

    private void addAllLinksToCollection(List<String> links) {
        for (String link : links) {
            if (!foundElementInSet(link)) {
                linksForVisit.put(link, false);
            }
        }
    }

    private boolean foundElementInSet(String linkForEqual) {
        for (String link : linksForVisit.keySet()) {
            if (link.equals(linkForEqual)) {
                return true;
            }
        }
        return false;
    }

    private int checkNewDepth(String link) {
        Object[] links = linksForVisit.keySet().toArray();
        String currentLink;
        int currentDepth = 1;
        if ((currentLink = String.valueOf(links[0])) != null) {
            currentDepth = link.substring(currentLink.length()).split("/").length;
        }
        return currentDepth;
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
}
