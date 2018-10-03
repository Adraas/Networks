package ru.wkn.model.htmlpage.htmlelements;

import ru.wkn.model.htmlpage.htmlelements.image.HtmlImage;

import java.util.Map;

public class HtmlElementFactory {

    public static HtmlElement createHtmlElement(String tagName, Map<String, String> dataAttributes) {
        return tagName.equals("a") ? new HtmlImage(dataAttributes)
                : null;
    }
}
