package ru.wkn.model.utils;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.wkn.model.htmlpage.htmlelements.HtmlElement;
import ru.wkn.model.htmlpage.htmlelements.HtmlElementFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Converter {

    public static List<HtmlElement> convertJsoupElementsToHtmlElements(Elements elements) {
        List<HtmlElement> htmlElements = new ArrayList<>();
        for (Element element : elements) {
            String tagName = element.tagName();
            Map<String, String> attributes = element.dataset();
            HtmlElement htmlElement = HtmlElementFactory.createHtmlElement(tagName, attributes);
            if (htmlElement != null) {
                htmlElements.add(htmlElement);
            }
        }
        return htmlElements;
    }
}
