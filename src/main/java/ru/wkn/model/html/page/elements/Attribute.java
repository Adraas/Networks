package ru.wkn.model.html.page.elements;

public abstract class Attribute {

    private String attributeName;
    private String attributeValue;

    public Attribute(String attributeName, String attributeValue) {
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
