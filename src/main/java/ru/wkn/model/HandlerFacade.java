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

    private String startUriAddress;
    private Map<String, Page> pages;
    private List<String> visitedLinks;
    private List<String> imageLinks;

    public HandlerFacade(Connector connector) {
        this.connector = connector;
        requestManager = new RequestManager(connector);
        pages = new HashMap<>();
        visitedLinks = new ArrayList<>();
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
        for (String visitedLink : visitedLinks) {
            Page currentPage = pages.get(visitedLink);
            if (currentPage != null) {
                imageLinks.addAll(getImageLinks(currentPage, visitedLink, isSameServer));
            }
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
        startUriAddress = connector.getUriAddress();
        Page page = getPage(startUriAddress, httpMethod);
        visitedLinks.add(startUriAddress);

        initPages(page, httpMethod, depth);
    }

    private void initPages(Page currentPage, String httpMethod, final int depth) throws IOException {
        List<String> linksFromCurrentPage = Converter
                .convertElementsToTheirAttributeValues(HtmlPageHandler
                        .selectElementsFromHtmlPage(currentPage, "a"), "href");
        String uriAddress = simpleUriAddress(connector.getUriAddress());
        pages.put(uriAddress, currentPage);

        if (!linksFromCurrentPage.isEmpty()) {
            for (String currentLink : linksFromCurrentPage) {
                currentLink = simpleUriAddress(currentLink);
                if (currentLink.length() > 1 && !currentLink.equals(uriAddress)) {
                    if (currentLink.startsWith(uriAddress)) {
                        int newDepth = checkNewDepth(currentLink);
                        if (newDepth <= depth && notFoundElementInList(currentLink)) {
                            initPages(getPage(currentLink, httpMethod), httpMethod, depth);
                            visitedLinks.add(currentLink);
                        }
                    }
                    if (currentLink.startsWith("/")) {
                        String fullCurrentLink = uriAddress.concat(currentLink);
                        int newDepth = checkNewDepth(fullCurrentLink);
                        if (newDepth <= depth && notFoundElementInList(fullCurrentLink)) {
                            initPages(getPage(fullCurrentLink, httpMethod), httpMethod, depth);
                            visitedLinks.add(fullCurrentLink);
                        }
                    }
                }
            }
        }
    }

    private String simpleUriAddress(String uriAddress) {
        if (uriAddress.endsWith("/")) {
            return uriAddress.substring(0, uriAddress.length() - 1);
        }
        return uriAddress;
    }

    private boolean notFoundElementInList(String linkForEqual) {
        for (String link : visitedLinks) {
            if (link.equals(linkForEqual)) {
                return false;
            }
        }
        return true;
    }

    private int checkNewDepth(String link) {
        int currentDepth = 1;
        if (startUriAddress != null) {
            int length = link.substring(startUriAddress.length()).split("/").length;
            currentDepth = length > 0 ? length : currentDepth;
        }
        return currentDepth;
    }

    private Page getPage(String uriAddress, String httpMethod) throws IOException {
        connector = new Connector(uriAddress, connector.getPort());
        requestManager.setConnector(connector);
        String httpResponse = requestManager.getResponseOnHttpRequest(httpMethod);
        return new Page(Converter.convertJsoupElementsToHtmlElements(httpResponse));
    }
}
