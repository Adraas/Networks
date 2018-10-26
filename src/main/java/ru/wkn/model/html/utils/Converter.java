package ru.wkn.model.html.utils;

import org.jsoup.select.Elements;
import ru.wkn.model.html.page.elements.Element;
import ru.wkn.model.html.page.elements.ElementFactory;
import ru.wkn.model.html.page.elements.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Converter {

    public static List<Element> convertJsoupElementsToHtmlElements(Elements elements) {
        List<Element> htmlElements = new ArrayList<>();
        for (org.jsoup.nodes.Element element : elements) {
            String tagName = element.tagName();
            Map<String, String> attributes = element.dataset();
            Element htmlElement = ElementFactory.createHtmlElement(tagName, attributes);
            if (htmlElement != null) {
                htmlElements.add(htmlElement);
            }
        }
        return htmlElements;
    }

    public static List<Image> selectImagesFromHtmlElements(List<Element> elements) {
        List<Image> images = new ArrayList<>();
        for (Element element : elements) {
            if (element instanceof Image) {
                images.add((Image) element);
            }
        }
        return images;
    }

    public static List<String> convertImagesToTheirLinks(List<Image> images) {
        List<String> links = new ArrayList<>();
        for (Image image : images) {
            links.add(image.getValueOfAttribute("src"));
        }
        return links;
    }
}
