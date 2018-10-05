package ru.wkn.model.htmlpage.htmlelements;

import ru.wkn.model.htmlpage.htmlelements.image.HtmlAttributeImage;

public class HtmlAttributeFactory {

    public static HtmlAttribute createHtmlAttribute(String attributeName, String attributeValue) {
        return attributeName.equals("src") ? new HtmlAttributeImage(attributeName, attributeValue)
                : null;
    }
}
