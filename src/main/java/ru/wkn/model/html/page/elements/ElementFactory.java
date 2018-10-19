package ru.wkn.model.html.page.elements;

import ru.wkn.model.html.page.elements.image.Image;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ElementFactory {

    public static Element createHtmlElement(String tagName, Map<String, String> dataAttributes) {
        return tagName.equals("img") ? new Image(htmlAttributes(dataAttributes))
                : null;
    }

    private static Set<Attribute> htmlAttributes(Map<String, String> dataAttributes) {
        Set<Attribute> attributes = new HashSet<>();
        if (dataAttributes == null || dataAttributes.isEmpty()) {
            return null;
        }
        Iterator<String> iterator = dataAttributes.keySet().iterator();
        for (; iterator.hasNext(); ) {
            String attributeName = iterator.next();
            Attribute attribute = AttributeFactory
                    .createHtmlAttribute(attributeName, dataAttributes.get(attributeName));
            if (attribute != null) {
                attributes.add(attribute);
            }
        }
        return attributes;
    }
}
