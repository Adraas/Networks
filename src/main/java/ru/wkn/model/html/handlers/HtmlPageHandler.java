package ru.wkn.model.html.handlers;

import ru.wkn.model.html.page.Page;
import ru.wkn.model.html.page.elements.Element;

import java.util.ArrayList;
import java.util.List;

public class HtmlPageHandler {

    public static List<Element> getImagesFromSiteByCondition(Page page, String uriAddress, boolean isSameServer) {
        List<Element> htmlImages = page.getElements("img");
        if (htmlImages != null) {
            List<Element> imagesFromTheSameSite = new ArrayList<>();
            for (Element currentImage : htmlImages) {
                String currentHrefToLowerCase = currentImage.getValueOfAttribute("src").toLowerCase();
                if (currentHrefToLowerCase.startsWith(uriAddress.toLowerCase()) == isSameServer) {
                    imagesFromTheSameSite.add(currentImage);
                }
            }
            return imagesFromTheSameSite;
        }
        return null;
    }

    public static List<Element> selectElementsFromHtmlPage(Page page, String tagName) {
        return page.getElements(tagName);
    }
}
