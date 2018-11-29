package ru.wkn.model.html.utils;

import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

public class Converter {

    public static List<String> convertElementsToTheirAttributeValues(List<Element> elements, String attributeName) {
        List<String> links = new ArrayList<>();
        for (Element element : elements) {
            links.add(element.attributes().getIgnoreCase(attributeName));
        }
        return links;
    }
}
