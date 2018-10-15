package ru.wkn.model.htmlpage.htmlelements;

import ru.wkn.model.htmlpage.htmlelements.image.HtmlImage;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HtmlElementFactory {

    public static HtmlElement createHtmlElement(String tagName, Map<String, String> dataAttributes) {
        return tagName.equals("img") ? new HtmlImage(htmlAttributes(dataAttributes))
                : null;
    }

    private static Set<HtmlAttribute> htmlAttributes(Map<String, String> dataAttributes) {
        Set<HtmlAttribute> htmlAttributes = new HashSet<>();
        if (dataAttributes == null || dataAttributes.isEmpty()) {
            return null;
        }
        Iterator<String> iterator = dataAttributes.keySet().iterator();
        for (; iterator.hasNext(); ) {
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
