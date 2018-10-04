package ru.wkn.model.htmlpage.htmlelements;

import ru.wkn.model.htmlpage.htmlelements.image.HtmlImage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HtmlElementFactory {

    public static HtmlElement createHtmlElement(String tagName, Map<String, String> dataAttributes) {
        return tagName.equals("a") ? new HtmlImage(htmlAttributes(dataAttributes))
                : null;
    }

    private static List<HtmlAttribute> htmlAttributes(Map<String, String> dataAttributes) {
        List<HtmlAttribute> htmlAttributes = new ArrayList<>();
        Iterator<String> iterator = dataAttributes.keySet().iterator();
        for (; iterator.hasNext();) {
            String attributeName = iterator.next();
            HtmlAttribute htmlAttribute = HtmlAttributeFactory
                    .createHtmlAttribute(attributeName, dataAttributes.get(attributeName));
            if (htmlAttribute != null) {
                htmlAttributes.add(htmlAttribute);
            }
        }
        return htmlAttributes;
    }
}
