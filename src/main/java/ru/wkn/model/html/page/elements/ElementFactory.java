package ru.wkn.model.html.page.elements;

import ru.wkn.model.html.page.elements.attributes.Attribute;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ElementFactory {

    public static Element createHtmlElement(String tagName, Map<String, String> dataAttributes) {
        return new Element(tagName, htmlAttributes(dataAttributes));
    }

    private static Set<Attribute> htmlAttributes(Map<String, String> dataAttributes) {
        Set<Attribute> attributes = new HashSet<>();
        if (dataAttributes == null || dataAttributes.isEmpty()) {
            return null;
        }
        Iterator<String> iterator = dataAttributes.keySet().iterator();
        for (; iterator.hasNext(); ) {
            String attributeName = iterator.next();
            Attribute attribute = new Attribute(attributeName, dataAttributes.get(attributeName));
            attributes.add(attribute);
        }
        return attributes;
    }
}
