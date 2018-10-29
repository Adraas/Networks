package ru.wkn.model.html.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import ru.wkn.model.html.page.elements.Element;
import ru.wkn.model.html.page.elements.ElementFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Converter {

    public static List<Element> convertJsoupElementsToHtmlElements(String httpResponse) {
        Document document = Jsoup.parse(httpResponse);
        Elements elements = document.getAllElements();
        List<Element> htmlElements = new ArrayList<>();
        for (org.jsoup.nodes.Element element : elements) {
            Map<String, String> attributes = element.dataset();
            String tagName = element.tagName();
            Element htmlElement = ElementFactory.createHtmlElement(tagName, attributes);
            htmlElements.add(htmlElement);
        }
        return htmlElements;
    }

    public static List<String> convertImagesToTheirLinks(List<Element> images) {
        List<String> links = new ArrayList<>();
        for (Element image : images) {
            links.add(image.getValueOfAttribute("src"));
        }
        return links;
    }
}
