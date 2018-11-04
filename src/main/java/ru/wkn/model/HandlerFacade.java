package ru.wkn.model;

import ru.wkn.model.html.handlers.HtmlPageHandler;
import ru.wkn.model.html.page.Page;
import ru.wkn.model.html.utils.Converter;
import ru.wkn.model.http.Connector;
import ru.wkn.model.http.RequestManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandlerFacade {

    private Connector connector;
    private RequestManager requestManager;

    private List<Page> pages;
    private Map<String, Boolean> linksForVisit;
    private List<String> visitedLinks;
    private List<String> imageLinks;

    public HandlerFacade(Connector connector) {
        this.connector = connector;
        requestManager = new RequestManager(connector);

        pages = new ArrayList<>();
        linksForVisit = new HashMap<>();
        visitedLinks = new ArrayList<>();
    }

    public void setConnector(Connector connector) {
        this.connector = connector;
        requestManager.setConnector(connector);
    }

    public List<String> getVisitedLinks() {
        return visitedLinks;
    }

    public List<String> getImageLinks(boolean isSameServer) {
        imageLinks = new ArrayList<>();
        fillImagesLinksList(isSameServer);
        return imageLinks;
    }

    private void fillImagesLinksList(boolean isSameServer) {
        int iterations = pages.size();
        for (int i = 0; i < iterations; i++) {
            imageLinks.addAll(getImageLinks(pages.get(i), visitedLinks.get(i), isSameServer));
        }
    }

    private List<String> getImageLinks(Page page, String uriAddress, boolean isSameServer) {
        List<String> images;

        if (isSameServer) {
            images = Converter
                    .convertElementsToTheirAttributeValues(HtmlPageHandler
                            .getImagesFromSiteByCondition(page, uriAddress, true), "src");

        } else {
            images = Converter
                    .convertElementsToTheirAttributeValues(HtmlPageHandler
                            .getImagesFromSiteByCondition(page, uriAddress, false), "src");
        }
        return images;
    }

    public void initLinks(String httpMethod, final int depth) throws IOException {
        Page page = getPage(httpMethod);

        initPages(page, httpMethod, depth);
        fillVisitedLinksList();
    }

    private void fillVisitedLinksList() {
        for (String link : linksForVisit.keySet()) {
            if (linksForVisit.get(link)) {
                visitedLinks.add(link);
            }
        }
    }

    private void initPages(Page currentPage, String httpMethod, final int depth) throws IOException {
        List<String> linksFromCurrentPage = Converter
                .convertElementsToTheirAttributeValues(HtmlPageHandler
                        .selectElementsFromHtmlPage(currentPage, "a"), "href");

        addLinksToCollectionForVisit(linksFromCurrentPage);
        pages.add(currentPage);

        if (!linksFromCurrentPage.isEmpty()) {

            for (String currentLink : linksFromCurrentPage) {

                if (currentLink.startsWith(connector.getUriAddress())) {
                    int newDepth = checkNewDepth(currentLink);

                    if (newDepth < depth) {
                        setConnector(new Connector(currentLink, connector.getPort()));
                        initPages(getPage(httpMethod), httpMethod, depth);
                    }
                }

                if (currentLink.startsWith("/")) {
                    String fullCurrentLink = connector.getUriAddress().concat(currentLink);
                    int newDepth = checkNewDepth(fullCurrentLink);

                    if (newDepth < depth) {
                        setConnector(new Connector(fullCurrentLink, connector.getPort()));
                        initPages(getPage(httpMethod), httpMethod, depth);
                    }
                }
            }
        }
    }

    private void addLinksToCollectionForVisit(List<String> links) {
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
        return new Page(Converter.convertJsoupElementsToHtmlElements(httpResponse));
    }
}
