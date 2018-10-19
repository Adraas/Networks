package ru.wkn.model.utils;

import org.jsoup.select.Elements;
import ru.wkn.model.html.page.elements.Element;
import ru.wkn.model.html.page.elements.ElementFactory;

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
}
