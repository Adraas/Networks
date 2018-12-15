package ru.wkn.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.wkn.model.html.handlers.HtmlPageHandler;
import ru.wkn.model.html.utils.Converter;
import ru.wkn.protocols.httpconnecting.Connector;
import ru.wkn.protocols.HttpProtocol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandlerFacade {

    private Connector connector;
    private HttpProtocol httpProtocol;

    private String startUriAddress;
    private Map<String, Document> pages;
    private List<String> visitedLinks;
    private List<String> imageLinks;

    public HandlerFacade(Connector connector) {
        this.connector = connector;
        httpProtocol = new HttpProtocol(connector);
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
            Document currentPage = pages.get(visitedLink);
            if (currentPage != null) {
                imageLinks.addAll(getImageLinks(currentPage, visitedLink, isSameServer));
            }
        }
    }

    private List<String> getImageLinks(Document page, String uriAddress, boolean isSameServer) {
        List<String> images;
        if (isSameServer) {
            images = Converter
                    .convertElementsToTheirAttributeValues(HtmlPageHandler
                            .getImagesFromSiteByCondition(page, simpleHomeUriAddress(uriAddress), true), "src");

        } else {
            images = Converter
                    .convertElementsToTheirAttributeValues(HtmlPageHandler
                            .getImagesFromSiteByCondition(page, simpleHomeUriAddress(uriAddress), false), "src");
        }
        return images;
    }

    public void initLinks(String httpMethod, final int depth) throws IOException {
        startUriAddress = connector.getUriAddress();
        Document page = getPage(startUriAddress, httpMethod);
        visitedLinks.add(startUriAddress);

        initPages(page, httpMethod, depth);
    }

    private void initPages(Document currentPage, String httpMethod, final int depth) throws IOException {
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

    private String simpleHomeUriAddress(String uriAddress) {
        return uriAddress.substring(0, 7)
                .concat(uriAddress.substring(7, uriAddress.substring(7).split("/")[0].length() + 7));
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

    private Document getPage(String uriAddress, String httpMethod) throws IOException {
        connector = new Connector(uriAddress, connector.getPort());
        httpProtocol.setConnector(connector);
        String httpResponse = httpProtocol.getResponseOnHttpRequest(httpMethod);
        return Jsoup.parse(httpResponse);
    }
}
