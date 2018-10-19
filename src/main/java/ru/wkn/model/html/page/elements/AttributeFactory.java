package ru.wkn.model.html.page.elements;

import ru.wkn.model.html.page.elements.image.AttributeImage;

public class AttributeFactory {

    public static Attribute createHtmlAttribute(String attributeName, String attributeValue) {
        return attributeName.equals("src") ? new AttributeImage(attributeName, attributeValue)
                : null;
    }
}
