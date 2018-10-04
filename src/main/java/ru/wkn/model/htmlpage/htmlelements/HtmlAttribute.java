package ru.wkn.model.htmlpage.htmlelements;

public abstract class HtmlAttribute {

    private String attributeName;
    private String attributeValue;

    public HtmlAttribute(String attributeName, String attributeValue) {
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }
}
