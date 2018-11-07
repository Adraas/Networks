package ru.wkn.model.html.handlers;

import ru.wkn.model.html.page.Page;
import ru.wkn.model.html.page.elements.Element;

import java.util.ArrayList;
import java.util.List;

public class HtmlPageHandler {

    public static List<Element> getImagesFromSiteByCondition(Page page, boolean isSameServer) {
        List<Element> htmlImages = page.getElements("img");
        List<Element> images = new ArrayList<>();
        if (htmlImages != null) {
            for (Element currentImage : htmlImages) {
                String currentHrefToLowerCase = currentImage.getValueOfAttribute("src").toLowerCase();
                if (currentHrefToLowerCase.startsWith("/") == isSameServer) {
                    images.add(currentImage);
                }
            }
        }
        return images;
    }

    public static List<Element> selectElementsFromHtmlPage(Page page, String tagName) {
        return page.getElements(tagName);
    }
}
