package ru.wkn.model.html.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import ru.wkn.model.html.page.elements.Element;
import ru.wkn.model.html.page.elements.ElementFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Converter {

    public static List<Element> convertJsoupElementsToHtmlElements(String httpResponse) {
        Document document = Jsoup.parse(httpResponse);
        Elements elements = document.getAllElements();
        List<Element> htmlElements = new ArrayList<>();
        for (org.jsoup.nodes.Element element : elements) {
            Map<String, String> attributes = new HashMap<>();
            Attributes jsoupAttributes = element.attributes();
            Iterator<Attribute> iterator = jsoupAttributes.iterator();
            for (; iterator.hasNext(); ) {
                Attribute jsoupAttribute = iterator.next();
                attributes.put(jsoupAttribute.getKey(), jsoupAttribute.getValue());
            }
            String tagName = element.tagName();
            Element htmlElement = ElementFactory.createHtmlElement(tagName, attributes);
            htmlElements.add(htmlElement);
        }
        return htmlElements;
    }

    public static List<String> convertElementsToTheirAttributeValues(List<Element> elements, String attributeName) {
        List<String> links = new ArrayList<>();
        for (Element element : elements) {
            links.add(element.getValueOfAttribute(attributeName));
        }
        return links;
    }
}
