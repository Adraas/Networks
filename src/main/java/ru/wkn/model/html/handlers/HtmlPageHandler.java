package ru.wkn.model.html.handlers;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class HtmlPageHandler {

    public static List<Element> getImagesFromSiteByCondition(Document page, String uriAddress, boolean isSameServer) {
        Elements htmlImages = page.getElementsByTag("img");
        List<Element> images = new ArrayList<>();
        if (htmlImages != null) {
            for (Element currentImage : htmlImages) {
                String currentHrefToLowerCase = currentImage.attributes().getIgnoreCase("src").toLowerCase();
                if (currentHrefToLowerCase.startsWith("/")) {
                    currentHrefToLowerCase = uriAddress.concat(currentHrefToLowerCase);
                }
                if (currentHrefToLowerCase.startsWith(uriAddress) == isSameServer) {
                    images.add(currentImage);
                }
            }
        }
        return images;
    }

    public static List<Element> selectElementsFromHtmlPage(Document page, String tagName) {
        return page.getElementsByTag(tagName);
    }
}
